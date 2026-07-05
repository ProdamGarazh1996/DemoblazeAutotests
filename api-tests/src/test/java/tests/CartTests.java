package tests;

import api.baseResponse.BaseResponse;
import api.baseResponse.BaseResponseWithDataArray;
import api.baseResponse.EmptyBaseResponse;
import io.qameta.allure.Description;
import objects.cart.CartApiRequest;
import objects.cart.CartGenerator;
import objects.category.CategoryItem;
import objects.category.CategoryItemGenerator;
import objects.user.User;
import objects.user.UserGenerator;
import org.testng.annotations.Test;
import steps.api.CartApiSteps;
import steps.api.LoginApiSteps;
import steps.api.SignupApiSteps;
import utils.HttpStatusCode;
import java.util.ArrayList;
import java.util.List;

public class CartTests {
    @Test(description = "Проверка добавления товара в корзину", groups = {"crit_regress", "test-12"})
    @Description("Проверка добавления товара в корзину")
    public void checkCartAddTest() {
        //Сгенерировать данные телефона
        CategoryItem phoneItem = CategoryItemGenerator.generateExpectedPhoneCategoryItem();
        //Сгенерировать нового пользователя
        User user = UserGenerator.generateRandomUser();
        //1. Зарегистрировать нового пользователя
        SignupApiSteps.signup(user);
        //2. Авторизоваться под новым пользователем
        BaseResponse<User> baseResponse = LoginApiSteps.login(user);
        //3. Сгенерировать данные для запроса на добавление товара в корзину
        CartApiRequest cartApiRequest = CartGenerator.generateCartApiRequest(
                LoginApiSteps.extractAuthToken(baseResponse.getMessage()),
                phoneItem.getId().toString()
        );
        //4. Добавить товар в корзину через АПИ
        EmptyBaseResponse emptyBaseResponse = CartApiSteps.addItemToCart(cartApiRequest);
        //5. Проверить статус код от АПИ метода addtoCart
        CartApiSteps.checkStatusCode(HttpStatusCode.OK.getCode(), emptyBaseResponse.getCode());
        //6. Получение данных из корзины через АПИ запрос
        BaseResponseWithDataArray<CartApiRequest> response = CartApiSteps.getItemsFromCart(cartApiRequest);
        //7. Проверка ответа метода viewcart полученного из АПИ
        cartApiRequest.setCookie(user.getUsername());
        CartApiSteps.checkViewCartResponse(response, List.of(cartApiRequest));
    }

    @Test(description = "Проверка удаления товара из корзины", groups = {"crit_regress", "test-13"})
    @Description("Проверка удаления товара из корзины")
    public void checkCartDeleteTest() {
        //Сгенерировать данные телефона
        CategoryItem phoneItem = CategoryItemGenerator.generateExpectedPhoneCategoryItem();
        //Сгенерировать нового пользователя
        User user = UserGenerator.generateRandomUser();
        //1. Зарегистрировать нового пользователя
        SignupApiSteps.signup(user);
        //2. Авторизоваться под новым пользователем
        BaseResponse<User> baseResponse = LoginApiSteps.login(user);
        //3. Сгенерировать данные для запроса на добавление товара в корзину
        CartApiRequest cartApiRequest = CartGenerator.generateCartApiRequest(
                LoginApiSteps.extractAuthToken(baseResponse.getMessage()),
                phoneItem.getId().toString()
        );
        //4. Добавить товар в корзину через АПИ
        CartApiSteps.addItemToCart(cartApiRequest);
        //5. Удалить товар из корзины
        CartApiSteps.deleteItemInCart(cartApiRequest);
        //6. Получение данных из корзины через АПИ запрос
        BaseResponseWithDataArray<CartApiRequest> response = CartApiSteps.getItemsFromCart(cartApiRequest);
        //7. Проверить что корзина пустая
        CartApiSteps.checkViewCartResponse(response, new ArrayList<>());
    }
}
