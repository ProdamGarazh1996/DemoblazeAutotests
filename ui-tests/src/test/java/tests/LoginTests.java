package tests;

import objects.user.User;
import objects.user.UserGenerator;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import steps.HomeSteps;
import steps.LoginSteps;
import steps.MenuSteps;

public class LoginTests extends BaseTest {

    User user = UserGenerator.generateUserUIForLogIn();

    @Test(description = "Проверка успешной авторизации пользователя", groups = {"regress", "test-1"})
    @Description("Проверка успешной авторизации пользователя")
    public void testSuccessfulLogin() {
        //1. Нажать на кнопку логина
        MenuSteps.clickOnMenuItem("Log in");
        //2. Заполнить поля и нажать на кнопку авторизации
        LoginSteps.fillFieldsAndClickLogin(user);
        //3. Проверить что пользователь авторизовался под нужным пользователем
        HomeSteps.checkUserLoginInHeader(user);
    }

    @Test(description = "Проверка успешного выхода пользователя", groups = {"regress", "test-3"})
    @Description("Проверка успешного выхода пользователя")
    public void testSuccessfulLogout() {
        //1. Нажать на кнопку логина
        MenuSteps.clickOnMenuItem("Log in");
        //2. Заполнить поля и нажать на кнопку авторизации
        LoginSteps.fillFieldsAndClickLogin(user);
        //3. Проверить что пользователь авторизовался под нужным пользователем
        HomeSteps.checkUserLoginInHeader(user);
        //4. Выйти из аккаунта
        HomeSteps.logout();
        //5. Проверить, что выход из аккаунта произошел
        HomeSteps.checkMenuItemExists("Log in");
    }
}