package steps.ui;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import objects.user.UserUI;
import pages.LoginPage;
import utils.AttachmentUtils;
import static com.codeborne.selenide.Selenide.page;

public class LoginSteps {

    static LoginPage loginPage = page(LoginPage.class);

    public static void waitLoginPopupShowUp() {
        loginPage.waitLoginPopupShowUp();
    }

    @Step("Заполнить все поля авторизации")
    public static void fillFields(UserUI userUI) {
        loginPage.fillLogin(userUI.getLogin());
        loginPage.fillPassword(userUI.getPassword());
    }

    public static void clickOnLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @Step("Заполнить поля авторизации и кликнуть по кнопке авторизации")
    public static void fillFieldsAndClickLogin(UserUI userUI) {
        waitLoginPopupShowUp();
        fillFields(userUI);
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        clickOnLoginButton();
    }

    public static void checkUserLoginInHeader(UserUI userUI) {
        Allure.addAttachment("Логин пользователя", userUI.getLogin());
        loginPage.checkUserLoggedIn(userUI.getLogin());
    }
}
