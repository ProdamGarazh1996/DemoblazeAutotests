package tests;

import io.qameta.allure.Description;
import objects.user.User;
import objects.user.UserGenerator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.HomeSteps;
import steps.LoginSteps;
import steps.MenuSteps;
import steps.SignUpSteps;

public class SignUpTests extends BaseTest {

    @DataProvider(name = "credentials")
    public Object[][] credentials() {
        return new Object[][] {
                {UserGenerator.generateRandomUser()},
        };
    }

    @Test(dataProvider = "credentials", description = "Проверка успешной регистрации", groups = {"regress", "test-7"})
    @Description("Проверка успешной регистрации")
    public void testSuccessfulSignUp(User user) {
        //1. Нажать на кнопку регистрации
        MenuSteps.clickOnMenuItem("Sign up");
        //2. Заполнить поля и кликнуть на кнопку регистрации
        SignUpSteps.fillFieldsAndClickSignUp(user);
        //3. Нажать на кнопку логина
        MenuSteps.clickOnMenuItem("Log in");
        //4. Заполнить поля и нажать на кнопку авторизации
        LoginSteps.fillFieldsAndClickLogin(user);
        //5. Проверить что пользователь авторизовался под нужным пользователем
        HomeSteps.checkUserLoginInHeader(user);
    }
}
