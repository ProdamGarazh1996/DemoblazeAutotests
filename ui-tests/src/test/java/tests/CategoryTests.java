package tests;

import io.qameta.allure.Description;
import objects.category.CategoryItem;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.ui.CategoryItemSteps;
import steps.ui.HomeSteps;

public class CategoryTests extends BaseTest {

    @DataProvider(name = "categoryItemInfo")
    public Object[][] categoryItemInfo() {
        return new Object[][] {
                {new CategoryItem("Samsung galaxy s6", "Phones", 1, "imgs/galaxy_s6.jpg", 360.0, "The Samsung Galaxy S6 is powered by 1.5GHz octa-core Samsung Exynos 7420 processor and it comes with 3GB of RAM. The phone packs 32GB of internal storage cannot be expanded.")},
                {new CategoryItem("Sony vaio i5" , "Laptops", 8, "imgs/sony_vaio_5.jpg", 790.0, "Sony is so confident that the VAIO S is a superior ultraportable laptop that the company proudly compares the notebook to Apple's 13-inch MacBook Pro. And in a lot of ways this notebook is better, thanks to a lighter weight.")},
                {new CategoryItem("Apple monitor 24" , "Monitors", 10, "imgs/apple_cinema.jpg", 400.0, "LED Cinema Display features a 27-inch glossy LED-backlit TFT active-matrix LCD display with IPS technology and an optimum resolution of 2560x1440. It has a 178 degree horizontal and vertical viewing angle, a \"typical\" brightness of 375 cd/m2, contrast ratio of 1000:1, and a 12 ms response time.")},

        };
    }

    @Test(description = "Проверка категорий сайта", groups = {"crit_regress", "test-2"})
    @Description("Проверка категорий сайта")
    public void testCategories() {
        //1. Проверить все катагории на главной странице сайта
        HomeSteps.openAllCategoriesAndCheckItems();
    }

    @Test(dataProvider = "categoryItemInfo", description = "Проверка товара в категории", groups = {"crit_regress", "test-4"})
    @Description("Проверка товара в категории")
    public void testCategoryItem(CategoryItem categoryItem) {
        //1. Открыть нужный товар
        HomeSteps.navigateToCategoryItem(categoryItem);
        //2. Проверить данные товара
        CategoryItemSteps.checkCategoryItem(categoryItem);
    }
}
