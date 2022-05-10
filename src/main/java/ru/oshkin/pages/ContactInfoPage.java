package ru.oshkin.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.oshkin.util.Contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.oshkin.util.constants.TestData.PHONE_NUMBER;
import static ru.oshkin.util.constants.TestData.SKYPE_LOGIN;

public class ContactInfoPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(ContactInfoPage.class.getName());

    private String contactLocator = ".js-custom-select-options-container:not(.hide)" +
            " button[title='%s']";

    @FindBy(xpath = "//button[contains(text(),'Указать телефон')]")
    private WebElement phone;

    @FindBy(xpath = "//Input[@name ='phone' and @placeholder ='Номер телефона']")
    private WebElement numberPhone;

    @FindBy(xpath = "//button[contains(text(),'Отправить')]")
    private WebElement sendButton;

    @FindBy(css = "div div.modal__close.ic-close.js-close-modal")
    private WebElement confirmationWindow;

    @FindBy(css = "button.js-lk-cv-custom-select-add")
    private WebElement addContactButton;

    @FindBy(xpath = "//span[contains(text(),'Способ связи')]")
    private WebElement communicationWay;

    @FindBy(css = "input.input.input_straight-top-left.input_straight-bottom-left.lk-cv-block__input")
    private List<WebElement> communicationValueWays;

    @FindBy(xpath = "//button[@title ='Сохранить и продолжить']")
    private WebElement saveButton;

    @FindBy(css = "p.header2-menu__item-text.header2-menu__item-text__username")
    private WebElement userButton;

    @FindBy(css = "div a[title='Выход']")
    private WebElement exitButton;

    public ContactInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void setContactInfo() {
        clickPhone();
        setPhone();
        clickSendButton();
        closeConfirmationWindow();
        setContacts();
        saveInfo();
        exitProfile();
    }

    public void checkContactInfo() {
        String skypeSelector = format(contactLocator, "Skype");
        WebElement skype = driver.findElement(By.cssSelector(skypeSelector));
        boolean skypeEnabled = skype.isEnabled();
        assertTrue(skypeEnabled);

        String viberSelector = format(contactLocator, "Viber");
        WebElement viber = driver.findElement(By.cssSelector(viberSelector));
        boolean viberEnabled = viber.isEnabled();
        assertTrue(viberEnabled);

        ArrayList<String> strings = new ArrayList<>();
        for (WebElement contact : communicationValueWays) {
            String text = contact.getAttribute("value");
            strings.add(text);
        }

        boolean isPhoneContains = strings.contains(PHONE_NUMBER);
        assertTrue(isPhoneContains);

        boolean isSkypeLoginContains = strings.contains(SKYPE_LOGIN);
        assertTrue(isSkypeLoginContains);
    }

    private void openBlock() {
        userButton.click();
        logger.info("Раскрытие блока");
    }

    /*
     *Принимает список контактов для добавления на страницу
     */
    private void addContact(ArrayList<Contact> contacts) {
        for (Contact contact : contacts) {//проходим по каждому контакту из списка
            addContactButton.click();
            logger.info("Добавить контакт");

            communicationWay.click();
            logger.info("Кликаем на способ связи");

            WebElement contactWebElem = driver.findElement(By.cssSelector(format(contactLocator, contact.getType())));
            contactWebElem.click();
            logger.info(format("Выбираем способ связи %s", contact.getType()));

            for (WebElement element : communicationValueWays) {//проходимся по правой колонке = содержимому
                String text = element.getAttribute("contact");
                if (text.length() == 0) { //как только нашли пустое поле
                    element.sendKeys(contact.getValue());
                    element.click();
                }
            }
        }
    }

    private void clickPhone() {
        phone.click();
        logger.info("Кликнули по телефону");
    }

    private void setPhone() {
        numberPhone.clear();
        numberPhone.sendKeys(PHONE_NUMBER);
        logger.info("Заполнили номер телефона");
    }

    private void clickSendButton() {
        sendButton.click();
        logger.info("Кликнули на кнопку отправить");
    }

    private void closeConfirmationWindow() {
        confirmationWindow.click();
        logger.info("Закрываем окно с подтверждением номера телефона");
    }

    private void setContacts() {
        addContact(Arrays.asList(new Contact[]{new Contact("Skype", PHONE_NUMBER)}));
        logger.info("Два контакта успешно заполнены");
    }

    private void saveInfo() {
        saveButton.click();
        logger.info("Кликаем на сохранить и продолжить");
    }

    private void exitProfile() {
        openBlock();

        exitButton.click();
        logger.info("Выходим из профиля клиента");
    }
}
