package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class HeaderPage {
    //локатор ккнопки "Конструктор"
    @FindBy(how = How.XPATH, using = ".//li/a[@href='/']")
    private SelenideElement constructorBtn;

    //локатор ккнопки "Лента заказов""
    @FindBy(how = How.XPATH, using = ".//li/a[@href='/feed']")
    private SelenideElement orderFeedBtn;

    //локатор логотипа
    @FindBy(how = How.XPATH, using = ".//div/a[@href='/']")
    private SelenideElement logo;

    //Локатор кнопки "Личный кабинет"
    @FindBy(how = How.XPATH, using = ".//a[@href='/account']")
    private SelenideElement lkBtn;

    //методы хедера
    @Step("Клик по логотипу")
    public HomePage clickToLogo () {
        logo.click();
        return (HomePage) page(HomePage.class);
    }

    @Step("Клик по кнопке \"Конструктор\"")
    public HomePage clickToConstructorBtn () {
        constructorBtn.click();
        return (HomePage) page(HomePage.class);
    }

    @Step("Клик по кнопке \"Личный кабинет\" (если пользователь не авторизован)")
    public LoginPage clickToLkBtnWhithoutLogin () {
        lkBtn.click();
        return (LoginPage) page(LoginPage.class);
    }

    @Step("Клик по кнопке \"Личный кабинет\" (если пользователь авторизован)")
    public LkPage clickToLkBtnWithLogin () {
        lkBtn.click();
        return (LkPage) page(LkPage.class);
    }
}

