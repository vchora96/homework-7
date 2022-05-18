package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonalDataPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(PersonalDataPage.class.getName());

    private final String fieldFormLocator = "//input[@name='%s']";

    public String name;
    public String blogName;
    public String latinName;

    public String secondName;
    public String latinSecondName;

    public String dateOfBirth;

    public void init(String name,
                     String blogName,
                     String latinName,
                     String secondName,
                     String latinSecondName,
                     String dateOfBirth) {
        this.name = name;
        this.blogName = blogName;
        this.latinName = latinName;
        this.secondName = secondName;
        this.latinSecondName = latinSecondName;
        this.dateOfBirth = dateOfBirth;
    }

    public PersonalDataPage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
    }

    public MainPage setPersonalDataInfo(String name,
                                        String blogName,
                                        String latinName,
                                        String secondName,
                                        String latinSecondName,
                                        String dateOfBirth) {
        init(name, blogName, latinName, secondName, latinSecondName, dateOfBirth);
        setName();
        setLatinName();
        setSecondName();
        setLatinSecondName();
        setBlogName();
        setDateOfBirth();


        return new MainPage(driver);
    }

    public MainPage checkPersonalDataInfo(String name,
                                          String blogName,
                                          String latinName,
                                          String secondName,
                                          String latinSecondName,
                                          String dateOfBirth) {
        init(name, blogName, latinName, secondName, latinSecondName, dateOfBirth);
        assertEquals(name, driver.findElement(By.xpath(String.format(fieldFormLocator, "fname"))).getAttribute("value"));
        assertEquals(latinName, driver.findElement(By.xpath(String.format(fieldFormLocator, "fname_latin"))).getAttribute("value"));
        assertEquals(secondName, driver.findElement(By.xpath(String.format(fieldFormLocator, "lname"))).getAttribute("value"));
        assertEquals(latinSecondName, driver.findElement(By.xpath(String.format(fieldFormLocator, "lname_latin"))).getAttribute("value"));
        assertEquals(blogName, driver.findElement(By.xpath(String.format(fieldFormLocator, "blog_name"))).getAttribute("value"));
        assertEquals(dateOfBirth, driver.findElement(By.xpath(String.format(fieldFormLocator, "date_of_birth"))).getAttribute("value"));

        return new MainPage(driver);
    }

    private void setName() {
        WebElement name = driver.findElement(By.xpath(String.format(fieldFormLocator, "fname")));
        name.clear();
        name.sendKeys(this.name);
        logger.info("Очищаем поле: name и вводим новое поле: name");
    }

    private void setLatinName() {
        WebElement latinName = driver.findElement(By.xpath(String.format(fieldFormLocator, "fname_latin")));
        latinName.clear();
        latinName.sendKeys(this.latinName);
        logger.info("Очищаем поле: latin name и вводим новое поле: latin name");
    }

    private void setSecondName() {
        WebElement secondName = driver.findElement(By.xpath(String.format(fieldFormLocator, "lname")));
        secondName.clear();
        secondName.sendKeys(this.secondName);
        logger.info("Очищаем поле: secondName и вводим новое поле: secondName");
    }

    private void setLatinSecondName() {
        WebElement latinSecondName = driver.findElement(By.xpath(String.format(fieldFormLocator, "lname_latin")));
        latinSecondName.clear();
        latinSecondName.sendKeys(this.latinSecondName);
        logger.info("Очищаем поле: latinSecondName и вводим новое поле: latinSecondName");
    }

    private void setBlogName() {
        WebElement blogName = driver.findElement(By.xpath(String.format(fieldFormLocator, "blog_name")));
        blogName.clear();
        blogName.sendKeys(this.blogName);
        logger.info("Очищаем поле: blogName и вводим новое поле: blogName");
    }

    private void setDateOfBirth() {
        WebElement dateOfBirth = driver.findElement(By.xpath(String.format(fieldFormLocator, "date_of_birth")));
        dateOfBirth.clear();
        dateOfBirth.sendKeys(this.dateOfBirth);
        logger.info("Очищаем поле: dateOfBirth и вводим новое поле: dateOfBirth");

        dateOfBirth.click();
        logger.info("Кликаем на поле: dateOfBirth");
    }
}
