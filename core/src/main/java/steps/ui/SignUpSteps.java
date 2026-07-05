package steps.ui;

import io.qameta.allure.Step;
import objects.user.User;
import pages.SignUpPage;
import utils.AttachmentUtils;
import static com.codeborne.selenide.Selenide.page;

public class SignUpSteps {
    static SignUpPage signUpPage = page(SignUpPage.class);

    public static void waitLoginPopupShowUp() {
        signUpPage.waitSignUpPopupShowUp();
    }

    @Step("Заполнить все поля регистрации")
    public static void fillFields(User user) {
        signUpPage.fillInputField("sign-username", user.getUsername());
        signUpPage.fillInputField("sign-password", user.getPassword());
    }

    public static void clickOnSignUpButton() {
        signUpPage.clickOnButton("Sign up");
    }

    @Step("Заполнить поля регистрации и кликнуть по кнопке регистрации")
    public static void fillFieldsAndClickSignUp(User user) {
        waitLoginPopupShowUp();
        fillFields(user);
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        clickOnSignUpButton();
        signUpPage.checkMessageAndClosePopup("Sign up successful.");
    }
}
