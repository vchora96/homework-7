package ru.oshkin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LogInPage {

    private void logInByUser() {
        String link = "https://otus.ru";
        driver.get(link);
        logger.info("Перешли по ссылке");

        WebElement logPage = driver.findElement(By.xpath("//button[@data-modal-id='new-log-reg']"));
        logPage.click();

        WebElement mail = driver.findElement(By.xpath(" //input[@type='text' and @placeholder='Электронная почта']"));
        mail.sendKeys(login);
        logger.info("Ввели почту");

        WebElement password = driver.findElement(By.xpath("//input[@type='password' and @placeholder='Введите пароль']"));
        password.sendKeys(pass);
        logger.info("Ввели пароль");

        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Войти')]"));
        button.submit();
        logger.info("Попытка авторизации");

        WebElement userButton = driver.findElement(By.xpath("//div[@class = 'header2-menu__item-wrapper " +
                "header2-menu__item-wrapper__username']"));
        userButton.click();
        logger.info("Раскрытие блока");

        WebElement personalAccountButton = driver.findElement(By.xpath("//a[@title ='Личный кабинет']"));
        personalAccountButton.click();
        logger.info("Открываем личный кабинет");

        WebElement aboutUserButton = driver.findElement(By.xpath("//a[@title ='О себе']"));
        aboutUserButton.click();
        logger.info("Открываем информацию о себе");
    }

}
