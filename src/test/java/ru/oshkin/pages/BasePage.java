package ru.oshkin.pages;

import org.openqa.selenium.WebDriver;
import ru.oshkin.WebBrowserType;
import ru.oshkin.WebDriverFactory;

public class BasePage {
    protected WebDriver driver;
    BasePage(){
        driver = WebDriverFactory.create(System.getenv("NAME"));


    }
}
