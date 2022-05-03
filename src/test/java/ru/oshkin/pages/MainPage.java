package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.String.format;

public class MainPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(MainPage.class.getName());

    @FindBy(xpath = "//div[@class ='input input_full lk-cv-block__input lk-cv-block__input_fake " +
            "lk-cv-block__input_select-fake js-custom-select-presentation']")
    private WebElement countryList;

    @FindBy(xpath = "//button[@title='Узбекистан']")
    private WebElement uzbekistan;

    @FindBy(xpath = "//div[@class ='input input_full lk-cv-block__input lk-cv-block__input_fake " +
            "lk-cv-block__input_select-fake js-custom-select-presentation']/child::span[contains(text(),'Город')]")
    private WebElement city;

    @FindBy(xpath = "//button[@title='Бухара']")
    private WebElement buhara;

    @FindBy(xpath = "//input[@data-title ='Уровень знания английского языка']/following-sibling::div")
    private WebElement levelLanguage;

    @FindBy(xpath = "//button[@title='Начальный уровень (Beginner)']")
    private WebElement beginnerLevel;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void setMainInfo() {
        countryList.click();
        logger.info("Кликаем на список стран");

        uzbekistan.click();
        logger.info("Кликаем на Узбекистан");

        city.click();
        logger.info("Кликаем на список городов");

        buhara.click();
        logger.info("Кликаем на Бухару");

        levelLanguage.click();
        logger.info("Кликаем на уровень английского языка");

        beginnerLevel.click();
        logger.info("Кликаем на начальный уровень английского языка");
    }

    private void makeClick(String locator, String fieldName) {
        WebElement elem = driver.findElement(By.xpath(locator));
        elem.click();
        logger.info(format("Кликаем на поле: %s", fieldName));
    }
}
