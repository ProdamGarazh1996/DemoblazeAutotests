package steps;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import objects.category.CategoryItem;
import objects.contact.ContactInfo;
import objects.user.User;
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
    public static void navigateToCategoryItem(CategoryItem categoryItem) {
        homePage.clickOnCategory(categoryItem.getCat());
        homePage.checkItemInCategoryExists(categoryItem.getTitle());
        homePage.clickOnItem(categoryItem.getTitle());
    }

    public static void checkUserLoginInHeader(User user) {
        Allure.addAttachment("Логин пользователя", user.getUsername());
        homePage.checkUserLoggedIn(user.getUsername());
    }

    public static void logout() {
        homePage.clickOnMenuItem("Log out");
    }

    public static void checkMenuItemExists(String menuItem) {
        homePage.checkMenuItemExists(menuItem);
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
    }

    @Step("Заполнить форму контакта и нажать на отправку сообщения")
    public static void fillContactFormAndClickSendMessage(ContactInfo contactInfo) {
        Faker faker = new Faker();
        homePage.fillInputField("recipient-email", contactInfo.getEmail());
        homePage.fillInputField("recipient-name", contactInfo.getName());
        homePage.fillTextAreaField("message-text", faker.lorem().sentence());
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        homePage.clickOnButton("Send message");
        homePage.checkMessageAndClosePopup("Thanks for the message!!");
    }
}
