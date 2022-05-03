package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;
import static ru.oshkin.old.TestData.*;
import static ru.oshkin.old.TestData.DATE_OF_BIRTH;
import static ru.oshkin.old.TestLocatorsData.*;
import static ru.oshkin.old.TestLocatorsData.DATE_OF_BIRTH_LOCATOR;

public class PrivateDataPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(PrivateDataPage.class.getName());

    private void setPrivateDataInfo() {
        findElemSetValue(NAME_LOCATOR, NAME, "имя");
        findElemSetValue(LATIN_NAME_LOCATOR, LATIN_NAME, "имя на английском");
        findElemSetValue(SECOND_NAME_LOCATOR, SECOND_NAME, "фамилия");
        findElemSetValue(LATIN_SECOND_NAME_LOCATOR, LATIN_SECOND_NAME, "фамилия на английском");
        findElemSetValue(BLOG_NAME_LOCATOR, BLOG_NAME, "имя в блоге");
        findElemSetValue(DATE_OF_BIRTH_LOCATOR, DATE_OF_BIRTH, "дата рождения");

        makeClick(DATE_OF_BIRTH_LOCATOR, "окно календаря");
    }

    private void findElemSetValue(String locator, String value, String fieldName) {
        WebDriverWait wait = new WebDriverWait(driver, 4);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        element.clear();
        element.sendKeys(value);
        logger.info(format("Очищаем поле: %s и вводим новое поле: %s", fieldName, fieldName));
    }

    private void makeClick(String locator, String fieldName) {
        WebElement elem = driver.findElement(By.xpath(locator));
        elem.click();
        logger.info(format("Кликаем на поле: %s", fieldName));
    }
}
