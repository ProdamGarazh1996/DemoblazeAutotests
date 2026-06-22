package objects.user;

import config.ConfigProvider;
import io.qameta.allure.Step;

public class UserUIGenerator {

    @Step("Сгенерировать объект класса UserUI из конфигов")
    public static UserUI generateUserUIForLogIn() {
        return new UserUI(ConfigProvider.LOGIN, ConfigProvider.PASS);
    }
}
