package steps.ui;

import io.qameta.allure.Step;
import objects.user.UserUI;
import pages.SignUpPage;
import utils.AttachmentUtils;
import static com.codeborne.selenide.Selenide.page;

public class SignUpSteps {
    static SignUpPage signUpPage = page(SignUpPage.class);

    public static void waitLoginPopupShowUp() {
        signUpPage.waitSignUpPopupShowUp();
    }

    @Step("Заполнить все поля регистрации")
    public static void fillFields(UserUI userUI) {
        signUpPage.fillInputField("sign-username", userUI.getLogin());
        signUpPage.fillInputField("sign-password", userUI.getPassword());
    }

    public static void clickOnSignUpButton() {
        signUpPage.clickOnButton("Sign up");
    }

    @Step("Заполнить поля регистрации и кликнуть по кнопке регистрации")
    public static void fillFieldsAndClickSignUp(UserUI userUI) {
        waitLoginPopupShowUp();
        fillFields(userUI);
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        clickOnSignUpButton();
        signUpPage.checkMessageAndClosePopup("Sign up successful.");
    }
}
