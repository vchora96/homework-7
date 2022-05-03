package ru.oshkin;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.oshkin.pages.LogInPage;

public class OtusHomeworkTest {

    @Test
    public void setPrivetDataOtusTest() {
        final WebDriver webDriver = new LogInPage()
                .logInByUser()
                .setPrivateDataInfo()
                .setMainInfo()
                .setContactInfo();

        //выполняем проверки
        CheckHelper checkHelper = new CheckHelper(webDriver);
        checkHelper.checkPrivateDataInfo();
        checkHelper.checkMainInfo();
        checkHelper.checkContactInfo();
    }
}
