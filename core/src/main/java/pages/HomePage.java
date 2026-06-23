package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage {
    private static final String CATEGORY_BY_ID_XPATH = "//a[contains(., '%s')]";
    private static final String ITEM_BY_ID_XPATH = "//div[@class=\"card h-100\"]//a[contains(., '%s')]";

    @Step("Кликнуть на категорию с текстом \"{category}\"")
    public void clickOnCategory(String category) {
        $x(String.format(CATEGORY_BY_ID_XPATH, category)).click();
    }

    @Step("Проверить что товар с названием \"{itemName}\" в категории")
    public void checkItemInCategoryExists(String itemName) {
        try {
            $x(String.format(ITEM_BY_ID_XPATH, itemName)).shouldBe(Condition.visible);
        } catch (UIAssertionError error) {
            Assert.fail("Не найден нужный товар: \"" + itemName + "\"");
        }
    }


}
