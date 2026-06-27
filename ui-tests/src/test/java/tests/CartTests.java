package tests;

import io.qameta.allure.Description;
import objects.CategoryItemUI;
import objects.contact.ContactInfoUI;
import objects.contact.ContactInfoUIGenerator;
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
                {new CategoryItemUI("Sony vaio i5" , "Laptops", "790", "Sony is so confident that the VAIO S is a superior ultraportable laptop that the company proudly compares the notebook to Apple's 13-inch MacBook Pro. And in a lot of ways this notebook is better, thanks to a lighter weight.")}
        };
    }

    @Test(dataProvider = "categoryItemInfo", description = "Проверка оформления товара в маркетплейсе", groups = {"regression", "test-5"})
    @Description("Проверка оформления товара в маркетплейсе")
    public void testSuccessfullyCheckoutItem(CategoryItemUI categoryItemUI) {
        ContactInfoUI contactInfoUI = ContactInfoUIGenerator.generateContactInfoUI();
        //1. Открыть нужный товар
        HomeSteps.navigateToCategoryItem(categoryItemUI);
        //2. Добавить товар в корзину
        CategoryItemSteps.addCategoryItemToCart();
        //3. Осуществить навигацию в корзину
        MenuSteps.clickOnMenuItem("Cart");
        //4. Проверить данные товара в корзине
        CartSteps.checkCartItem(0, categoryItemUI);
        //5. Нажать на кнопку "Place Order"
        CartSteps.clickOnPlaceOrderButton();
        //6. Заполнить поля контактной информации
        CartSteps.fillAllContactInfoFields(contactInfoUI);
        //7. Нажать на кнопку покупки
        CartSteps.clickOnPurchaseButton();
        //8. Проверить информацию в попапе об успешной покупке
        CartSteps.checkSuccessfulPurchaseInfo(contactInfoUI, categoryItemUI.getPrice());
    }
}
