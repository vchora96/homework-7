package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.lang.String.format;
import static ru.oshkin.old.TestData.PHONE_NUMBER;
import static ru.oshkin.old.TestData.SKYPE_LOGIN;

public class ContactInfoPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(ContactInfoPage.class.getName());

    @FindBy(xpath = "//button[contains(text(),'Указать телефон')]")
    private WebElement phone;

    @FindBy(xpath = "//Input[@name ='phone' and @placeholder ='Номер телефона']")
    private WebElement numberPhone;

    @FindBy(xpath = "//button[contains(text(),'Отправить')]")
    private WebElement sendButton;

    @FindBy(xpath = "//div[@class ='modal__close ic-close js-close-modal']")
    private WebElement confirmationWindow;

    public ContactInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void setContactInfo() {

        phone.click();
        logger.info("Кликнули по телефону");

        numberPhone.clear();
        numberPhone.sendKeys(PHONE_NUMBER);
        logger.info("Заполнили номер телефона");

        sendButton.click();
        logger.info("Кликнули на кнопку отправить");

        confirmationWindow.click();
        logger.info("Закрываем окно с подтверждением номера телефона");

        addContact(
                "//div[@class ='lk-cv-block__select-options lk-cv-block__select-options_left " +
                        "js-custom-select-options-container']/descendant::button[@title='Viber']",
                "Viber", PHONE_NUMBER);
        addContact(
                "//div[@class ='lk-cv-block__select-options lk-cv-block__select-options_left " +
                        "js-custom-select-options-container']/descendant::button[@title ='Skype']",
                "Skype", SKYPE_LOGIN);
    }

    private void addContact(String locator, String contactType, String value) {
        driver.findElement(By.cssSelector("button.js-lk-cv-custom-select-add")).click();
        logger.info("Добавить контакт");

        makeClick("//span[contains(text(),'Способ связи')]", "Способ связи");

        selectCommunicationWay(locator, contactType);
        List<WebElement> elements = driver.findElements(By.xpath("//input[@class='input input_straight-top-left " +
                "input_straight-bottom-left lk-cv-block__input" +
                " lk-cv-block__input_9 lk-cv-block__input_md-8']"));
        for (WebElement element : elements) {
            String text = element.getAttribute("value");
            if (text.length() == 0) {
                element.sendKeys(value);
                element.click();
            }
        }
    }

    private void selectCommunicationWay(String locator, String name) {
        List<WebElement> communicationWays = driver.findElements(By.xpath(locator));
        communicationWays.get(0).click();
        logger.info(format("Выбираем способ связи %s", name));
    }
}
