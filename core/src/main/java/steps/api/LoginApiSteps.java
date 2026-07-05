package steps.api;

import api.BaseApi;
import api.baseResponse.BaseResponse;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import objects.user.User;
import objects.user.UserGenerator;
import objects.ErrorMessage;
import org.testng.Assert;
import utils.ErrorMessageText;
import utils.HttpStatusCode;
import static api.ApiEndPoints.LOGIN;

public class LoginApiSteps extends BaseApiSteps {
    @Step("Отправить запрос на авторизацию")
    public static BaseResponse<User> login(User user) {
        Allure.addAttachment("Логин пользователя", user.getUsername());
        BaseApi baseApi = new BaseApi();
        return baseApi.getEntityPost(user, LOGIN.getEndPoint(), User.class);
    }

    @Step("Проверить данные полученные из АПИ при успешной авторизации")
    public static void checkLoginResponse(BaseResponse<User> response) {
        checkStatusCode(HttpStatusCode.OK.getCode(), response.getCode());
        if (!response.getMessage().contains("Auth_token")) {
            Assert.fail("В ответе не содержится токен авторизации: " + response.getMessage());
        }
    }

    @Step("Проверить данные полученные из АПИ при неуспешной авторизации")
    public static void checkUnsuccessfulLoginResponse(BaseResponse<ErrorMessage> response, ErrorMessageText errorMessageText) {
        checkStatusCode(HttpStatusCode.OK.getCode(), response.getCode());
        ErrorMessage errorMessage = response.getItem();
        Assert.assertEquals(errorMessage.getErrorMessage(), errorMessageText.getMessage(), "Ожидаемое значение сообщения об ошибке: \"" + errorMessageText.getMessage() + "\"" + " не соответствует фактическому: " + errorMessage.getErrorMessage());
    }

    @Step("Извлечь токен авторизации из строки")
    public static String extractAuthToken(String message) {
        String splitedMessage = message.split(" ")[1];
        return splitedMessage.substring(0, splitedMessage.length() - 2);
    }

    @Step("Отправить запрос с несуществующим логином и паролем")
    public static BaseResponse<ErrorMessage> sendNonExistingUserCredentials() {
        User user = UserGenerator.generateRandomUser();
        BaseApi baseApi = new BaseApi();
        return baseApi.getEntityPost(user, LOGIN.getEndPoint(), ErrorMessage.class);
    }

    @Step("Отправить пустой json в метод авторизации")
    public static BaseResponse<ErrorMessage> sendEmptyLoginRequest() {
        BaseApi baseApi = new BaseApi();
        return baseApi.getEntityPost("{}", LOGIN.getEndPoint(), ErrorMessage.class);
    }
}
