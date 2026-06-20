package tests;

import org.testng.annotations.Test;
import io.qameta.allure.*;

public class UITests extends BaseTest {

    @Test(groups = {"regression"})
    @Description("uiTest")
    public void uiTest() {
        System.out.println("uiTest");
    }

    @Test(groups = {"smoke"})
    @Description("uiTest2")
    public void uiTest2() {
        System.out.println("uiTest2");
    }
}