package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.page;

public class LkPage extends HeaderPage{

    //локатор вкладки "Профиль"
    @FindBy (how = How.LINK_TEXT, using = "Профиль")
    private SelenideElement profileLink;

    //локатор вкладки "История заказов"
    @FindBy (how = How.LINK_TEXT, using = "История заказов")
    private SelenideElement orderHistoryLink;

    //локатор кнопки "Выход"
    @FindBy (how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement logoutBtn;

    //локатор описания раздела "В этом разделе вы можете изменить свои персональные данные"
    @FindBy (how = How.XPATH, using = ".//p[contains(text(),'В этом разделе вы можете')]")
    private SelenideElement description;

    //методы Личного кабинета
    @Step("Проверка, что описание страницы соответствует ожидаемому")
    public LkPage checkFormDescription (String formTitle) {
        description.shouldHave(exactText(formTitle));
        return this;
    }

    @Step("Клик по кнопке \"Выход\"")
    public LoginPage clickToLogout () {
        logoutBtn.click();
        return (LoginPage) page(LoginPage.class);
    }


}
