package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogInPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(LogInPage.class.getName());
    private String login;
    private String pass;

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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private void init(String login, String pass){
        this.login = login;
        this.pass = pass;
    }

    public PersonalDataPage logInByUser(String login, String pass) {
        init(login, pass);
        openSite();

        closeChat();

        setLogin();
        setPassword();
        autorize();

        openBlock();
        openPersonalCabinet();
        openAboutUser();

        return new PersonalDataPage(driver);
    }

    private void closeChat() {
        try {
            if (chatCross.isDisplayed()) {
                chatCross.click();
                logger.info("Закрываем всплывающее окно чата");
            }
        }catch (Exception ex){
            logger.error("Не удалось закрыть чат", ex);
        }
    }

    private void setLogin() {
        mail.click();
        mail.sendKeys(login);
        logger.info("Ввели почту");
        String text = mail.getAttribute("value");
        assertEquals(login, text);
    }

    private void setPassword() {
        password.click();
        password.sendKeys(pass);
        logger.info("Ввели пароль");
        String text = password.getAttribute("value");
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
