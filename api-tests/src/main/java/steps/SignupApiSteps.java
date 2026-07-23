package steps;

import api.BaseApi;
import api.baseResponse.EmptyBaseResponse;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import objects.user.User;
import static api.ApiEndPoints.SIGNUP;

public class SignupApiSteps extends BaseApiSteps {

    @Step("Отправить запрос на регистрацию")
    public static EmptyBaseResponse signup(User user) {
        Allure.addAttachment("Логин пользователя", user.getUsername());
        BaseApi baseApi = new BaseApi();
        return baseApi.getEntityWithEmptyResponse(user, SIGNUP.getEndPoint());
    }
}
