package steps.ui;

import com.google.gson.Gson;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import objects.CategoryItemUI;
import org.testng.asserts.SoftAssert;
import pages.CategoryItemPage;
import utils.AttachmentUtils;

public class CategoryItemSteps {

    static CategoryItemPage categoryItemPage = new CategoryItemPage();

    @Step("Проверить данные в карточке товара")
    public static void checkCategoryItem(CategoryItemUI categoryItemUI) {
        categoryItemPage.waitAddToCartButtonIsLoaded();
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        Allure.addAttachment("Данные товара", new Gson().toJson(categoryItemUI));
        Allure.addAttachment("Данные из карточки на сайте", new Gson().toJson(new CategoryItemUI(categoryItemPage.getName() , categoryItemUI.getCategory() , categoryItemPage.getPrice() ,categoryItemPage.getDescription())));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(categoryItemPage.getName(), categoryItemUI.getName(), "Ожидаемое название товара: \"" + categoryItemUI.getName() + "\" не соответствует фактическому: \"" + categoryItemPage.getName());
        softAssert.assertEquals(categoryItemPage.getPrice(), "$" + categoryItemUI.getPrice(), "Ожидаемое цена товара: \"$" + categoryItemUI.getPrice() + "\" не соответствует фактическому: \"" + categoryItemPage.getPrice());
        softAssert.assertEquals(categoryItemPage.getDescription(), categoryItemUI.getDescription(), "Ожидаемое описание товара: \"" + categoryItemUI.getDescription() + "\" не соответствует фактическому: \"" + categoryItemPage.getDescription());
        softAssert.assertAll();
    }

    @Step("Добавить товар в корзину")
    public static void addCategoryItemToCart() {
        categoryItemPage.waitAddToCartButtonIsLoaded();
        categoryItemPage.clickAddToCartButton();
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        categoryItemPage.checkMessageAndClosePopup("Product added");
    }
}
