package steps.api;

import api.BaseApi;
import api.baseResponse.BaseResponse;
import api.baseResponse.BaseResponseWithDataArray;
import api.baseResponse.EmptyBaseResponse;
import com.google.gson.Gson;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import objects.cart.CartApiRequest;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.HttpStatusCode;
import java.util.List;
import static api.ApiEndPoints.*;

public class CartApiSteps extends BaseApiSteps {
    @Step("Отправить запрос на добавление товара в корзину через АПИ запрос")
    public static EmptyBaseResponse addItemToCart(CartApiRequest cartApiRequest) {
        BaseApi baseApi = new BaseApi();
        return baseApi.getEntityWithEmptyResponse(cartApiRequest, ADDTOCART.getEndPoint());
    }

    @Step("Получение данных из корзины через АПИ запрос")
    public static BaseResponseWithDataArray<CartApiRequest> getItemsFromCart(CartApiRequest cartApiRequest) {
        BaseApi baseApi = new BaseApi();
        return baseApi.getArraysPost(cartApiRequest, VIEWCART.getEndPoint(), CartApiRequest.class);
    }

    @Step("Проверка ответа метода viewcart полученного из АПИ")
    public static void checkViewCartResponse(
            BaseResponseWithDataArray<CartApiRequest> response,
            List<CartApiRequest> expectedItems
    ) {
        CartApiRequest[] responseItems = response.getItems();
        Allure.addAttachment("Ожидаемый список товаров в корзине", new Gson().toJson(expectedItems));
        Allure.addAttachment("Фактический список товаров корзине", new Gson().toJson(responseItems));
        checkStatusCode(HttpStatusCode.OK.getCode(), response.getCode());

        if (expectedItems.isEmpty()) {
            Assert.assertEquals(responseItems.length, 0, "Корзина не пустая");
            return;
        }

        for (int i=0;i<expectedItems.size();i++) {
            CartApiRequest cartApiRequest = expectedItems.get(i);
            boolean flag = false;
            for (int j=0;j<responseItems.length;j++) {
                if (responseItems[i].getId().equals(cartApiRequest.getId())) {
                    flag = true;
                    SoftAssert softAssert = new SoftAssert();
                    softAssert.assertEquals(cartApiRequest.getId(), responseItems[i].getId(), "Ожидаемое значение параметра \"id\": " + cartApiRequest.getId() + " не соответствует фактическому значению: " + responseItems[i].getId());
                    softAssert.assertEquals(cartApiRequest.getCookie(), responseItems[i].getCookie(), "Ожидаемое значение параметра \"cookie\": " + cartApiRequest.getCookie() + " не соответствует фактическому значению: " + responseItems[i].getCookie());
                    softAssert.assertEquals(cartApiRequest.getProdId(), responseItems[i].getProdId(), "Ожидаемое значение параметра \"prod_id\": " + cartApiRequest.getProdId() + " не соответствует фактическому значению: " + responseItems[i].getProdId());
                    softAssert.assertAll();
                }
            }
            Assert.assertTrue(flag, "Нужный товар с id\"" + cartApiRequest.getId() + "\" не найден в корзине");
        }
    }

    @Step("Удалить товар из корзины")
    public static BaseResponse<CartApiRequest> deleteItemInCart(CartApiRequest cartApiRequest) {
        BaseApi baseApi = new BaseApi();
        return baseApi.getEntityPost(cartApiRequest, DELETE_ITEM.getEndPoint(), CartApiRequest.class);
    }
}
