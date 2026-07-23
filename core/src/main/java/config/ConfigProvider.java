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

    Config config = readConfig();
    // URL для используемых стендов
    String URL = config.getString("url");
    String API_URL = config.getString("api_url");

    //Директория где хранятся отчеты
    String REPORTS_FOLDER = config.getString("reportsFolder");

    // Настройки Selenide
    String BROWSER = System.getProperty("browser") != null
            ? System.getProperty("browser")
            : config.getString("browser");
    Boolean HEADLESS = Boolean.parseBoolean(config.getString("headless"));
    String BROWSER_SIZE = config.getString("browserSize");
    Long SELENIDE_TIMEOUT = Long.parseLong(config.getString("timeout"));

    // Данные для авторизации
    String LOGIN = System.getProperty("login") != null
            ? System.getProperty("login")
            : config.getString("usersParams.testUser.login");
    String PASS = System.getProperty("pass") != null
            ? System.getProperty("pass")
            : config.getString("usersParams.testUser.pass");
}
