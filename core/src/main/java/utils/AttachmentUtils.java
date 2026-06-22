package utils;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AttachmentUtils {

    public static void attachScreenshotToStep() {

        // Получаем файл скриншота через Selenide
        File screenshot = Screenshots.takeScreenShotAsFile();
        if (screenshot != null) {
            try {
                // Прикрепляем файл к шагу
                Allure.addAttachment("Screenshot", "image/png", new FileInputStream(screenshot), "png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void attachPageSource() {
        String pageSource = WebDriverRunner.getWebDriver().getPageSource();
        Allure.addAttachment("Page source", "text/html", pageSource, ".html");
    }
}