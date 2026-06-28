package tests;

import io.qameta.allure.Description;
import objects.user.UserUI;
import objects.user.UserUIGenerator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.ui.HomeSteps;
import steps.ui.LoginSteps;
import steps.ui.MenuSteps;
import steps.ui.SignUpSteps;

public class SignUpTests extends BaseTest {

    @DataProvider(name = "credentials")
    public Object[][] credentials() {
        return new Object[][] {
                {UserUIGenerator.generateRandomUserUI()},
        };
    }

    @Test(dataProvider = "credentials", description = "Проверка успешной регистрации", groups = {"test-7"})
    @Description("Проверка успешной регистрации")
    public void testSuccessfulSignUp(UserUI userUI) {
        //1. Нажать на кнопку регистрации
        MenuSteps.clickOnMenuItem("Sign up");
        //2. Заполнить поля и кликнуть на кнопку регистрации
        SignUpSteps.fillFieldsAndClickSignUp(userUI);
        //3. Нажать на кнопку логина
        MenuSteps.clickOnMenuItem("Log in");
        //4. Заполнить поля и нажать на кнопку авторизации
        LoginSteps.fillFieldsAndClickLogin(userUI);
        //5. Проверить что пользователь авторизовался под нужным пользователем
        HomeSteps.checkUserLoginInHeader(userUI);
    }
}
