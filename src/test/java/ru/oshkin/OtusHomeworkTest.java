package ru.oshkin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.oshkin.pages.LogInPage;
import ru.oshkin.util.Contact;
import ru.oshkin.util.WebBrowserType;
import ru.oshkin.factory.WebDriverFactory;
import ru.oshkin.util.constants.City;
import ru.oshkin.util.constants.LanguageLevel;

import java.util.ArrayList;
import java.util.Locale;

public class OtusHomeworkTest {

    private WebDriver driver;
    private WebBrowserType type;

    public final String NAME = "Жора";
    public final String BLOG_NAME = "Жора";
    public final String LATIN_NAME = "George";

    public final String SECOND_NAME = "Иванов";
    public final String LATIN_SECOND_NAME = "Ivanov";

    public final String DATE_OF_BIRTH = "13.11.2001";

    private final String login = System.getProperty("login", "macorax714@idurse.com");
    private final String pass = System.getProperty("pass", "********"); //пароль от тестовой УЗ

    public static final String PHONE_NUMBER = "+7 966 666-66-66";
    public static final String SKYPE_LOGIN = "SKYPE-TEST-LOGIN";

    @BeforeEach
    public void startUp() {
        String envVariable = System.getProperty("browser", "MOZILLA");
        this.type = WebBrowserType.valueOf(envVariable.toUpperCase(Locale.ROOT));
    }

    @AfterEach
    public void finish() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void setPrivetDataOtusTest() {
        driver = WebDriverFactory.create(type, null);

        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Skype", SKYPE_LOGIN));
        contacts.add(new Contact("Viber", PHONE_NUMBER));

        //Заполняем страницу данными
        new LogInPage(driver)
                .logInByUser(this.login, this.pass)
                .setPersonalDataInfo(NAME, BLOG_NAME, LATIN_NAME, SECOND_NAME, LATIN_SECOND_NAME, DATE_OF_BIRTH)
                .setMainInfo(City.ANGREN, LanguageLevel.Beginner)
                .setContactInfo(PHONE_NUMBER, contacts);

        driver.close();
        driver = WebDriverFactory.create(type, null);
        // проверяем заполненность страницы
        checkData();

    }

    @Test
    public void setPrivetDataOtusWithOptionsTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = WebDriverFactory.create(type, options);

        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Skype", SKYPE_LOGIN));
        contacts.add(new Contact("Viber", PHONE_NUMBER));

        //Заполняем страницу данными
        new LogInPage(driver)
                .logInByUser(this.login, this.pass)
                .setPersonalDataInfo(NAME, BLOG_NAME, LATIN_NAME, SECOND_NAME, LATIN_SECOND_NAME, DATE_OF_BIRTH)
                .setMainInfo(City.ANGREN, LanguageLevel.Beginner)
                .setContactInfo(PHONE_NUMBER, contacts);

        driver.close();
        driver = WebDriverFactory.create(type, null);
        //проверяем заполненность страницы
        checkData();
    }

    private void checkData() {
        new LogInPage(driver)
                .logInByUser(this.login, this.pass)
                .checkPrivateDataInfo()
                .checkMainInfo()
                .checkContactInfo();
    }
}
