package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Класс для получения данных из файла resources/appData.conf
 * С этими данными мы работаем в тестах
 */
public interface ConfigProvider {

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("appData.conf");
    }

    // URL для используемых стендов
    String URL = readConfig().getString("url");

    //Директория где хранятся отчеты
    String REPORTS_FOLDER = readConfig().getString("reportsFolder");

    // Настройки Selenide
    String BROWSER = System.getProperty("browser") != null
            ? System.getProperty("browser")
            : readConfig().getString("browser");
    Boolean HEADLESS = Boolean.parseBoolean(readConfig().getString("headless"));
    String BROWSER_SIZE = readConfig().getString("browserSize");
    Long SELENIDE_TIMEOUT = Long.parseLong(readConfig().getString("timeout"));

    // Данные для авторизации
    String LOGIN = System.getProperty("login") != null
            ? System.getProperty("login")
            : readConfig().getString("usersParams.testUser.login");
    String PASS = System.getProperty("pass") != null
            ? System.getProperty("pass")
            : readConfig().getString("usersParams.testUser.pass");
}
