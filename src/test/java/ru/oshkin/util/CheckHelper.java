package ru.oshkin.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.oshkin.util.constants.City;
import ru.oshkin.util.constants.Country;
import ru.oshkin.util.constants.LanguageLevel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.oshkin.util.constants.TestData.*;
import static ru.oshkin.util.constants.TestData.SKYPE_LOGIN;

public class CheckHelper {

    private final WebDriver driver;

    @FindBy(xpath = "//input [@name ='fname']")
    private WebElement name;

    @FindBy(xpath = "//input [@name ='fname_latin']")
    private WebElement secondName;

    @FindBy(xpath = "//input [@name ='lname']")
    private WebElement latinName;

    @FindBy(xpath = "//input [@name ='lname_latin']")
    private WebElement latinSecondName;

    @FindBy(xpath = "//input [@name ='blog_name']")
    private WebElement blogName;

    @FindBy(xpath = "//input [@name ='date_of_birth']")
    private WebElement dateOfBirth;

    public CheckHelper(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkPrivateDataInfo() {
        assertEquals(NAME, name.getAttribute("value"));
        assertEquals(LATIN_NAME, secondName.getAttribute("value"));
        assertEquals(SECOND_NAME, latinName.getAttribute("value"));
        assertEquals(LATIN_SECOND_NAME, latinSecondName.getAttribute("value"));
        assertEquals(BLOG_NAME, blogName.getAttribute("value"));
        assertEquals(DATE_OF_BIRTH, dateOfBirth.getAttribute("value"));
    }

    public void checkMainInfo() {
        assertEquals(Country.UZBEKISTAN.getCountry(), driver.findElement(By.xpath("//input[@name ='country']/following-sibling::div")).getText());
        assertEquals(City.ANGREN.getCityName(), driver.findElement(By.xpath("//input[@name ='city']/" +
                "following-sibling::div")).getText());
        assertEquals(LanguageLevel.Elementary.getDescription(), driver.findElement(By.xpath("//input[@name ='english_level']/" +
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
