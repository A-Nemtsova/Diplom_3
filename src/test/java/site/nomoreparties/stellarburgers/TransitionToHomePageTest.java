package site.nomoreparties.stellarburgers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.pageobjects.*;

import static com.codeborne.selenide.Selenide.open;

public class TransitionToHomePageTest {
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
        lkPage = homePageWithoutLogin.clickToHomeLoginButton().login(email, password).clickToLkBtnWithLogin();
        //registerPage = homePage.clickToHomeLoginButton().clickToRegistrationLink();
    }

    @After
    public void clear() {
        userClient.deleteUser(token);
    }

    @Test
    public void transitionToHomePageFromLkThroughConstructor () {
        lkPage.checkFormDescription("В этом разделе вы можете изменить свои персональные данные");
        homePageWithLogin = lkPage.clickToConstructorBtn();
        homePageWithLogin.checkFormTitle("Соберите бургер");
    }

    @Test
    public void transitionToHomePageFromLkThroughLogo () {
        lkPage.checkFormDescription("В этом разделе вы можете изменить свои персональные данные");
        homePageWithLogin = lkPage.clickToLogo();
        homePageWithLogin.checkFormTitle("Соберите бургер");
    }
}
