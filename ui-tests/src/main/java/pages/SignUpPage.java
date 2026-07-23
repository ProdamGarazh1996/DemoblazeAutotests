package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.UIAssertionError;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.$x;

public class SignUpPage extends BasePage {
    public void waitSignUpPopupShowUp() {
        try {
            $x("//div[@id='signInModal']").shouldHave(not(Condition.attribute("aria-hidden")));
        } catch (UIAssertionError error) {
            Assert.fail("Модальное окно с регистрацией не открылось");
        }
    }
}
