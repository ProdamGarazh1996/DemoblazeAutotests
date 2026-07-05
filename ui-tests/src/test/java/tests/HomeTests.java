package tests;

import io.qameta.allure.Description;
import objects.contact.ContactInfoGenerator;
import org.testng.annotations.Test;
import steps.ui.HomeSteps;
import steps.ui.MenuSteps;

public class HomeTests extends BaseTest {
    @Test(description = "Проверка заполнения контакта", groups = {"regress", "test-6"})
    @Description("Проверка заполнения контакта")
    public void testContactForm() {
        //1. Нажать на кнопку "Contact"
        MenuSteps.clickOnMenuItem("Contact");
        //2. Заполнить поля контакта и нажать на отправку
        HomeSteps.fillContactFormAndClickSendMessage(ContactInfoGenerator.generateContactInfo());
    }
}
