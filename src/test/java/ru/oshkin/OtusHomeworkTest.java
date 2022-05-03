package ru.oshkin;

import org.junit.jupiter.api.Test;
import ru.oshkin.pages.LogInPage;

public class OtusHomeworkTest {

    @Test
    public void setPrivetDataOtusTest() {
        new LogInPage()
                .logInByUser()
                .setPrivateDataInfo()
                .setMainInfo()
                .setContactInfo();
    }
}
