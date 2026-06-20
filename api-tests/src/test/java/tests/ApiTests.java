package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class ApiTests {
    @Test(groups = {"regression"})
    @Description("apiTest")
    public void apiTest() {
        System.out.println("apiTest");
    }

    @Test(groups = {"smoke"})
    @Description("apiTest2")
    public void apiTest2() {
        System.out.println("apiTest2");
    }
}
