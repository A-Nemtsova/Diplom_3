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
import site.nomoreparties.stellarburgers.model.RequestForLogin;
import site.nomoreparties.stellarburgers.model.RequestForRegistration;
import site.nomoreparties.stellarburgers.pageobjects.HomePage;
import site.nomoreparties.stellarburgers.pageobjects.LoginPage;
import site.nomoreparties.stellarburgers.pageobjects.RegisterPage;

import static com.codeborne.selenide.Selenide.open;

@Story("Регистрация")
public class RegisterTest {
    private RegisterPage registerPage;
    private HomePage homePage;
    private UserClient userClient;

    private String email;
    private String name;
    private String password;

    private RequestForRegistration requestForRegistration;

    @Before
    public void openPage() {
        //Закомментировать /* и */ для запуска тестов в Яндекс.Браузере
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
        requestForRegistration = RequestForRegistration.getRandomUser();
        name = requestForRegistration.getName();
        email = requestForRegistration.getEmail();
        password = requestForRegistration.getPassword();
        homePage = open(HomePage.URL, HomePage.class);

    }

    @After
    public void clear() {
        //Закомментировать /* и */, если тесты запускаются в Яндекс.Браузере
        /*
        WebDriverRunner.getWebDriver().close();
        */
        RequestForLogin requestForLogin = new RequestForLogin(email, password);
        String token = userClient.getTokenAfterLogin(requestForLogin);
        if (token.length() > 0) {
            userClient.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля. Минимальный пароль — шесть символов.")
    @Description("Проверка, что при вводе пароля меньше 6 символов отображается ошибка \"Некорректныц пароль\"")
    public void checkErrorForSmallPassword () {
        registerPage = homePage.clickToHomeLoginButton().clickToRegistrationLink();
        registerPage.checkFormTitle("Регистрация");
        registerPage.registrationWithErorr(name, email, "12345");
        registerPage.checkTextError("Некорректный пароль");
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля. Минимальный пароль — шесть символов.")
    @Description("Проверка, успешной регистрации")
    public void checkSuccessRegistration () {
        registerPage = homePage.clickToHomeLoginButton().clickToRegistrationLink();
        registerPage.checkFormTitle("Регистрация");
        LoginPage loginPage = registerPage.registration(name, email, password);
        loginPage.checkFormTitle("Вход");
    }
}
