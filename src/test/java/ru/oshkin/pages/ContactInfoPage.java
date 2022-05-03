package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.lang.String.format;
import static ru.oshkin.util.TestData.PHONE_NUMBER;
import static ru.oshkin.util.TestData.SKYPE_LOGIN;

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

    @FindBy(css = "button.js-lk-cv-custom-select-add")
    private WebElement addContactButton;

    @FindBy(xpath = "//span[contains(text(),'Способ связи')]")
    private WebElement communicationWay;

    @FindBy(xpath = "//div[@class ='lk-cv-block__select-options lk-cv-block__select-options_left " +
            "js-custom-select-options-container']/descendant::button[@title='Viber']")
    private List<WebElement> communicationViberWays;

    @FindBy(xpath = "//div[@class ='lk-cv-block__select-options lk-cv-block__select-options_left " +
            "js-custom-select-options-container']/descendant::button[@title ='Skype']")
    private List<WebElement> communicationSkypeWays;

    @FindBy(xpath = "//input[@class='input input_straight-top-left " + "input_straight-bottom-left lk-cv-block__input " +
            " lk-cv-block__input_9 lk-cv-block__input_md-8']")
    private List<WebElement> communicationValueWays;

    @FindBy(xpath = "//button[@title ='Сохранить и продолжить']")
    private WebElement saveButton;

    public ContactInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setContactInfo() {

        phone.click();
        logger.info("Кликнули по телефону");

        numberPhone.clear();
        numberPhone.sendKeys(PHONE_NUMBER);
        logger.info("Заполнили номер телефона");

        sendButton.click();
        logger.info("Кликнули на кнопку отправить");

        confirmationWindow.click();
        logger.info("Закрываем окно с подтверждением номера телефона");

        addContact(communicationViberWays, "Viber", PHONE_NUMBER);
        addContact(communicationSkypeWays, "Skype", SKYPE_LOGIN);
        logger.info("Два контакта успешно заполнены");

        saveButton.click();
        logger.info("Кликаем на сохранить и продолжить");
    }

    private void addContact(List<WebElement> communicationWays, String contactType, String value) {
        addContactButton.click();
        logger.info("Добавить контакт");

        communicationWay.click();
        logger.info("Кликаем на способ связи");

        communicationWays.get(0).click();
        logger.info(format("Выбираем способ связи %s", contactType));

        for (WebElement element : communicationValueWays) {//проходимся по правой колонке = содержимому
            String text = element.getAttribute("value");
            if (text.length() == 0) { //как только нашли пустое поле
                element.sendKeys(value);
                element.click();
            }
        }
    }
}
