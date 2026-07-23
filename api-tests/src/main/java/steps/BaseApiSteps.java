package steps;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.Assert;

public class BaseApiSteps {

    @Step("Проверить код статуса")
    public static void checkStatusCode(int expectedStatusCode, int actualStatusCode) {
        Allure.addAttachment("Фактическое значение кода статуса", Integer.toString(expectedStatusCode));
        Allure.addAttachment("Ожидаемое значение кода статуса", Integer.toString(actualStatusCode));
        Assert.assertEquals(actualStatusCode, expectedStatusCode, "Ожидаемое значение кода статуса: " + actualStatusCode + " не соответствует фактическому значению кода статуса: " + expectedStatusCode);
    }
}
