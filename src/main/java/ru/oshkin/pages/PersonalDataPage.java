package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonalDataPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(PersonalDataPage.class.getName());

    private final String fieldFormLocator = "//input[@name='%s']";

    public static final String NAME = "Жора";
    public static final String BLOG_NAME = "Жора";
    public static final String LATIN_NAME = "George";

    public static final String SECOND_NAME = "Иванов";
    public static final String LATIN_SECOND_NAME = "Ivanov";

    public static final String DATE_OF_BIRTH = "13.11.2001";


    public PersonalDataPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
    }

    public MainPage setPrivateDataInfo() {
        setName();
        setLatinName();
        setSecondName();
        setLatinSecondName();
        setBlogName();
        setDateOfBirth();

        return new MainPage(driver);
    }

    public MainPage checkPrivateDataInfo() {
        assertEquals(NAME, driver.findElement(By.xpath(String.format(fieldFormLocator, "fname"))).getAttribute("value"));
        assertEquals(LATIN_NAME, driver.findElement(By.xpath(String.format(fieldFormLocator, "fname_latin"))).getAttribute("value"));
        assertEquals(SECOND_NAME, driver.findElement(By.xpath(String.format(fieldFormLocator, "lname"))).getAttribute("value"));
        assertEquals(LATIN_SECOND_NAME, driver.findElement(By.xpath(String.format(fieldFormLocator, "lname_latin"))).getAttribute("value"));
        assertEquals(BLOG_NAME, driver.findElement(By.xpath(String.format(fieldFormLocator, "blog_name"))).getAttribute("value"));
        assertEquals(DATE_OF_BIRTH, driver.findElement(By.xpath(String.format(fieldFormLocator, "date_of_birth"))).getAttribute("value"));

        return new MainPage(driver);
    }

    private void setName() {
        WebElement name = driver.findElement(By.xpath(String.format(fieldFormLocator, "fname")));
        name.clear();
        name.sendKeys(NAME);
        logger.info("Очищаем поле: name и вводим новое поле: name");
    }

    private void setLatinName() {
        WebElement latinName = driver.findElement(By.xpath(String.format(fieldFormLocator, "fname_latin")));
        latinName.clear();
        latinName.sendKeys(LATIN_NAME);
        logger.info("Очищаем поле: latin name и вводим новое поле: latin name");
    }

    private void setSecondName() {
        WebElement secondName = driver.findElement(By.xpath(String.format(fieldFormLocator, "lname")));
        secondName.clear();
        secondName.sendKeys(SECOND_NAME);
        logger.info("Очищаем поле: secondName и вводим новое поле: secondName");
    }

    private void setLatinSecondName() {
        WebElement latinSecondName = driver.findElement(By.xpath(String.format(fieldFormLocator, "lname_latin")));
        latinSecondName.clear();
        latinSecondName.sendKeys(LATIN_SECOND_NAME);
        logger.info("Очищаем поле: latinSecondName и вводим новое поле: latinSecondName");
    }

    private void setBlogName() {
        WebElement blogName = driver.findElement(By.xpath(String.format(fieldFormLocator, "blog_name")));
        blogName.clear();
        blogName.sendKeys(BLOG_NAME);
        logger.info("Очищаем поле: blogName и вводим новое поле: blogName");
    }

    private void setDateOfBirth() {
        WebElement dateOfBirth = driver.findElement(By.xpath(String.format(fieldFormLocator, "date_of_birth")));
        dateOfBirth.clear();
        dateOfBirth.sendKeys(DATE_OF_BIRTH);
        logger.info("Очищаем поле: dateOfBirth и вводим новое поле: dateOfBirth");

        dateOfBirth.click();
        logger.info("Кликаем на поле: dateOfBirth");
    }
}
