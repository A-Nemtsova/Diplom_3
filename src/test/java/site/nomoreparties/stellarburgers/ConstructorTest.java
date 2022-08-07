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

import static com.codeborne.selenide.Selenide.open;

@Story("Раздел «Конструктор»")
public class ConstructorTest {
    private HomePage homePageWithoutLogin;

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
        UserClient userClient = new UserClient();
        homePageWithoutLogin = open(HomePage.URL, HomePage.class);
    }

    //Закоментировать /* и */, если тесты запускаются в Яндекс.Браузере
  /*
    @After
    public void clear() {
        WebDriverRunner.getWebDriver().close();
    }
  */

    @Test
    @DisplayName("Переход к разделу \"Будки\"")
    @Description("Проверка, что работает переход к разделу \"Булки\" (вкладка становится активной)")
    public void checkBunsTab () {
        homePageWithoutLogin.isActiveBunsTab();
    }

    @Test
    @DisplayName("Переход к разделу \"Соусы\"")
    @Description("Проверка, что работает переход к разделу \"Соусы\" (вкладка становится активной)")
    public void checkSauceTab () {
      homePageWithoutLogin.isActiveSauceTab();
    }

    @Test
    @DisplayName("Переход к разделу \"Начинки\"")
    @Description("Проверка, что работает переход к разделу \"Начинки\" (вкладка становится активной)")
    public void checkFillingTab () {
        homePageWithoutLogin.isActiveFillingTab();
    }
}
