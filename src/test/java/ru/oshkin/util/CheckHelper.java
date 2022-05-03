package ru.oshkin.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.oshkin.util.constants.TestData.*;
import static ru.oshkin.util.constants.TestData.SKYPE_LOGIN;
import static ru.oshkin.util.constants.TestLocatorsData.*;

public class CheckHelper {

    private final WebDriver driver;

    public CheckHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void checkPrivateDataInfo() {
        assertEquals(NAME, driver.findElement(By.xpath(NAME_LOCATOR)).getAttribute("value"));
        assertEquals(LATIN_NAME, driver.findElement(By.xpath(LATIN_NAME_LOCATOR)).getAttribute("value"));
        assertEquals(SECOND_NAME, driver.findElement(By.xpath(SECOND_NAME_LOCATOR)).getAttribute("value"));
        assertEquals(LATIN_SECOND_NAME, driver.findElement(By.xpath(LATIN_SECOND_NAME_LOCATOR)).getAttribute("value"));
        assertEquals(BLOG_NAME, driver.findElement(By.xpath(BLOG_NAME_LOCATOR)).getAttribute("value"));
        assertEquals(DATE_OF_BIRTH, driver.findElement(By.xpath(DATE_OF_BIRTH_LOCATOR)).getAttribute("value"));
    }


    public void checkMainInfo() {
        assertEquals("Узбекистан", driver.findElement(By.xpath("//input[@name ='country']/following-sibling::div")).getText());
        assertEquals("Бухара", driver.findElement(By.xpath("//input[@name ='city']/" +
                "following-sibling::div")).getText());
        assertEquals("Начальный уровень (Beginner)", driver.findElement(By.xpath("//input[@name ='english_level']/" +
                "following-sibling::div")).getText());
    }

    public void checkContactInfo() {
        WebElement skype = driver.findElement(By.xpath("//div[contains(text(),'Skype')]"));
        boolean skypeEnabled = skype.isEnabled();
        assertTrue(skypeEnabled);

        WebElement viber = driver.findElement(By.xpath("//div[contains(text(),'Viber')]"));
        boolean viberEnabled = viber.isEnabled();
        assertTrue(viberEnabled);

        List<WebElement> contacts = driver.findElements(By.xpath("//input[@class='input input_straight-top-left " +
                "input_straight-bottom-left lk-cv-block__input" +
                " lk-cv-block__input_9 lk-cv-block__input_md-8']"));
        ArrayList<String> strings = new ArrayList<>();
        for (WebElement contact : contacts) {
            String text = contact.getAttribute("value");
            strings.add(text);
        }

        boolean isPhoneContains = strings.contains(PHONE_NUMBER);
        assertTrue(isPhoneContains);

        boolean isSkypeLoginContains = strings.contains(SKYPE_LOGIN);
        assertTrue(isSkypeLoginContains);
    }
}
