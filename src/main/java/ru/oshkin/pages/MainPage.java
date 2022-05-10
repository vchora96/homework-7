package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.oshkin.util.constants.City;
import ru.oshkin.util.constants.LanguageLevel;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(MainPage.class.getName());

    @FindBy(css = "input[name='country'].js-custom-select-input + div")
    private WebElement countryList;


    @FindBy(css = "input[data-title='Город'].js-custom-select-input + div")
    private WebElement city;

    @FindBy(xpath = "//input[@data-title ='Уровень знания английского языка']/following-sibling::div")
    private WebElement levelLanguage;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public ContactInfoPage setMainInfo() {
        clickCountries();
        choseResidencePlace(City.ANGREN);
        clickLanguageLevel();
        choseLanguageLevel();

        return new ContactInfoPage(driver);
    }

    public ContactInfoPage checkMainInfo() {
        assertEquals(City.ANGREN.getCountry(), driver.findElement(By.xpath("//input[@name ='country']/following-sibling::div")).getText());
        assertEquals(City.ANGREN.getCityName(), driver.findElement(By.xpath("//input[@name ='city']/" +
                "following-sibling::div")).getText());
        assertEquals(LanguageLevel.Elementary.getDescription(), driver.findElement(By.xpath("//input[@name ='english_level']/" +
                "following-sibling::div")).getText());

        return new ContactInfoPage(driver);
    }

    private void choseLanguageLevel() {
        selectLanguageLevel(LanguageLevel.Elementary).click();
        logger.info("Кликаем на начальный уровень английского языка");
    }

    private void clickLanguageLevel() {
        levelLanguage.click();
        logger.info("Кликаем на уровень английского языка");
    }

    private void clickCity(City city) {
        //todo: иногда не удается кликнуть на город, так как на предыдущем шаге не всплывает окно
        WebElement selectCity = selectCity(city);
        selectCity.click();
        logger.info("Кликаем на город");
    }

    private void choseResidencePlace(City city) {
        WebElement country = selectCountry(city.getCountry());
        country.click();
        logger.info(String.format("Кликаем на страну: %s", city.getCountry()));
        choseCities();
        clickCity(city);
    }

    private void choseCities() {
        city.click();
        logger.info("Кликаем на список городов");
    }

    private void clickCountries() {
        countryList.click();
        logger.info("Кликаем на список стран");
    }

    private WebElement selectCity(City city) {
        String cityName = city.getCityName();//Получаем имя города из enum
        String selector = String.format("//button[@title='%s']", cityName);
        return driver.findElement(By.xpath(selector));
    }

    private WebElement selectLanguageLevel(LanguageLevel languageLevel) {
        String languageLevelDescription = languageLevel.getDescription();
        String selector = String.format("//button[@title='%s']", languageLevelDescription);
        return driver.findElement(By.xpath(selector));
    }

    private WebElement selectCountry(String country) {
        String selector = String.format("//button[@title='%s']", country);
        return driver.findElement(By.xpath(selector));
    }
}
