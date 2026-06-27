package objects.contact;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import utils.DateUtils;
import java.util.Date;

public class ContactInfoUIGenerator {

    @Step("Сгенерировать объект класса UserUI из конфигов")
    public static ContactInfoUI generateContactInfoUI() {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String country = faker.country().name();
        String city = faker.country().capital();
        String creditCard = faker.finance().creditCard();
        Date randomDate = DateUtils.getRandomDate();
        String month = DateUtils.getMonthByDate(randomDate);
        return new ContactInfoUI(name, country, city, creditCard, month, Integer.toString(randomDate.getYear()), randomDate);
    }
}
