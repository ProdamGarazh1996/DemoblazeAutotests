package steps.ui;

import com.google.gson.Gson;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import objects.CategoryItemUI;
import objects.contact.ContactInfoUI;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import utils.AttachmentUtils;
import utils.DateUtils;

public class CartSteps {
    static CartPage cartPage = new CartPage();

    @Step("Проверка данных добавленного товара")
    public static void checkCartItem(int index, CategoryItemUI categoryItemUI) {
        cartPage.waitUntilCartIsNotEmpty();
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        Allure.addAttachment("Данные товара", new Gson().toJson(categoryItemUI));
        Allure.addAttachment("Данные из корзины на сайте", new Gson().toJson(new CategoryItemUI(cartPage.getElementTitle(index) , categoryItemUI.getCategory() , cartPage.getElementPrice(index) ,categoryItemUI.getDescription())));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getElementTitle(index), categoryItemUI.getName(), "Ожидаемое название товара: \"" + categoryItemUI.getName() + "\" не соответствует фактическому: \"" + cartPage.getElementTitle(index));
        softAssert.assertEquals(cartPage.getElementPrice(index), categoryItemUI.getPrice(), "Ожидаемое цена товара: \"$" + categoryItemUI.getPrice() + "\" не соответствует фактическому: \"" + cartPage.getElementPrice(index));
        softAssert.assertAll();
    }

    @Step("Проверка итоговой цены")
    public static void checkCartTotal(String expectedTotal) {
        cartPage.waitUntilCartIsNotEmpty();
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getTotalPriceValue(), expectedTotal, "Ожидаемое значение итоговой цены товаров: \"" + expectedTotal + "\" не соответствует фактическому: \"" + cartPage.getTotalPriceValue());
        softAssert.assertAll();
    }

    public static void clickOnPlaceOrderButton() {
        cartPage.clickOnButton("Place Order");
    }

    @Step("Заполнить все поля контактной информации")
    public static void fillAllContactInfoFields(ContactInfoUI contactInfoUI) {
        cartPage.fillInputField("name", contactInfoUI.getName());
        cartPage.fillInputField("country", contactInfoUI.getCountry());
        cartPage.fillInputField("city", contactInfoUI.getCity());
        cartPage.fillInputField("card", contactInfoUI.getCreditCard());
        cartPage.fillInputField("month", contactInfoUI.getMonth());
        cartPage.fillInputField("year", contactInfoUI.getYear());
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
    }

    @Step("Проверить информацию об успешной покупке")
    public static void checkSuccessfulPurchaseInfo(ContactInfoUI contactInfoUI, String expectedAmount) {
        cartPage.waitUntilSuccessfulPurchasePopupWillAppear();
        String purchaseInfo = cartPage.getSuccessfulPurchaseInfo();
        purchaseInfo = purchaseInfo.substring(purchaseInfo.indexOf("\n") + 1);
        String amount = purchaseInfo.substring(purchaseInfo.indexOf("Amount:") + 8, purchaseInfo.indexOf("\n"));
        purchaseInfo = purchaseInfo.substring(purchaseInfo.indexOf("\n") + 1);
        String cardNumber = purchaseInfo.substring(purchaseInfo.indexOf("Card Number:") + 13, purchaseInfo.indexOf("\n"));
        purchaseInfo = purchaseInfo.substring(purchaseInfo.indexOf("\n") + 1);
        String name = purchaseInfo.substring(purchaseInfo.indexOf("Name:") + 6, purchaseInfo.indexOf("\n"));
        purchaseInfo = purchaseInfo.substring(purchaseInfo.indexOf("\n") + 1);
        String dateString = purchaseInfo.substring(purchaseInfo.indexOf("Date:") + 7);
        String expectedDate = DateUtils.getCurrentLocalDateTime().getYear() + "/" + contactInfoUI.getMonth() + "/" + contactInfoUI.getYear();
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        Allure.addAttachment("Ожидаемая контактная информация", new Gson().toJson(contactInfoUI));
        Allure.addAttachment("Данные в попапе об успешной оплате", "{\"amount\": " + amount + ", \"cardNumber\": " + cardNumber + ", \"name\": " + name + ", \"dateString\": " + dateString + "}");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(amount, expectedAmount + " USD", "Ожидаемая общая сумма: \"" + expectedAmount + " USD\" не соответствует фактическому: \"" + amount);
        softAssert.assertEquals(cardNumber, contactInfoUI.getCreditCard(), "Ожидаемое значение кредитной карты: \"" + cardNumber + "\" не соответствует фактическому: \"" + contactInfoUI.getCreditCard());
        softAssert.assertEquals(name, contactInfoUI.getName(), "Ожидаемое значение имени: \"" + name + "\" не соответствует фактическому: \"" + contactInfoUI.getName());
        softAssert.assertAll();
    }

    public static void clickOnPurchaseButton() {
        cartPage.clickOnButton("Purchase");
    }
}
