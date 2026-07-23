package steps;

import com.google.gson.Gson;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import objects.category.CategoryItem;
import objects.contact.ContactInfo;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import utils.AttachmentUtils;

public class CartSteps {
    static CartPage cartPage = new CartPage();

    @Step("Проверка данных добавленного товара")
    public static void checkCartItem(int index, CategoryItem categoryItem) {
        cartPage.waitUntilCartIsNotEmpty();
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        Allure.addAttachment("Данные товара", new Gson().toJson(categoryItem));
        Allure.addAttachment("Данные из корзины на сайте", new Gson().toJson(new CategoryItem(cartPage.getElementTitle(index) , categoryItem.getCat() , 1, "", Double.parseDouble(cartPage.getElementPrice(index)), categoryItem.getDesc())));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getElementTitle(index), categoryItem.getTitle(), "Ожидаемое название товара: \"" + categoryItem.getTitle() + "\" не соответствует фактическому: \"" + cartPage.getElementTitle(index));
        softAssert.assertEquals(Double.parseDouble(cartPage.getElementPrice(index)), categoryItem.getPrice(), "Ожидаемое цена товара: \"$" + categoryItem.getPrice() + "\" не соответствует фактическому: \"" + cartPage.getElementPrice(index));
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
    public static void fillAllContactInfoFields(ContactInfo contactInfo) {
        cartPage.fillInputField("name", contactInfo.getName());
        cartPage.fillInputField("country", contactInfo.getCountry());
        cartPage.fillInputField("city", contactInfo.getCity());
        cartPage.fillInputField("card", contactInfo.getCreditCard());
        cartPage.fillInputField("month", contactInfo.getMonth());
        cartPage.fillInputField("year", contactInfo.getYear());
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
    }

    @Step("Проверить информацию об успешной покупке")
    public static void checkSuccessfulPurchaseInfo(ContactInfo contactInfo, Double expectedAmount) {
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
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        Allure.addAttachment("Ожидаемая контактная информация", new Gson().toJson(contactInfo));
        Allure.addAttachment("Данные в попапе об успешной оплате", "{\"amount\": " + amount + ", \"cardNumber\": " + cardNumber + ", \"name\": " + name + ", \"dateString\": " + dateString + "}");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(Double.parseDouble(amount.split(" ")[0]), expectedAmount, "Ожидаемая общая сумма: \"" + expectedAmount + " USD\" не соответствует фактическому: \"" + amount);
        softAssert.assertEquals(cardNumber, contactInfo.getCreditCard(), "Ожидаемое значение кредитной карты: \"" + cardNumber + "\" не соответствует фактическому: \"" + contactInfo.getCreditCard());
        softAssert.assertEquals(name, contactInfo.getName(), "Ожидаемое значение имени: \"" + name + "\" не соответствует фактическому: \"" + contactInfo.getName());
        softAssert.assertAll();
    }

    public static void clickOnPurchaseButton() {
        cartPage.clickOnButton("Purchase");
    }
}
