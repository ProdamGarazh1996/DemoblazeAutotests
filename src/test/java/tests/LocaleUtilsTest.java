package tests;

import org.testng.annotations.Test;
import io.qameta.allure.*;

public class LocaleUtilsTest extends BaseTest {

    @Test(groups = {"regression"})
    @Description("testParseLocale")
    public void testParseLocale() {

        System.out.println("testParseLocale");
    }

    @Test(groups = {"regression2"})
    @Description("testParseLocale2")
    public void testParseLocale2() {
        System.out.println("testParseLocale2");
    }
}