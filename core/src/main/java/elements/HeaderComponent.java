package elements;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;

public class HeaderComponent {
    private static final String MENU_ITEM_XPATH = "//div[@id='navbarExample']/ul/li[contains(.,'%s')]";

    @Step("кликнуть на пункт меню \"{menuItem}\"")
    public void clickOnMenuItem(String menuItem) {
        $x(String.format(MENU_ITEM_XPATH, menuItem)).click();
    }
}
