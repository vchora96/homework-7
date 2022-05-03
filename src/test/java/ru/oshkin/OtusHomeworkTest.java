package ru.oshkin;

import org.junit.jupiter.api.Test;
import ru.oshkin.pages.LogInPage;
import ru.oshkin.pages.PrivateDataPage;

public class OtusHomeworkTest {

    @Test
    public void setPrivetDataOtusTest() {
        LogInPage logInPage = new LogInPage();
        logInPage.logInByUser().setPrivateDataInfo();
    }
}
