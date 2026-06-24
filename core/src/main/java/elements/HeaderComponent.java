package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Selenide.$x;

public class HeaderComponent {
    private static final String MENU_ITEM_XPATH = "//div[@id='navbarExample']/ul/li[contains(.,'%s')]";

    @Step("кликнуть на пункт меню \"{menuItem}\"")
    public void clickOnMenuItem(String menuItem) {
        $x(String.format(MENU_ITEM_XPATH, menuItem)).click();
    }

    @Step("Проверить что пункт меню \"{menuItem}\" присутствует")
    public void checkMenuItemExists(String menuItem) {
        try {
            $x(String.format(MENU_ITEM_XPATH, menuItem)).shouldBe(Condition.visible);
        } catch (UIAssertionError error) {
            Assert.fail("Не найден пункт меню \"" + menuItem + "\"");
        }
    }
}
