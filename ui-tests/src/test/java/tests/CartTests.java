package tests;

import io.qameta.allure.Description;
import objects.category.CategoryItem;
import objects.contact.ContactInfo;
import objects.contact.ContactInfoGenerator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.ui.CartSteps;
import steps.ui.CategoryItemSteps;
import steps.ui.HomeSteps;
import steps.ui.MenuSteps;

public class CartTests extends BaseTest {

    @DataProvider(name = "categoryItemInfo")
    public Object[][] categoryItemInfo() {
        return new Object[][] {
                {new CategoryItem("Sony vaio i5" , "Laptops", 8, "imgs/sony_vaio_5.jpg", 790.0, "Sony is so confident that the VAIO S is a superior ultraportable laptop that the company proudly compares the notebook to Apple's 13-inch MacBook Pro. And in a lot of ways this notebook is better, thanks to a lighter weight.")}
        };
    }

    @Test(dataProvider = "categoryItemInfo", description = "Проверка оформления товара в маркетплейсе", groups = {"crit_regress", "test-5"})
    @Description("Проверка оформления товара в маркетплейсе")
    public void testSuccessfullyCheckoutItem(CategoryItem categoryItem) {
        ContactInfo contactInfo = ContactInfoGenerator.generateContactInfo();
        //1. Открыть нужный товар
        HomeSteps.navigateToCategoryItem(categoryItem);
        //2. Добавить товар в корзину
        CategoryItemSteps.addCategoryItemToCart();
        //3. Осуществить навигацию в корзину
        MenuSteps.clickOnMenuItem("Cart");
        //4. Проверить данные товара в корзине
        CartSteps.checkCartItem(0, categoryItem);
        //5. Нажать на кнопку "Place Order"
        CartSteps.clickOnPlaceOrderButton();
        //6. Заполнить поля контактной информации
        CartSteps.fillAllContactInfoFields(contactInfo);
        //7. Нажать на кнопку покупки
        CartSteps.clickOnPurchaseButton();
        //8. Проверить информацию в попапе об успешной покупке
        CartSteps.checkSuccessfulPurchaseInfo(contactInfo, categoryItem.getPrice());
    }
}
