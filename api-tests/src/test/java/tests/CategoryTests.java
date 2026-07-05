package tests;

import api.baseResponse.BaseResponse;
import api.baseResponse.BaseResponseWithDataArray;
import io.qameta.allure.Description;
import objects.category.CategoryItem;
import objects.category.CategoryItemGenerator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.api.CategoryApiSteps;
import java.util.ArrayList;

public class CategoryTests {

    @DataProvider(name = "categoryInfo")
    public Object[][] categoryInfo() {
        return new Object[][] {
                { CategoryItemGenerator.generateExpectedPhoneCategoryItems(), "phone" },
                { CategoryItemGenerator.generateExpectedNotebookCategoryItems(), "notebook" },
                { CategoryItemGenerator.generateExpectedMonitorCategoryItems(), "monitor" }
        };
    }

    @DataProvider(name = "categoryItemInfo")
    public Object[][] categoryItemInfo() {
        return new Object[][] {
                { CategoryItemGenerator.generateExpectedPhoneCategoryItem()},
                { CategoryItemGenerator.generateExpectedNotebookCategoryItem()},
                { CategoryItemGenerator.generateExpectedMonitorCategoryItem()}
        };
    }

    @Test(dataProvider = "categoryInfo", description = "Проверка получения товаров из категории", groups = {"crit_regress", "test-8"})
    @Description("Проверка получения товаров из категории")
    public void checkCategoryTest(ArrayList<CategoryItem> expectedCategoryItems, String category) {
        //1. Получение товаров категории
        BaseResponseWithDataArray<CategoryItem> response = CategoryApiSteps.getCategoryItems(category);
        //2. Проверка списка товаров, полученных из АПИ
        CategoryApiSteps.checkCategoryList(expectedCategoryItems, response);
    }

    @Test(dataProvider = "categoryItemInfo", description = "Проверка получения товара из категории по его ID", groups = {"crit_regress", "test-9"})
    @Description("Проверка получения товара из категории по его ID")
    public void getCategoryItemByIdTest(CategoryItem expectedCategoryItem) {
        //1. Получение товара из категории
        BaseResponse<CategoryItem> response = CategoryApiSteps.getCategoryItemById(expectedCategoryItem.getId());
        //2. Проверка товара полученного из АПИ
        CategoryApiSteps.checkCategoryItem(expectedCategoryItem, response);
    }

    @Test(description = "Проверка пагинации", groups = {"crit_regress", "test-10"})
    @Description("Проверка пагинации")
    public void checkPaginationOfCategoryTest() {
        //1. Получение товаров категории используя пагинацию
        BaseResponseWithDataArray<CategoryItem> response = CategoryApiSteps.getCategoryItemsByPagination(1);
        //2. Проверка списка товаров, полученных из АПИ
        CategoryApiSteps.checkCategoryList(
                CategoryItemGenerator.generateExpectedCategoryItemsByPagination(),
                response
        );
    }
}
