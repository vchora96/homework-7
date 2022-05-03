package ru.oshkin;

import org.junit.jupiter.api.Test;
import ru.oshkin.pages.LogInPage;

public class OtusHomeworkTest {

    @Test
    public void setPrivetDataOtusTest(){
        LogInPage logInPage = new LogInPage();
        logInPage.logInByUser();
    }
}
