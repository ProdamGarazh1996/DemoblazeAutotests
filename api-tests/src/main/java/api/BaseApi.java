package api;

import api.baseResponse.BaseResponse;
import api.baseResponse.BaseResponseWithDataArray;
import api.baseResponse.EmptyBaseResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.$Gson$Types;
import config.ConfigProvider;
import io.qameta.allure.Allure;
import lombok.Getter;
import lombok.SneakyThrows;
import utils.JsonUtils;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Getter
public class BaseApi {


    public <T> String prettyPrintAsJson(T requestBody) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(requestBody);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    HttpClient client = HttpClient.newHttpClient();

    Gson gson = new Gson();

    public <T> void attachRequestAndResponseDataToAllureReport(HttpResponse<String> response, String endpoint, T body) {
        Allure.attachment("API запрос", "ContentType:  " + response.headers().firstValue("Content-Type") + " \n" +
                "Endpoint:  " + endpoint + " \n" + "Body:  " + prettyPrintAsJson(body));
        Allure.attachment("API ответ", response.body() + " \n" +
                "Status code: " + response.statusCode() + "\n" +
                "Headers: " + "\n" + response.headers());
    }


    /**
     * Отправка POST запроса по эндпоинту
     *
     * @param endPoint - базовый URL для API
     * @param body     - тело запроса
     */
    @SneakyThrows
    protected HttpResponse<String> sendPost(String endPoint, Object body) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ConfigProvider.API_URL + endPoint))
                .header("Content-Type", "application/json") // Inform the server about the JSON format
                .header("Accept", "application/json")       // Request JSON back from the server
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(body)))  // Attach payload to the POST method
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private <T> BaseResponse<T> mapResponseToObject(HttpResponse<String> response, Class<T> clazz) {
        BaseResponse<T> createItemResponse = null;
        try {
            // Создаем параметризованный тип BaseResponse<clazz> динамически
            Type javaType = $Gson$Types.newParameterizedTypeWithOwner(null, BaseResponse.class, clazz);

            // Десериализуем строку в объект нужного типа
            createItemResponse = gson.fromJson(response.body(), javaType);
            createItemResponse.setCode(response.statusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return createItemResponse;
    }

    /**
     * Отправка POST-запроса
     *
     * @param body     - тело
     * @param endpoint - путь к ресурсу
     * @param clazz    - параметризированный тип ответа
     * @param <T>      - тип ответа
     * @return - тело
     */
    public <T> BaseResponseWithDataArray<T> getArraysPost(Object body, String endpoint, Class<T> clazz) {
        HttpResponse<String> response = sendPost(endpoint, body);
        attachRequestAndResponseDataToAllureReport(response, endpoint, body);
        BaseResponseWithDataArray<T> createItemResponse = null;
        try {
            // Создаем параметризованный тип BaseResponse<clazz> динамически
            Type javaType = $Gson$Types.newParameterizedTypeWithOwner(null, BaseResponseWithDataArray.class, clazz);

            // Десериализуем строку в объект нужного типа
            createItemResponse = gson.fromJson(response.body(), javaType);
            createItemResponse.setCode(response.statusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return createItemResponse;
    }

    public EmptyBaseResponse getEntityWithEmptyResponse(Object body, String endpoint) {
        HttpResponse<String> response = sendPost(endpoint, body);
        attachRequestAndResponseDataToAllureReport(response, endpoint, body);
        return mapResponseToObject(response);
    }

    private EmptyBaseResponse mapResponseToObject(HttpResponse<String> response) {
        String body = response.body();
        EmptyBaseResponse createItemResponse = null;
        //Если ответ пустой, то просто записываем статус код
        if (body.isEmpty() || body.equals("\"\"\n")) {
            createItemResponse = new EmptyBaseResponse();
            createItemResponse.setCode(response.statusCode());
        } else {
            // Если не пустой то маппим с помощью jackson
            try {
                createItemResponse = gson.fromJson(body, EmptyBaseResponse.class);
                createItemResponse.setCode(response.statusCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return createItemResponse;
    }

    /**
     * Отправка POST-запроса
     *
     * @param body     - тело
     * @param endpoint - путь к ресурсу
     * @param clazz    - параметризированный тип ответа
     * @param <T>      - тип ответа
     * @return - тело
     */
    public <T> BaseResponse<T> getEntityPost(Object body, String endpoint, Class<T> clazz) {
        HttpResponse<String> response = sendPost(endpoint, body);
        attachRequestAndResponseDataToAllureReport(response, endpoint, body);

        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(response.statusCode());

        String responseString = response.body();

        if (JsonUtils.isJson(responseString)) {
            try {
                // Используем gson для десериализации вместо mapper.readValue
                T objectResponse = gson.fromJson(responseString, clazz);
                baseResponse.setItem(objectResponse);
            } catch (JsonSyntaxException e) {
                // Перехватываем специфичное для Gson исключение парсинга
                throw new RuntimeException("Ошибка парсинга JSON ответа для класса " + clazz.getSimpleName(), e);
            }
        } else {
            baseResponse.setMessage(responseString);
        }

        return baseResponse;
    }


}

