package tests;

import api.baseResponse.BaseResponse;
import api.baseResponse.EmptyBaseResponse;
import io.qameta.allure.Description;
import objects.ErrorMessage;
import objects.user.User;
import objects.user.UserGenerator;
import org.testng.annotations.Test;
import steps.LoginApiSteps;
import steps.SignupApiSteps;
import utils.ErrorMessageText;
import utils.HttpStatusCode;

public class LoginTests {
    @Test(description = "Проверка успешной авторизации через АПИ", groups = {"regress", "test-11"})
    @Description("Проверка успешной авторизации через АПИ")
    public void checkLoginTest() {
        //Сгенерировать нового пользователя
        User user = UserGenerator.generateRandomUser();
        //1. Зарегистрировать нового пользователя
        EmptyBaseResponse emptyBaseResponse = SignupApiSteps.signup(user);
        //2. Проверить ответ АПИ метода signup
        SignupApiSteps.checkStatusCode(HttpStatusCode.OK.getCode(), emptyBaseResponse.getCode());
        //3. Авторизоваться под новым пользователем
        BaseResponse<User> baseResponse = LoginApiSteps.login(user);
        //4. Проверить ответ АПИ метода login
        LoginApiSteps.checkLoginResponse(baseResponse);
    }

    @Test(description = "Проверка ошибки авторизации используя данные несуществующего пользователя", groups = {"regress", "test-14"})
    @Description("Проверка ошибки авторизации используя данные несуществующего пользователя")
    public void checkLoginWithNonExistingUserTest() {
        //1. Попытка авторизации с данными несуществующего пользователя
        BaseResponse<ErrorMessage> baseResponse = LoginApiSteps.sendNonExistingUserCredentials();
        //2. Проверить ответ неуспешной авторизации
        LoginApiSteps.checkUnsuccessfulLoginResponse(baseResponse, ErrorMessageText.NON_EXISTING_USER);
    }

    @Test(description = "Проверка ошибки авторизации отправляя пустой json", groups = {"regress", "test-15"})
    @Description("Проверка ошибки авторизации отправляя пустой json")
    public void checkLoginWithEmptyJsonTest() {
        //1. Попытка авторизации с данными несуществующего пользователя
        BaseResponse<ErrorMessage> baseResponse = LoginApiSteps.sendEmptyLoginRequest();
        //2. Проверить ответ неуспешной авторизации
        LoginApiSteps.checkUnsuccessfulLoginResponse(baseResponse, ErrorMessageText.EMPTY_REQUEST);
    }
}
