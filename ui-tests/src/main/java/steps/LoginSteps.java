package steps;

import io.qameta.allure.Step;
import objects.user.User;
import pages.LoginPage;
import utils.AttachmentUtils;
import static com.codeborne.selenide.Selenide.page;

public class LoginSteps {

    static LoginPage loginPage = page(LoginPage.class);

    public static void waitLoginPopupShowUp() {
        loginPage.waitLoginPopupShowUp();
    }

    @Step("Заполнить все поля авторизации")
    public static void fillFields(User user) {
        loginPage.fillInputField("loginusername", user.getUsername());
        loginPage.fillInputField("loginpassword", user.getPassword());
    }

    public static void clickOnLoginButton() {
        loginPage.clickOnButton("Log in");
    }

    @Step("Заполнить поля авторизации и кликнуть по кнопке авторизации")
    public static void fillFieldsAndClickLogin(User user) {
        waitLoginPopupShowUp();
        fillFields(user);
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        clickOnLoginButton();
    }
}
