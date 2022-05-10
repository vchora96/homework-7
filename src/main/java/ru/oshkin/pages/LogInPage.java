package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LogInPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(LogInPage.class.getName());
    private final String login = System.getProperty("login", "macorax714@idurse.com");
    private final String pass = System.getProperty("pass", "Test12345");

    @FindBy(xpath = "//input[@type='text' and @placeholder='Электронная почта']")
    private WebElement mail;

    @FindBy(xpath = "//input[@type='password' and @placeholder='Введите пароль']")
    private WebElement password;

    @FindBy(xpath = "//button[contains(text(),'Войти')]")
    private WebElement button;

    @FindBy(css = "p.header2-menu__item-text.header2-menu__item-text__username")
    private WebElement userButton;

    @FindBy(xpath = "//a[@title ='Личный кабинет']")
    private WebElement personalAccountButton;

    @FindBy(xpath = "//a[@title ='О себе']")
    private WebElement aboutUserButton;

    @FindBy(css = "jdiv.closeIcon_f9a1")
    private WebElement chatCross;


    public LogInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public PrivateDataPage logInByUser() {
        openSite();

        closeChat();

        setLogin();
        setPassword();
        autorize();

        openBlock();
        openPersonalCabinet();
        openAboutUser();

        return new PrivateDataPage(driver);
    }

    private void closeChat() {
        if (chatCross.isDisplayed()) {
            chatCross.click();
            logger.info("Закрываем всплывающее окно чата");
        }
    }

    private void setLogin() {
        mail.click();
        mail.sendKeys(login);
        logger.info("Ввели почту");
        String text = mail.getText();
        assertEquals(login, text);
    }

    private void setPassword() {
        password.click();
        password.sendKeys(pass);
        logger.info("Ввели пароль");
        String text = password.getText();
        assertEquals(pass, text);
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
