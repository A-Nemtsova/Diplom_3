package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.page;

public class PasswordRecoveryPage extends HeaderPage {

    //локатор названия страницы "Восстановление пароля"
    @FindBy(how = How.XPATH, using = ".//h2[text()='Восстановление пароля']")
    private SelenideElement titlePasswordRecoveryPage;

    //локатор поля "Email"
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement inputEmail;

    //локатор кнопки "Восстановить"
    @FindBy(how = How.XPATH, using = ".//button[text()='Восстановить']")
    private SelenideElement recoveryBtn;

    //локатор ссылки "Войти"
    @FindBy(how = How.XPATH, using = ".//a[@href='/login']")
    private SelenideElement loginLink;

    //методы страницы восстановления пароля
    @Step("Проверка, что название страницы соответствует ожидаемому")
    public PasswordRecoveryPage checkFormTitle (String formTitle) {
        titlePasswordRecoveryPage.shouldHave(exactText(formTitle));
        return this;
    }

    @Step("Ввод email-a")
    public PasswordRecoveryPage setFieldEmail (String email) { ;
        inputEmail.setValue(email);
        return this;
    }

    @Step("Клик по кнопке \"Восстановить\"")
    public PasswordRecoveryPage clickToRecoveryButton () {
        recoveryBtn.click();
        return this;
    }

    @Step("Восстановление пароля")
    public PasswordRecoveryPage recovery (String email) {
        setFieldEmail(email);
        clickToRecoveryButton();
        return this;
    }

    @Step("Клик по ссылке \"Войти\"")
    public LoginPage clickToLoginLink() {
        loginLink.click();
        return (LoginPage) page(LoginPage.class);
    }


}
