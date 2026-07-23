package steps;

import api.BaseApi;
import api.baseResponse.BaseResponse;
import api.baseResponse.BaseResponseWithDataArray;
import com.google.gson.Gson;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import objects.category.CategoryItem;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.HttpStatusCode;
import java.util.Arrays;
import java.util.List;
import static api.ApiEndPoints.*;

public class CategoryApiSteps extends BaseApiSteps {

    @Step("Сделать запрос на получение товаров категории \"{category}\"")
    public static BaseResponseWithDataArray<CategoryItem> getCategoryItems(String category) {
        BaseApi baseApi = new BaseApi();
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.setCat(category);
        return baseApi.getArraysPost(categoryItem, BY_CAT.getEndPoint(), CategoryItem.class);
    }

    @Step("Сделать запрос на получение товара по ID: \"{id}\"")
    public static BaseResponse<CategoryItem> getCategoryItemById(int id) {
        BaseApi baseApi = new BaseApi();
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.setId(id);
        return baseApi.getEntityPost(categoryItem, VIEW.getEndPoint(), CategoryItem.class);
    }

    @Step("Сделать запрос на получение товаров категории по ID: \"{id}\" используя пагинацию")
    public static BaseResponseWithDataArray<CategoryItem> getCategoryItemsByPagination(int id) {
        BaseApi baseApi = new BaseApi();
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.setId(id);
        return baseApi.getArraysPost(categoryItem, PAGINATION.getEndPoint(), CategoryItem.class);
    }

    @Step("Проверить товар полученный из АПИ")
    public static void checkCategoryItem(CategoryItem expectedCategoryItem, BaseResponse<CategoryItem> response) {
        Allure.addAttachment("Ожидаемые данные товара", new Gson().toJson(expectedCategoryItem));
        Allure.addAttachment("Фактические данные товара", new Gson().toJson(response.getItem()));
        checkStatusCode(HttpStatusCode.OK.getCode(), response.getCode());
        CategoryItem actualCategoryItem = response.getItem();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualCategoryItem.getTitle(), expectedCategoryItem.getTitle(), "Ожидаемое значение параметра \"title\" равное " + expectedCategoryItem.getTitle() + " не соответствует фактическому значению " + actualCategoryItem.getTitle());
        softAssert.assertEquals(actualCategoryItem.getCat(), expectedCategoryItem.getCat(), "Ожидаемое значение параметра \"cat\" равное " + expectedCategoryItem.getCat() + " не соответствует фактическому значению " + actualCategoryItem.getCat());
        softAssert.assertEquals(actualCategoryItem.getDesc(), expectedCategoryItem.getDesc(), "Ожидаемое значение параметра \"desc\" равное " + expectedCategoryItem.getDesc() + " не соответствует фактическому значению " + actualCategoryItem.getDesc());
        softAssert.assertEquals(actualCategoryItem.getImg(), expectedCategoryItem.getImg(), "Ожидаемое значение параметра \"img\" равное " + expectedCategoryItem.getImg() + " не соответствует фактическому значению " + actualCategoryItem.getImg());
        softAssert.assertEquals(actualCategoryItem.getPrice(), expectedCategoryItem.getPrice(), "Ожидаемое значение параметра \"price\" равное " + expectedCategoryItem.getPrice() + " не соответствует фактическому значению " + actualCategoryItem.getPrice());
        softAssert.assertAll();
    }

    @Step("Проверить что в ответе содержатся все товары из категории")
    public static void checkCategoryList(List<CategoryItem> expectedCategoryList, BaseResponseWithDataArray<CategoryItem> response) {
        List<CategoryItem> actualCategoryList = Arrays.asList(response.getItems());
        Allure.addAttachment("Ожидаемый список товаров", new Gson().toJson(expectedCategoryList));
        Allure.addAttachment("Фактический список товаров", new Gson().toJson(actualCategoryList));
        checkStatusCode(HttpStatusCode.OK.getCode(), response.getCode());
        for (int i=0;i<expectedCategoryList.size();i++) {
            CategoryItem categoryItem = expectedCategoryList.get(i);
            boolean flag = false;
            for (int j=0;j<actualCategoryList.size();j++) {
                CategoryItem actualCategory = actualCategoryList.get(j);
                if (actualCategory.getTitle().equals(categoryItem.getTitle())) {
                    flag = true;
                    SoftAssert softAssert = new SoftAssert();
                    softAssert.assertEquals(actualCategory.getCat(), categoryItem.getCat(), "Ожидаемое значение параметра \"cat\" равное " + categoryItem.getCat() + " не соответствует фактическому значению " + actualCategory.getCat());
                    softAssert.assertEquals(actualCategory.getDesc(), categoryItem.getDesc(), "Ожидаемое значение параметра \"desc\" равное " + categoryItem.getDesc() + " не соответствует фактическому значению " + actualCategory.getDesc());
                    softAssert.assertEquals(actualCategory.getImg(), categoryItem.getImg(), "Ожидаемое значение параметра \"img\" равное " + categoryItem.getImg() + " не соответствует фактическому значению " + actualCategory.getImg());
                    softAssert.assertEquals(actualCategory.getPrice(), categoryItem.getPrice(), "Ожидаемое значение параметра \"price\" равное " + categoryItem.getPrice() + " не соответствует фактическому значению " + actualCategory.getPrice());
                    softAssert.assertAll();
                }
            }
            Assert.assertTrue(flag, "Нужный товар \"" + categoryItem.getTitle() + "\" не найден в категории \"" + categoryItem.getCat() + "\"");
        }
    }
}
