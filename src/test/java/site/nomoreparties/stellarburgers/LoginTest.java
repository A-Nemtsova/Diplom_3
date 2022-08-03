package site.nomoreparties.stellarburgers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.pageobjects.HomePage;
import site.nomoreparties.stellarburgers.pageobjects.LoginPage;
import site.nomoreparties.stellarburgers.pageobjects.PasswordRecoveryPage;
import site.nomoreparties.stellarburgers.pageobjects.RegisterPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    private RegisterPage registerPage;
    private HomePage homePageWithoutLogin;
    private HomePage homePageWithLogin;
    private PasswordRecoveryPage passwordRecoveryPage;
    private LoginPage loginPage;
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
    }

    @After
    public void clear() {
        userClient.deleteUser(token);
    }

    @Test
    public void loginFromHomePage () {
        loginPage = homePageWithoutLogin.clickToHomeLoginButton();
        loginPage.checkFormTitle("Вход");
        homePageWithLogin = loginPage.login(email, password);
        homePageWithLogin.isVisibleCreateOrderBtn();
    }

    @Test
    public void loginFromLk () {
        homePageWithoutLogin.isVisibleHomeLoginBtn();
        loginPage = homePageWithoutLogin.clickToLkBtnWhithoutLogin();
        loginPage.checkFormTitle("Вход");
        homePageWithLogin = loginPage.login(email, password);
        homePageWithLogin.isVisibleCreateOrderBtn();
    }

    @Test
    public  void loginFromRegistrationPage () {
        homePageWithoutLogin.isVisibleHomeLoginBtn();
        registerPage = homePageWithoutLogin.clickToHomeLoginButton().clickToRegistrationLink();
        registerPage.checkFormTitle("Регистрация");
        loginPage = registerPage.clickToLoginLink();
        loginPage.checkFormTitle("Вход");
        homePageWithLogin = loginPage.login(email, password);
        homePageWithLogin.isVisibleCreateOrderBtn();
    }

    @Test
    public void loginFromPasswordRecoveryPage () {
        homePageWithoutLogin.isVisibleHomeLoginBtn();
        passwordRecoveryPage = homePageWithoutLogin.clickToHomeLoginButton().clickToRecoveryLink();
        passwordRecoveryPage.checkFormTitle("Восстановление пароля");
        loginPage = passwordRecoveryPage.clickToLoginLink();
        loginPage.checkFormTitle("Вход");
        homePageWithLogin = loginPage.login(email, password);
        homePageWithLogin.isVisibleCreateOrderBtn();
    }
}
