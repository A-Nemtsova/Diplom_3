package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.page;

public class HomePage extends HeaderPage {

    public final static String URL = "https://stellarburgers.nomoreparties.site";

    //локатор названия страницы "Соберите бургер"
    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    private SelenideElement titleHomePage;

    //локатор вкладки "Булки"
    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunsTab;

    //локатор вкладки "Соусы"
    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement sauceTab;

    //локатор вкладки "Начинки"
    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
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

    @Step("Проверка, что вкладка \"Булки\" имеет определенный cssClass")
    public boolean isActiveBunsTab()  {
        bunsSection.scrollIntoView(true);
        return bunsTab.ancestor("div").getAttribute("class").equals("tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect");
    }

    @Step("Проверка, что вкладка \"Соусы\" имеет определенный cssClass")
    public boolean isActiveSauceTab() {
       sauceSection.scrollIntoView(true);
        return sauceTab.ancestor("div").getAttribute("class").equals("tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect");
    }

    @Step("Проверка, что вкладка \"Начинки\" имеет определенный cssClass")
    public boolean isActiveFillingTab() {
        fillingSection.scrollIntoView(true);
        return fillingTab.ancestor("div").getAttribute("class").equals("tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect");
    }

    @Step("Клик по кнопке \"Войти в аккаунт\"")
    public LoginPage clickToHomeLoginButton () {
        homeLoginBtn.click();
        return (LoginPage) page(LoginPage.class);
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
