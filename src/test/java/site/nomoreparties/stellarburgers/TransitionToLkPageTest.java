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
import site.nomoreparties.stellarburgers.pageobjects.*;

import static com.codeborne.selenide.Selenide.open;

@Story("Переход в личный кабинет")
public class TransitionToLkPageTest {
    private HomePage homePageWithLogin;
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
        String email = userClient.getRandomEmail();
        String name = userClient.getRandomName();
        String password = userClient.getRandomPassword();
        token = userClient.getTokenAfterRegistration(email, name, password);
        HomePage homePageWithoutLogin = open(HomePage.URL, HomePage.class);
        homePageWithLogin = homePageWithoutLogin.clickToHomeLoginButton().login(email, password);
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
    @DisplayName("Переход в личный кабинет по клику на \"Личный кабинет\"")
    @Description("Проверка, что происходит переход из домашней страницы в личный кабинет по клику на \"Личный кабинет\" (для авторизованного пользователя)")
    public void transitionToLkFromHomePage () {
        homePageWithLogin.isVisibleCreateOrderBtn();
        LkPage lkPage = homePageWithLogin.clickToLkBtnWithLogin();
        lkPage.checkFormDescription("В этом разделе вы можете изменить свои персональные данные");
    }
}
