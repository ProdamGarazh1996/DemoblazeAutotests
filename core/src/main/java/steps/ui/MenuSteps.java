package steps.ui;

import io.qameta.allure.Step;
import pages.HomePage;
import utils.AttachmentUtils;
import static com.codeborne.selenide.Selenide.page;

public class MenuSteps {
    static HomePage homePage = page(HomePage.class);

    @Step("кликнуть на пункт меню \"{menuItem}\"")
    public static void clickOnMenuItem(String menuItem) {
        homePage.checkMenuItemExists(menuItem);
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        homePage.clickOnMenuItem(menuItem);
    }
}
