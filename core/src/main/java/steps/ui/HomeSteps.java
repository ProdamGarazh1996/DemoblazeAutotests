package steps.ui;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import objects.CategoryItemUI;
import objects.user.UserUI;
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


    @Step("Осуществить основные шаги по навигации в карточку нужного товара")
    public static void navigateToCategoryItem(CategoryItemUI categoryItemUI) {
        homePage.clickOnCategory(categoryItemUI.getCategory());
        homePage.checkItemInCategoryExists(categoryItemUI.getName());
        homePage.clickOnItem(categoryItemUI.getName());
    }

    public static void checkUserLoginInHeader(UserUI userUI) {
        Allure.addAttachment("Логин пользователя", userUI.getLogin());
        homePage.checkUserLoggedIn(userUI.getLogin());
    }

    public static void logout() {
        homePage.clickOnMenuItem("Log out");
    }

    public static void checkMenuItemExists(String menuItem) {
        homePage.checkMenuItemExists(menuItem);
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
    }
}
