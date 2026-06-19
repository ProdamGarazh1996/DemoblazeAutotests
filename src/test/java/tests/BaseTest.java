package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigProvider;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {
//    private LoginPage loginPage;
//    public MainPage mainPage;


    /**
     * Инициализация Selenide с настройками
     */
    @Step("Настройка Selenide")
    public void configureSelenide(){
        Configuration.browser = ConfigProvider.BROWSER;
        Configuration.baseUrl = ConfigProvider.URL;
        Configuration.browserSize = ConfigProvider.BROWSER_SIZE;
        Configuration.headless = ConfigProvider.HEADLESS;
        Configuration.reportsFolder = ConfigProvider.REPORTS_FOLDER;
        Configuration.webdriverLogsEnabled = false;
        Configuration.timeout=ConfigProvider.SELENIDE_TIMEOUT;
    }
    @BeforeTest(alwaysRun = true)
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeMethod(alwaysRun = true)
    @Step("Открыть тестовый стенд")
    public void performSetupAndNavigate() {
        configureSelenide();
        System.out.println("User: " + ConfigProvider.LOGIN + "  password: " + ConfigProvider.PASS);
        Selenide.open(ConfigProvider.URL);
//        loginPage = new LoginPage();
//        mainPage = loginPage.logIn(ConfigProvider.LOGIN, ConfigProvider.PASS);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
