package objects.cart;

import io.qameta.allure.Step;
import java.util.UUID;

public class CartGenerator {
    @Step("Сгенерировать объект класса CartApiRequest")
    public static CartApiRequest generateCartApiRequest(String cookie, String productId) {
        return new CartApiRequest(
                UUID.randomUUID().toString(),
                cookie,
                productId,
                true
        );
    }
}
