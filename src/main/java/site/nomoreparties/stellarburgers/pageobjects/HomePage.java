package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;


public class HomePage extends HeaderPage {

    public final static String URL = "https://stellarburgers.nomoreparties.site";

    //локатор названия страницы "Соберите бургер"
    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    private SelenideElement titleHomePage;

    //локатор активной вкладки "Булки" (при загрузке страницы по умолчанию активна)
    @FindBy(how = How.XPATH, using = ".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']//span[text()='Булки']")
    private SelenideElement bunsTab;

    //локатор не активной вкладки "Соусы"
    @FindBy(how = How.XPATH, using = ".//div//span[text()='Соусы']")
    private SelenideElement inActiveSauceTab;

    //локатор активной вкладки "Соусы"
    @FindBy(how = How.XPATH, using = ".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']//span[text()='Соусы']")
    private SelenideElement sauceTab;

    //локатор не активной вкладки "Начинки"
    @FindBy(how = How.XPATH, using = ".//div//span[text() ='Начинки']")
    private SelenideElement inActiveFillingTab;

    //локатор активной вкладки "Начинки"
    @FindBy(how = How.XPATH, using = ".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']//span[text() ='Начинки']")
    private SelenideElement fillingTab;

    //локатор раздела "Булки"
    @FindBy(how = How.XPATH, using = ".//h2[text() = 'Булки']")
    private SelenideElement bunsSection;

    //локатор раздела "Соусы"
    @FindBy(how = How.XPATH, using = ".//h2[text() = 'Соусы']")
    private SelenideElement sauceSection;

    //локатор раздела "Начинки"
    @FindBy(how = How.XPATH, using = ".//h2[text() = 'Начинки']")
    private SelenideElement fillingSection;

    //локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement homeLoginBtn;

    //локатор кнопки "Оформить заказ"
    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement createOrderBtn;

    //Методы домашней страницы
    @Step("Проверка, что название страницы соответствует ожидаемому")
    public HomePage checkFormTitle (String formTitle) {
        titleHomePage.shouldHave(exactText(formTitle));
        return this;
    }

    @Step("Проверка, что при загрузки ситраницы вкладка \"Булки\" активна")
    public SelenideElement isActiveBunsTab() {
        return bunsTab.shouldBe(exist);
    }

    @Step("Проверка, что по клику на неактивную вкладку \"Соусы\" на странице имеется активная вкладка \"Соусы\"")
    public SelenideElement isActiveSauceTab() {
        inActiveSauceTab.click();
        return sauceTab.shouldBe(exist);
    }

    @Step("Проверка, что по клику на неактивную вкладку \"Начинки\" на странице имеется активная вкладка \"Начинки\"")
    public SelenideElement isActiveFillingTab() {
        inActiveFillingTab.click();
        return fillingTab.shouldBe(exist);
    }

    @Step("Клик по кнопке \"Войти в аккаунт\"")
    public LoginPage clickToHomeLoginButton () {
        homeLoginBtn.click();
        return page(LoginPage.class);
    }

    @Step("Проверка, что отображается кнопка \"Войти в аккаунт\"")
    public HomePage isVisibleHomeLoginBtn () {
        homeLoginBtn.shouldHave(Condition.visible);
        return this;
    }

    @Step("Проверка, что отображается кнопка \"Оформить заказ\"")
    public HomePage isVisibleCreateOrderBtn () {
        createOrderBtn.shouldHave(Condition.visible);
        return this;
    }
}
