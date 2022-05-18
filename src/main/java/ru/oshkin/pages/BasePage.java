package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class.getName());
    protected final Wait<WebDriver> wait;

    protected WebDriver driver;
    //получаем свойство, определенное JVM (либо сам JVM, либо любые опции -D)
    private final String baseUrl = System.getProperty("url", "https://otus.ru");

    @FindBy(xpath = "//button[@data-modal-id='new-log-reg']")
    private WebElement logPage;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(10).toMillis(),
                Duration.ofSeconds(5).toMillis());
    }

    protected void openSite() {
        driver.get(baseUrl);
        logger.info("Перешли по ссылке");
        logPage.click();
    }
}
