package steps.ui;

import io.qameta.allure.Step;
import pages.HomePage;
import utils.AttachmentUtils;
import static com.codeborne.selenide.Selenide.page;

public class HomeSteps {

    static HomePage homePage = page(HomePage.class);

    @Step("Начать открывать все категории и проверять товары в этой категории")
    public static void openAllCategoriesAndCheckItems() {
        homePage.clickOnCategory("Phones");
        homePage.checkItemInCategoryExists("Samsung galaxy s6");
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        homePage.clickOnCategory("Laptops");
        homePage.checkItemInCategoryExists("Sony vaio i5");
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        homePage.clickOnCategory("Monitors");
        homePage.checkItemInCategoryExists("Apple monitor 24");
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
    }
}
