package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class.getName());

    protected WebDriver driver;
    //получаем свойство, определенное JVM (либо сам JVM, либо любые опции -D)
    private final String baseUrl = System.getProperty("url", "https://otus.ru");

    @FindBy(xpath = "//button[@data-modal-id='new-log-reg']")
    private WebElement logPage;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void openSite() {
        driver.get(baseUrl);
        logger.info("Перешли по ссылке");
        logPage.click();
    }
}
