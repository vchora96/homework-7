package ru.oshkin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.oshkin.WebBrowserType;
import ru.oshkin.WebDriverFactory;

public class BasePage {
    //ссылочная переменная
    protected WebDriver driver;

    //конструктор родительского класса
    BasePage() {
        String envVariable = System.getenv("type");
        //String envVariable = System.getProperty("type");
        WebBrowserType type = WebBrowserType.valueOf(envVariable);
        driver = WebDriverFactory.create(type);
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
