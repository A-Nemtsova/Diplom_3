package site.nomoreparties.stellarburgers;

import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.pageobjects.HomePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;

public class ConstructorTest {
    private HomePage homePageWithoutLogin;
    private UserClient userClient;

    @Before
    public void openPageInChrome() {
        //открывается страница и создаётся экземпляр класса страницы
        userClient = new UserClient();
        homePageWithoutLogin = open(homePageWithoutLogin.URL, HomePage.class);
    }

    @Test
    public void checkBunsTab () {
        assertTrue(homePageWithoutLogin.isActiveBunsTab());
    }

    @Test
    public void checkSauceTab () {
      assertTrue(homePageWithoutLogin.isActiveSauceTab());
    }

    @Test
    public void checkFillingTab () {
        assertTrue(homePageWithoutLogin.isActiveFillingTab());
    }
}
