package objects.user;

import config.ConfigProvider;
import io.qameta.allure.Step;
import utils.RandomStringGenerator;

public class UserGenerator {

    @Step("Сгенерировать объект класса User из конфигов")
    public static User generateUserUIForLogIn() {
        return new User(ConfigProvider.LOGIN, ConfigProvider.PASS);
    }

    @Step("Сгенерировать рандомный объект класса User")
    public static User generateRandomUser() {
        return new User(
                RandomStringGenerator.generateAlphanumeric(10),
                RandomStringGenerator.generateAlphanumeric(10)
        );
    }
}
