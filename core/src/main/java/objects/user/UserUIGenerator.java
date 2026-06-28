package objects.user;

import config.ConfigProvider;
import io.qameta.allure.Step;
import utils.RandomStringGenerator;

public class UserUIGenerator {

    @Step("Сгенерировать объект класса UserUI из конфигов")
    public static UserUI generateUserUIForLogIn() {
        return new UserUI(ConfigProvider.LOGIN, ConfigProvider.PASS);
    }

    @Step("Сгенерировать рандомный объект класса UserUI")
    public static UserUI generateRandomUserUI() {
        return new UserUI(
                RandomStringGenerator.generateAlphanumeric(10),
                RandomStringGenerator.generateAlphanumeric(10)
        );
    }
}
