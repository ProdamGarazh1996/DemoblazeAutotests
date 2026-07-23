package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ex.UIAssertionError;
import elements.HeaderComponent;
import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Selenide.*;

public class BasePage {
    HeaderComponent headerComponent = page(HeaderComponent.class);
    private static final String INPUT_BY_ID_XPATH = "//input[@id='%s']";
    private static final String TEXTAREA_BY_ID_XPATH = "//textarea[@id='%s']";
    private static final String BUTTON_BY_TEXT_XPATH = "//button[text()='%s']";
    private static final String USER_LOGIN_XPATH = "//li/a[text()='Welcome %s']";

    public void clickOnMenuItem(String menuItem) {
        headerComponent.clickOnMenuItem(menuItem);
    }

    public void checkMenuItemExists(String menuItem) {
        headerComponent.checkMenuItemExists(menuItem);
    }

    @Step("Заполнить поле ввода с id \"{id}\" значением \"{value}\"")
    public void fillInputField(String id, String value) {
        $x(String.format(INPUT_BY_ID_XPATH, id)).setValue(value);
    }

    @Step("Заполнить поле textarea с id \"{id}\" значением \"{value}\"")
    public void fillTextAreaField(String id, String value) {
        $x(String.format(TEXTAREA_BY_ID_XPATH, id)).setValue(value);
    }

    @Step("Кликнуть на кнопку с текстом \"{text}\"")
    public void clickOnButton(String text) {
        $x(String.format(BUTTON_BY_TEXT_XPATH, text)).click();
    }

    @Step("Проверить, что пользователь \"{userLogin}\" вошел в систему")
    public void checkUserLoggedIn(String userLogin) {
        try {
            $x(String.format(USER_LOGIN_XPATH, userLogin)).shouldBe(Condition.visible);
        } catch (UIAssertionError error) {
            Assert.fail("Пользователь не вошел в систему");
        }
    }

    @Step("Проверить сообщение, а потом закрыть всплывающее окно")
    public void checkMessageAndClosePopup(String message) {
        Assert.assertEquals(Selenide.switchTo().alert().getText(), message);
        Selenide.switchTo().alert().accept();
    }
}
