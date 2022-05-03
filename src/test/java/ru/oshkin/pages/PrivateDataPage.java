package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.oshkin.old.TestData;

import java.util.concurrent.TimeUnit;

public class PrivateDataPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(PrivateDataPage.class.getName());

    @FindBy(xpath = "//input [@name ='fname']")
    private WebElement name;

    @FindBy(xpath = "//input [@name ='fname_latin']")
    private WebElement latinName;

    @FindBy(xpath = "//input [@name ='lname']")
    private WebElement secondName;

    @FindBy(xpath = "//input [@name ='lname_latin']")
    private WebElement latinSecondName;

    @FindBy(xpath = "//input [@name ='blog_name']")
    private WebElement blogName;

    @FindBy(xpath = "//input [@name ='date_of_birth']")
    private WebElement dateOfBirth;

    public PrivateDataPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public MainPage setPrivateDataInfo() {
        name.clear();
        name.sendKeys(TestData.NAME);
        logger.info("Очищаем поле: name и вводим новое поле: name");

        latinName.clear();
        latinName.sendKeys(TestData.LATIN_NAME);
        logger.info("Очищаем поле: latin name и вводим новое поле: latin name");

        secondName.clear();
        secondName.sendKeys(TestData.SECOND_NAME);
        logger.info("Очищаем поле: secondName и вводим новое поле: secondName");

        latinSecondName.clear();
        latinSecondName.sendKeys(TestData.LATIN_SECOND_NAME);
        logger.info("Очищаем поле: latinSecondName и вводим новое поле: latinSecondName");

        blogName.clear();
        blogName.sendKeys(TestData.BLOG_NAME);
        logger.info("Очищаем поле: blogName и вводим новое поле: blogName");

        dateOfBirth.clear();
        dateOfBirth.sendKeys(TestData.DATE_OF_BIRTH);
        logger.info("Очищаем поле: dateOfBirth и вводим новое поле: dateOfBirth");

        dateOfBirth.click();
        logger.info("Кликаем на поле: dateOfBirth");

        return new MainPage(driver);
    }
}
