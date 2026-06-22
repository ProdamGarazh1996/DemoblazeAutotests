package tests;

import objects.user.UserUI;
import objects.user.UserUIGenerator;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import steps.ui.LoginSteps;
import steps.ui.MenuSteps;

public class LoginTests extends BaseTest {

    UserUI userUI = UserUIGenerator.generateUserUIForLogIn();

    @Test(description = "Проверка успешной авторизации пользователя", groups = {"regression", "test-1"})
    @Description("Проверка успешной авторизации пользователя")
    public void testSuccessfulLogin() {
        //1. Нажать на кнопку логина
        MenuSteps.clickOnMenuItem("Log in");
        //2. Заполнить поля и нажать на кнопку авторизации
        LoginSteps.fillFieldsAndClickLogin(userUI);
        //3. Проверить что пользователь авторизовался под нужным пользователем
        LoginSteps.checkUserLoginInHeader(userUI);
    }

    @Test(groups = {"smoke"})
    @Description("uiTest2")
    public void uiTest2() {
        System.out.println("uiTest2");
    }
}