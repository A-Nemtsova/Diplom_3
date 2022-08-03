package site.nomoreparties.stellarburgers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.pageobjects.HomePage;
import site.nomoreparties.stellarburgers.pageobjects.LoginPage;
import site.nomoreparties.stellarburgers.pageobjects.RegisterPage;

import static com.codeborne.selenide.Selenide.open;

public class RegisterTest {
    private RegisterPage registerPage;
    private HomePage homePage;
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
        homePage = open(homePage.URL, HomePage.class);

    }

    @After
    public void clear() {
        token = userClient.getTokenAfterLogin(email, password);
        if (token.length() > 0) {
            userClient.deleteUser(token);
        }
    }

    @Test
    public void checkErrorForSmallPassword () {
        registerPage = homePage.clickToHomeLoginButton().clickToRegistrationLink();
        registerPage.checkFormTitle("Регистрация");
        registerPage.registrationWithErorr(name, email, "123");
        registerPage.checkTextError("Некорректный пароль");
    }

    @Test
    public void checkSuccessRegistration () {
        registerPage = homePage.clickToHomeLoginButton().clickToRegistrationLink();
        registerPage.checkFormTitle("Регистрация");
        loginPage = registerPage.registration(name, email, password);
        loginPage.checkFormTitle("Вход");
    }

}
