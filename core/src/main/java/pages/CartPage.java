package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage extends BasePage {

    private static final String TABLE_ELEMENTS_XPATH = "//tbody//tr";

    public SelenideElement getTableElements(int index) {
        return $$x(TABLE_ELEMENTS_XPATH).get(index);
    }

    public String getElementTitle(int index) {
        SelenideElement selenideElement = getTableElements(index);
        return selenideElement.$$x("./td").get(1).getOwnText();
    }

    public String getElementPrice(int index) {
        SelenideElement selenideElement = getTableElements(index);
        return selenideElement.$$x("./td").get(2).getOwnText();
    }

    @Step("Подождать пока не прогрузятся товары в корзине")
    public void waitUntilCartIsNotEmpty() {
        try {
            $x(TABLE_ELEMENTS_XPATH).shouldBe(Condition.visible);
        } catch (UIAssertionError error) {
            Assert.fail("Товары в корзине не прогрузились");
        }
    }

    @Step("Получить итоговую цену")
    public String getTotalPriceValue() {
        return $x("//h3[@id='totalp']").getOwnText();
    }

    @Step("Подождать пока не появится попап об успешной покупке")
    public void waitUntilSuccessfulPurchasePopupWillAppear() {
        try {
            $x("//h2[text()='Thank you for your purchase!']").shouldBe(Condition.visible);
        } catch (UIAssertionError error) {
            Assert.fail("Попап об успешной покупке не появился");
        }
    }

    public String getSuccessfulPurchaseInfo() {
        return $x("//div[@data-has-done-function]//p").getOwnText();
    }
}
