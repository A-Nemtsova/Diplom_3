package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.pageobjects.HomePage;
import site.nomoreparties.stellarburgers.pageobjects.LoginPage;
import site.nomoreparties.stellarburgers.pageobjects.PasswordRecoveryPage;
import site.nomoreparties.stellarburgers.pageobjects.RegisterPage;

import static com.codeborne.selenide.Selenide.open;

@Story("Вход")
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
    public void openPage() {
        //Закомментировать /* и */  для запуска тестов в Яндекс.Браузере
        /*
        ChromeOptions options = new ChromeOptions();
        System.getProperty("webdriver.chrome.driver", "C:/Users/anemtsova/WebDriver/bin/chromedriver.exe");
        options.setBinary("C:/Users/anemtsova/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        options.addArguments("test-type=browser", "chromeoptions.args", "--no-sandbox", "--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
        */
        //открывается страница и создаётся экземпляр класса страницы
        userClient = new UserClient();
        email = userClient.getRandomEmail();
        name = userClient.getRandomName();
        password = userClient.getRandomPassword();
        token = userClient.getTokenAfterRegistration(email, name, password);
        homePageWithoutLogin = open(HomePage.URL, HomePage.class);
    }

    @After
    public void clear() {
        //Закомментировать /* и */ , если тесты запускаются в Яндекс.Браузере
        /*
        WebDriverRunner.getWebDriver().close();
        */
        userClient.deleteUser(token);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка, что на форму входа можно попачть через кнопку \"Войти в аккаунт\" на домашней странице и там можно авторизоваться")
    public void loginFromHomePage () {
        loginPage = homePageWithoutLogin.clickToHomeLoginButton();
        loginPage.checkFormTitle("Вход");
        homePageWithLogin = loginPage.login(email, password);
        homePageWithLogin.isVisibleCreateOrderBtn();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка, что на форму входа можно попачть через кнопку \"Личный кабинет\" и там можно авторизоваться")
    public void loginFromLk () {
        homePageWithoutLogin.isVisibleHomeLoginBtn();
        loginPage = homePageWithoutLogin.clickToLkBtnWhithoutLogin();
        loginPage.checkFormTitle("Вход");
        homePageWithLogin = loginPage.login(email, password);
        homePageWithLogin.isVisibleCreateOrderBtn();
    }

    @Test
    @DisplayName("Вход пчерез кнопку в форме регистрации")
    @Description("Проверка, что на форму входа можно попачть через форму регистрации и там можно авторизоваться")
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
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка, что на форму входа можно попачть через кнопку в форме восстановления пароля и там можно авторизоваться")
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
