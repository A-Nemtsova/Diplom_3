package site.nomoreparties.stellarburgers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.pageobjects.*;

import static com.codeborne.selenide.Selenide.open;

public class TransitionToLkPageTest {
    private HomePage homePageWithoutLogin;
    private HomePage homePageWithLogin;
    private LkPage lkPage;
    private UserClient userClient;

    private String email;
    private String name;
    private String password;
    private String token;

    @Before
    public void openPageInChrome() {
        //открывается страница и создаётся экземпляр класса страницы
        userClient = new UserClient();
        email = userClient.getRandomEmail();
        name = userClient.getRandomName();
        password = userClient.getRandomPassword();
        token = userClient.getTokenAfterRegistration(email, name, password);
        homePageWithoutLogin = open(homePageWithoutLogin.URL, HomePage.class);
        homePageWithLogin = homePageWithoutLogin.clickToHomeLoginButton().login(email, password);
    }

    @After
    public void clear() {
        userClient.deleteUser(token);
    }

    @Test
    public void transitionToLkFromHomePage () {
        homePageWithLogin.isVisibleCreateOrderBtn();
        lkPage = homePageWithLogin.clickToLkBtnWithLogin();
        lkPage.checkFormDescription("В этом разделе вы можете изменить свои персональные данные");
    }
}
