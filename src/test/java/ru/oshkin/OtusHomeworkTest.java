package ru.oshkin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.oshkin.pages.LogInPage;
import ru.oshkin.util.CheckHelper;
import ru.oshkin.util.WebBrowserType;
import ru.oshkin.util.WebDriverFactory;

import java.util.Locale;

public class OtusHomeworkTest {

    private WebDriver driver;

    @BeforeEach
    public void startUp() {
        String envVariable = System.getenv("type");
        //String envVariable = System.getProperty("type");
        WebBrowserType type = WebBrowserType.valueOf(envVariable.toUpperCase(Locale.ROOT));
        driver = WebDriverFactory.create(type);
    }

    @AfterEach
    public void finish() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void setPrivetDataOtusTest() {
        new LogInPage(driver)
                .logInByUser()
                .setPrivateDataInfo()
                .setMainInfo()
                .setContactInfo();

        driver.quit();
        startUp();
        new LogInPage(driver).logInByUser();

        //выполняем проверки
        CheckHelper checkHelper = new CheckHelper(driver);
        checkHelper.checkPrivateDataInfo();
        checkHelper.checkMainInfo();
        checkHelper.checkContactInfo();
    }
}
