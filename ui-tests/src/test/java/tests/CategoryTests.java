package tests;

import io.qameta.allure.Description;
import objects.CategoryItemUI;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.ui.CategoryItemSteps;
import steps.ui.HomeSteps;

public class CategoryTests extends BaseTest {

    @DataProvider(name = "categoryItemInfo")
    public Object[][] categoryItemInfo() {
        return new Object[][] {
                {new CategoryItemUI("Samsung galaxy s6", "Phones" , "360", "The Samsung Galaxy S6 is powered by 1.5GHz octa-core Samsung Exynos 7420 processor and it comes with 3GB of RAM. The phone packs 32GB of internal storage cannot be expanded.")},
                {new CategoryItemUI("Sony vaio i5" , "Laptops", "790", "Sony is so confident that the VAIO S is a superior ultraportable laptop that the company proudly compares the notebook to Apple's 13-inch MacBook Pro. And in a lot of ways this notebook is better, thanks to a lighter weight.")},
                {new CategoryItemUI("Apple monitor 24" , "Monitors", "400", "LED Cinema Display features a 27-inch glossy LED-backlit TFT active-matrix LCD display with IPS technology and an optimum resolution of 2560x1440. It has a 178 degree horizontal and vertical viewing angle, a \"typical\" brightness of 375 cd/m2, contrast ratio of 1000:1, and a 12 ms response time.")},

        };
    }

    @Test(description = "Проверка категорий сайта", groups = {"regression", "test-2"})
    @Description("Проверка категорий сайта")
    public void testCategories() {
        //1. Проверить все катагории на главной странице сайта
        HomeSteps.openAllCategoriesAndCheckItems();
    }

    @Test(dataProvider = "categoryItemInfo", description = "Проверка товара в категории", groups = {"regression", "test-4"})
    @Description("Проверка товара в категории")
    public void testCategoryItem(CategoryItemUI categoryItemUI) {
        //1. Открыть нужный товар
        HomeSteps.navigateToCategoryItem(categoryItemUI);
        //2. Проверить данные товара
        CategoryItemSteps.checkCategoryItem(categoryItemUI);
    }
}
