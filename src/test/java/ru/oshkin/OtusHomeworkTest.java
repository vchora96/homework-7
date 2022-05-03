package ru.oshkin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.oshkin.pages.LogInPage;
import ru.oshkin.util.CheckHelper;
import ru.oshkin.util.WebBrowserType;
import ru.oshkin.util.WebDriverFactory;

import java.util.Locale;

public class OtusHomeworkTest {

    private WebDriver driver;
    private WebBrowserType type;

    @BeforeEach
    public void startUp() {
        String envVariable = System.getProperty("browser");
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
        driver = WebDriverFactory.create(type);

        new LogInPage(driver)
                .logInByUser()
                .setPrivateDataInfo()
                .setMainInfo()
                .setContactInfo();

        driver.quit();
        driver = WebDriverFactory.create(type);
        new LogInPage(driver).logInByUser();

        //выполняем проверки
        CheckHelper checkHelper = new CheckHelper(driver);
        checkHelper.checkPrivateDataInfo();
        checkHelper.checkMainInfo();
        checkHelper.checkContactInfo();
    }

    @Test
    public void setPrivetDataOtusWithOptionsTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = WebDriverFactory.create(type, options);

        new LogInPage(driver)
                .logInByUser()
                .setPrivateDataInfo()
                .setMainInfo()
                .setContactInfo();

        driver.quit();
        driver = WebDriverFactory.create(type, options);
        new LogInPage(driver).logInByUser();

        //выполняем проверки
        CheckHelper checkHelper = new CheckHelper(driver);
        checkHelper.checkPrivateDataInfo();
        checkHelper.checkMainInfo();
        checkHelper.checkContactInfo();
    }
}
