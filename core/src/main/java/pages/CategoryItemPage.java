package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Selenide.$x;

public class CategoryItemPage extends BasePage {

    private static final String ADD_TO_CART_BUTTON_XPATH = "//a[text()='Add to cart']";

    @Step("Подождать пока не загрузится кнопка добавления товара в корзину")
    public void waitAddToCartButtonIsLoaded() {
        try {
            $x(ADD_TO_CART_BUTTON_XPATH).shouldBe(Condition.visible);
        } catch (UIAssertionError error) {
            Assert.fail("Не прогрузилась кнопка добавления товара в корзину");
        }
    }

    @Step("Кликнуть по кнопке добавления товара в корзину")
    public void clickAddToCartButton() {
        $x(ADD_TO_CART_BUTTON_XPATH).click();
    }

    @Step("Подтведить добавление товара в коризину закрыв всплывающее окно")
    public void confirmAddToCartAction() {
        Selenide.switchTo().alert().accept();
    }

    public String getPrice() {
        return $x("//h3").getOwnText();
    }

    public String getName() {
        return $x("//h2").getText();
    }

    public String getDescription() {
        return $x("//div[@id='more-information']//p").getText();
    }
}
