package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import steps.ui.HomeSteps;

public class CategoryTests extends BaseTest {

    @Test(description = "Проверка категорий сайта", groups = {"smoke", "test-2"})
    @Description("Проверка категорий сайта")
    public void testCategories() {
        //1. Проверить все катагории на главной странице сайта
        HomeSteps.openAllCategoriesAndCheckItems();
    }
}
