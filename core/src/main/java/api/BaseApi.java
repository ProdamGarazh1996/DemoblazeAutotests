package api;

import api.baseResponse.BaseResponse;
import api.baseResponse.BaseResponseWithDataArray;
import api.baseResponse.EmptyBaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.GsonBuilder;
import config.ConfigProvider;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.SneakyThrows;
import utils.JsonUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Getter
public class BaseApi {

    protected final ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    protected RequestSpecification requestSpecification;

    /**
     * Конструктор спецификации для обращения к API
     */
    public BaseApi() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ConfigProvider.API_URL)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .setRelaxedHTTPSValidation()
                .build();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); // Отключение ошибок маппера для пропуска несуществующих полей DTO
    }


    /**
     * Конструктор спецификации для обращения к API c авторизацией
     *
     * @param baseUrl  - базовый url
     */
    public BaseApi(String baseUrl) {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .setRelaxedHTTPSValidation()
                .build();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  // Отключение ошибок маппера для пропуска несуществующих полей DTO
    }

    /**
     * Отправка GET запроса по эндпоинту
     *
     * @param endPoint - базовый URL для API
     */
    @SneakyThrows
    protected Response sendGet(String endPoint) {
        Response response = RestAssured.given().spec(requestSpecification).when().get(endPoint).thenReturn();
        response.prettyPrint();
        return response;
    }

    /**
     * Отправка GET запроса по эндпоинту с параметрами
     *
     * @param endPoint - базовый URL для API
     */
    private Response sendGet(String endPoint, Map<String, String> params) {
        Response response = RestAssured.given().spec(requestSpecification).params(params).when().get(endPoint).thenReturn();
        response.prettyPrint();
        return response;
    }

    /**
     * Отправка POST запроса по эндпоинту
     *
     * @param endPoint - базовый URL для API
     * @param body     - тело запроса
     */
    @SneakyThrows
    protected Response sendPost(String endPoint, Object body) {
        Response response = RestAssured.given()
                .spec(requestSpecification)
                .when()
                .body(body)
                .post(endPoint)
                .thenReturn();
        return response;
    }

    /**
     * Отправка POST запроса по эндпоинту c multiPart
     *
     * @param endPoint        - базовый URL для API
     * @param multiPartParams - тело запроса
     */
    @SneakyThrows
    protected Response sendPost(String endPoint, List<MultiPartSpecification> multiPartParams) {
        for (MultiPartSpecification param : multiPartParams) {
            requestSpecification.multiPart(param);
        }
        Response response = RestAssured.given()
                .spec(requestSpecification)
                .when()
                .post(endPoint)
                .thenReturn();
        response.prettyPrint();
        return response;
    }


    /**
     * Отправка POST запроса по эндпоинту c headers
     *
     * @param endPoint - базовый URL для API
     * @param body     - тело запроса
     * @param headers  - заголовки запроса
     */
    @SneakyThrows
    protected Response sendPost(String endPoint, Object body, Map<String, String> headers) {
        Response response = RestAssured.given()
                .spec(requestSpecification)
                .headers(headers)
                .when()
                .body(body)
                .post(endPoint)
                .thenReturn();
        response.prettyPrint();
        return response;
    }
    /**
     * Отправка POST запроса по эндпоинту через form-data на создание документа
     *
     * @param endPoint - базовый URL для API
     * @param body     - тело запроса
     * @param fileURL  - путь до файла в корневой папке проекта
     */
    @SneakyThrows
    protected Response sendPostDocument(String endPoint, Object body, String fileURL){
        Response response = RestAssured.given()
                .spec(requestSpecification)
                .multiPart(new MultiPartSpecBuilder(body)
                        .controlName("document")
                        .mimeType("application/json")
                        .charset(StandardCharsets.UTF_8).build())
                .multiPart("attachments", new File(fileURL), "image/jpeg")
                .when()
                .post(endPoint);
        response.prettyPrint();
        return response;
    }

    /**
     * Отправка PUT запроса по эндпоинту c headers
     *
     * @param endPoint - базовый URL для API
     * @param body     - тело запроса
     */
    @SneakyThrows
    protected Response sendPut(String endPoint, Object body) {
        Response response = RestAssured.given()
                .spec(requestSpecification)
                .when()
                .body(body)
                .put(endPoint)
                .thenReturn();
        response.prettyPrint();
        return response;
    }

    /**
     * Отправка PUT запроса по эндпоинту c headers
     *
     * @param endPoint - базовый URL для API
     */
    @SneakyThrows
    protected Response sendPut(String endPoint) {
        Response response = RestAssured.given()
                .spec(requestSpecification)
                .when()
                .put(endPoint)
                .thenReturn();
        response.prettyPrint();
        return response;
    }

    /**
     * Отправка DELETE запроса по эндпоинту
     *
     * @param endPoint - базовый URL для API
     */
    @SneakyThrows
    protected Response sendDelete(String endPoint) {
        Response response = RestAssured.given().spec(requestSpecification).when().delete(endPoint).thenReturn();
        response.prettyPrint();
        return response;
    }
    /**
     * Отправка DELETE запроса по эндпоинту
     *
     * @param endPoint -  URL для API
     * @param body - тело запроса
     */
    @SneakyThrows
    protected Response sendDelete(String endPoint, Object body) {
        Response response = RestAssured.given()
                .spec(requestSpecification)
                .when()
                .body(body)
                .delete(endPoint)
                .thenReturn();
        response.prettyPrint();
        return response;
    }

    private  <T> BaseResponse<T> mapResponseToObject(Response response, Class<T> clazz) {
        BaseResponse<T> createItemResponse = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(BaseResponse.class, clazz);
            createItemResponse = mapper.readValue(response.asString(), javaType);
            createItemResponse.setCode(response.getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return createItemResponse;
    }


    private EmptyBaseResponse mapResponseToObject(Response response) {
        EmptyBaseResponse createItemResponse = null;
        //Если ответ пустой, то просто записываем статус код
        if (response.getBody().asString().isEmpty() || response.getBody().asString().equals("\"\"\n")) {
            createItemResponse = new EmptyBaseResponse();
            createItemResponse.setCode(response.getStatusCode());
        } else {
            // Если не пустой то маппим с помощью jackson
            try {

                createItemResponse = mapper.readValue(response.asString(), EmptyBaseResponse.class);
                createItemResponse.setCode(response.getStatusCode());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return createItemResponse;
    }

    public <T> void attachRequestAndResponseDataToAllureReport(Response response, String endpoint, T body) {
        Allure.attachment("API запрос", "ContentType:  " + response.contentType() + " \n" +
                "Endpoint:  " + endpoint + " \n" + "Body:  " + prettyPrintAsJson(body));
        Allure.attachment("API ответ", response.asPrettyString() + " \n" +
                "Status code: " + response.getStatusCode() + "\n" +
                "Headers: " + "\n" + response.headers());
    }

    public void attachRequestAndResponseDataToAllureReport(Response response, String endpoint) {
        Allure.attachment("API запрос", "ContentType:  " + response.contentType() + " \n" +
                "Endpoint:  " + endpoint + " \n");
        Allure.attachment("API ответ", response.asPrettyString() + " \n" +
                "Status code: " + response.getStatusCode() + "\n" +
                "Headers: " + "\n" + response.headers());
    }

    public <T> BaseResponse<T> getEntity(String endpoint, Class<T> clazz) {
        Response response = sendGet(endpoint);
        BaseResponse<T> createItemResponse = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(BaseResponse.class, clazz);
            createItemResponse = mapper.readValue(response.asString(), javaType);
            createItemResponse.setCode(response.getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return createItemResponse;
    }


    public <T> BaseResponseWithDataArray<T> getArrays(String endpoint, Map<String, String> params, Class<T> clazz) {
        Response response = sendGet(endpoint, params);

        BaseResponseWithDataArray<T> createItemResponse = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(BaseResponseWithDataArray.class, clazz);
            createItemResponse = mapper.readValue(response.asString(), javaType);
            createItemResponse.setCode(response.getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return createItemResponse;
    }

    public <T> BaseResponseWithDataArray<T> getArrays(String endpoint, Class<T> clazz) {
        Response response = sendGet(endpoint);

        BaseResponseWithDataArray<T> createItemResponse = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(BaseResponseWithDataArray.class, clazz);
            createItemResponse = mapper.readValue(response.asString(), javaType);
            createItemResponse.setCode(response.getStatusCode());
        } catch (IOException e) {
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
    public <T> BaseResponse<T> createEntity(T body, String endpoint, Class<T> clazz) {
        Response response = sendPost(endpoint, body);
        attachRequestAndResponseDataToAllureReport(response, endpoint, body);
        return mapResponseToObject(response, clazz);
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
        Response response = sendPost(endpoint, body);
        attachRequestAndResponseDataToAllureReport(response, endpoint, body);
        BaseResponseWithDataArray<T> createItemResponse = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(BaseResponseWithDataArray.class, clazz);
            createItemResponse = mapper.readValue(response.asString(), javaType);
            createItemResponse.setCode(response.getStatusCode());
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
    public <T> BaseResponse<T> getEntityPost(Object body, String endpoint, Class<T> clazz) {
        Response response = sendPost(endpoint, body);
        attachRequestAndResponseDataToAllureReport(response, endpoint, body);
        BaseResponse<T> baseResponse;
        try {
            baseResponse = new BaseResponse<>();
            baseResponse.setCode(response.getStatusCode());
            if (JsonUtils.isJson(response.asString())) {
                T objectResponse = mapper.readValue(response.prettyPrint(), clazz);
                baseResponse.setItem(objectResponse);
            } else {
                baseResponse.setMessage(response.asString());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return baseResponse;
    }

    public EmptyBaseResponse getEntityWithEmptyResponse(Object body, String endpoint) {
        Response response = sendPost(endpoint, body);
        attachRequestAndResponseDataToAllureReport(response, endpoint, body);
        return mapResponseToObject(response);
    }

    /**
     * Отправка DELETE-запроса
     *
     * @param body - тело запроса
     * @param endpoint - путь к ресурсу
     * @return - пустой ответ
     */
    public EmptyBaseResponse deleteEntity(Object body, String endpoint) {
        Response response = sendDelete(endpoint, body);
        attachRequestAndResponseDataToAllureReport(response, endpoint);
        return mapResponseToObject(response);
    }


    /**
     * Отправка POST-запроса
     *
     * @param multiPartParams - параметры форм-даты
     * @param endpoint - путь к ресурсу
     * @param clazz    - параметризированный тип ответа
     * @param <T>      - тип ответа
     * @return - тело
     */
    public <T> BaseResponse<T> createEntity(List<MultiPartSpecification> multiPartParams, String endpoint, Class<T> clazz) {
        Response response = sendPost(endpoint, multiPartParams);
        attachRequestAndResponseDataToAllureReport(response, endpoint);
        return mapResponseToObject(response, clazz);
    }


    /**
     * Отправка POST-запроса на создание документа
     *
     * @param body     - тело
     * @param endpoint - путь к ресурсу
     * @param clazz    - параметризированный тип
     * @param <T>      - тип ответа
     * @return - ответ
     */
    @Deprecated
    public <T> BaseResponse<T> createDocumentEntity(Object body, String endpoint, Class<T> clazz, String fileURL) {
        Response response = sendPostDocument(endpoint, body, fileURL);
        attachRequestAndResponseDataToAllureReport(response, endpoint, body);
        return mapResponseToObject(response, clazz);
    }
    /**
     * Отправка POST-запроса на редактирование документа
     *
     * @param body     - тело
     * @param endpoint - путь к ресурсу
     * @param clazz    - параметризированный тип
     * @param <T>      - тип ответа
     * @return - ответ
     */
    public <T> BaseResponse<T> editDocumentEntity(Object body, String endpoint, Class<T> clazz, String fileURL) {
        Response response = sendPostDocument(endpoint, body, fileURL);
        attachRequestAndResponseDataToAllureReport(response, endpoint, body);
        return mapResponseToObject(response, clazz);
    }

    /**
     * Отправка PUT-запроса
     *
     * @param body     - тело
     * @param endpoint - путь к ресурсу
     * @param clazz    - параметризированный тип ответа
     * @param <T>      - тип ответа
     * @return - тело
     */
    public <T> BaseResponse<T> updateEntity(T body, String endpoint, Class<T> clazz) {
        Response response = sendPut(endpoint, body);
        attachRequestAndResponseDataToAllureReport(response, endpoint, body);
        return mapResponseToObject(response, clazz);
    }
    /**
     * Отправка POST-запроса
     *
     * @param endpoint - путь к ресурсу
     * @param clazz    - параметризированный тип ответа
     * @param <T>      - тип ответа
     * @param multiPartParams - параметры для form-data
     * @return - тело
     */
    public <T> BaseResponse<T> updateEntity(List<MultiPartSpecification> multiPartParams, String endpoint, Class<T> clazz) {
        Response response = sendPost(endpoint, multiPartParams);
        attachRequestAndResponseDataToAllureReport(response, endpoint);
        return mapResponseToObject(response, clazz);
    }

    /**
     * Отправка PUT-запроса
     *
     * @param endpoint - путь к ресурсу
     * @param clazz    - параметризированный тип ответа
     * @param <T>      - тип ответа
     * @return - тело
     */
    public <T> BaseResponse<T> updateEntity(String endpoint, Class<T> clazz) {
        Response response = sendPut(endpoint);
        attachRequestAndResponseDataToAllureReport(response, endpoint);
        return mapResponseToObject(response, clazz);
    }

    /**
     * Отправка PUT-запроса
     *
     * @param endpoint - путь к ресурсу
     * @return - тело
     */
    public Response updateEntity(String endpoint) {
        Response response = sendPut(endpoint);
        attachRequestAndResponseDataToAllureReport(response, endpoint);
        return response;
    }


    /**
     * Отправка PUT-запроса
     *
     * @param endpoint - путь к ресурсу
     * @return - тело
     */
    public <T> EmptyBaseResponse updateEntity(String endpoint, T body, Class<T> clazz) {
        Response response = sendPut(endpoint, body);
        attachRequestAndResponseDataToAllureReport(response, endpoint);
        return mapResponseToObject(response);
    }

    /**
     * Отправка POST-запроса с заголовками
     *
     * @param body     - тело
     * @param endpoint - путь к ресурсу
     * @param clazz    - параметризированный тип ответа
     * @param headers  - заголовки
     * @param <T>      - тип ответа
     * @return - тело
     */
    public <T> BaseResponse<T> createEntity(T body, String endpoint, Class<T> clazz, Map<String, String> headers) {
        Response response = sendPost(endpoint, body, headers);
        attachRequestAndResponseDataToAllureReport(response, endpoint, body);
        return mapResponseToObject(response, clazz);
    }

    public <T> String prettyPrintAsJson(T requestBody) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(requestBody);
    }

    /**
     * Отправка DELETE-запроса
     *
     * @param endpoint - путь к ресурсу
     */
    public Response deleteEntity(String endpoint) {
        return sendDelete(endpoint);
    }

    public Response getResponse(String endPoint) {
        return sendGet(endPoint);
    }

    public Response getResponse(String endPoint, Map<String, String> params) {
        return sendGet(endPoint, params);
    }
}

