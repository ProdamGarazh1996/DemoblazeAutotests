package tests;

import objects.user.UserUI;
import objects.user.UserUIGenerator;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import steps.ui.HomeSteps;
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
        HomeSteps.checkUserLoginInHeader(userUI);
    }

    @Test(description = "Проверка успешного выхода пользователя", groups = {"regression", "test-3"})
    @Description("Проверка успешного выхода пользователя")
    public void testSuccessfulLogout() {
        //1. Нажать на кнопку логина
        MenuSteps.clickOnMenuItem("Log in");
        //2. Заполнить поля и нажать на кнопку авторизации
        LoginSteps.fillFieldsAndClickLogin(userUI);
        //3. Проверить что пользователь авторизовался под нужным пользователем
        HomeSteps.checkUserLoginInHeader(userUI);
        //4. Выйти из аккаунта
        HomeSteps.logout();
        //5. Проверить, что выход из аккаунта произошел
        HomeSteps.checkMenuItemExists("Log in");
    }
}