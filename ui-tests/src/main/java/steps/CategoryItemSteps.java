package steps;

import com.google.gson.Gson;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import objects.category.CategoryItem;
import org.testng.asserts.SoftAssert;
import pages.CategoryItemPage;
import utils.AttachmentUtils;

public class CategoryItemSteps {

    static CategoryItemPage categoryItemPage = new CategoryItemPage();

    @Step("Проверить данные в карточке товара")
    public static void checkCategoryItem(CategoryItem categoryItem) {
        categoryItemPage.waitAddToCartButtonIsLoaded();
        AttachmentUtils.attachScreenshotToStep();
        AttachmentUtils.attachPageSource();
        String price = categoryItemPage.getPrice();
        Allure.addAttachment("Данные товара", new Gson().toJson(categoryItem));
        Allure.addAttachment("Данные из карточки на сайте", new Gson().toJson(new CategoryItem(categoryItemPage.getName() , categoryItem.getCat(), 1, "", Double.parseDouble(price.substring(1)) ,categoryItemPage.getDescription())));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(categoryItemPage.getName(), categoryItem.getTitle(), "Ожидаемое название товара: \"" + categoryItem.getTitle() + "\" не соответствует фактическому: \"" + categoryItemPage.getName());
        softAssert.assertEquals(Double.parseDouble(price.substring(1)), categoryItem.getPrice(), "Ожидаемое цена товара: " + categoryItem.getPrice() + "\" не соответствует фактическому: \"" + categoryItemPage.getPrice());
        softAssert.assertEquals(categoryItemPage.getDescription(), categoryItem.getDesc(), "Ожидаемое описание товара: \"" + categoryItem.getDesc() + "\" не соответствует фактическому: \"" + categoryItemPage.getDescription());
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
