package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LogInPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(LogInPage.class.getName());
    private final String login = System.getProperty("login");
    private final String pass = System.getProperty("pass");

    @FindBy(xpath = "//input[@type='text' and @placeholder='Электронная почта']")
    private WebElement mail;

    @FindBy(xpath = "//input[@type='password' and @placeholder='Введите пароль']")
    private WebElement password;

    @FindBy(xpath = "//button[contains(text(),'Войти')]")
    private WebElement button;

    @FindBy(xpath = "//div[@class = 'header2-menu__item-wrapper header2-menu__item-wrapper__username']")
    private WebElement userButton;

    @FindBy(xpath = "//a[@title ='Личный кабинет']")
    private WebElement personalAccountButton;

    @FindBy(xpath = "//a[@title ='О себе']")
    private WebElement aboutUserButton;

    public LogInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public PrivateDataPage logInByUser() {
        openSite();

        setLogin();
        setPassword();
        autorize();

        openBlock();
        openPersonalCabinet();
        openAboutUser();

        return new PrivateDataPage(driver);
    }

    private void setLogin() {
        mail.sendKeys(login);
        logger.info("Ввели почту");
    }

    private void setPassword() {
        password.sendKeys(pass);
        logger.info("Ввели пароль");
    }

    private void autorize() {
        button.submit();
        logger.info("Попытка авторизации");
    }

    private void openBlock() {
        userButton.click();
        logger.info("Раскрытие блока");
    }

    private void openPersonalCabinet() {
        personalAccountButton.click();
        logger.info("Открываем личный кабинет");
    }

    private void openAboutUser() {
        aboutUserButton.click();
        logger.info("Открываем информацию о себе");
    }
}
