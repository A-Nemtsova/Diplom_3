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
import site.nomoreparties.stellarburgers.model.RequestForRegistration;
import site.nomoreparties.stellarburgers.pageobjects.*;

import static com.codeborne.selenide.Selenide.open;

@Story("Переход из личного кабинета в конструктор")
public class TransitionToHomePageTest {
    private HomePage homePageWithLogin;
    private LkPage lkPage;
    private UserClient userClient;

    private String token;

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

        RequestForRegistration requestForRegistration = RequestForRegistration.getRandomUser();
        token = userClient.getTokenAfterRegistration(requestForRegistration);
        HomePage homePageWithoutLogin = open(HomePage.URL, HomePage.class);
        lkPage = homePageWithoutLogin.clickToHomeLoginButton().login(requestForRegistration.getEmail(), requestForRegistration.getPassword()).clickToLkBtnWithLogin();
    }

    @After
    public void clear() {
        //Закомментировать /* и */, если тесты запускаются в Яндекс.Браузере
        /*
        WebDriverRunner.getWebDriver().close();
        */
        userClient.deleteUser(token);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на \"Конструктор\"")
    @Description("Проверка, что происходит переход из личного кабинета в конструктор по клику на \"Конструктор\"")
    public void transitionToHomePageFromLkThroughConstructor () {
        lkPage.checkFormDescription("В этом разделе вы можете изменить свои персональные данные");
        homePageWithLogin = lkPage.clickToConstructorBtn();
        homePageWithLogin.checkFormTitle("Соберите бургер");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    @Description("Проверка, что происходит переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void transitionToHomePageFromLkThroughLogo () {
        lkPage.checkFormDescription("В этом разделе вы можете изменить свои персональные данные");
        homePageWithLogin = lkPage.clickToLogo();
        homePageWithLogin.checkFormTitle("Соберите бургер");
    }
}
