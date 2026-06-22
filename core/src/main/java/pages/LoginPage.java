package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.UIAssertionError;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    public void waitLoginPopupShowUp() {
        try {
            $x("//div[@id='logInModal']").shouldHave(not(Condition.attribute("aria-hidden")));
        } catch (UIAssertionError error) {
            Assert.fail("Модальное окно с авторизацией не открылось");
        }
    }

    public void fillLogin(String login) {
        fillInputField("loginusername", login);
    }

    public void fillPassword(String password) {
        fillInputField("loginpassword", password);
    }


    public void clickOnLoginButton() {
        clickOnButton("Log in");
    }
}
