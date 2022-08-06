package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends HeaderPage {

    //локатор названия страницы "Вход"
    @FindBy(how = How.XPATH, using = ".//h2[text()='Вход']")
    private SelenideElement titleLoginPage;

    //локатор поля "Email"
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement inputEmail;

    //локатор поля "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement inputPassword;

    //локатор текста ошибки "Некорректный пароль"
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement textPasswordError;

    //локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement loginBtn;

    //локатор ссылки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//a[@href='/register']")
    private SelenideElement registrationLink;

    //локатор ссылки "Восстановить пароль"
    @FindBy(how = How.XPATH, using = ".//a[@href='/forgot-password']")
    private SelenideElement passwordRecoveryLink;

    //методы страницы входа
    @Step("Проверка, что название страницы соответствует ожидаемому")
    public LoginPage checkFormTitle (String formTitle) {
        titleLoginPage.shouldHave(exactText(formTitle));
        return this;
    }

    @Step("Ввод email-a")
    public LoginPage setFieldEmail (String email) {
        inputEmail.setValue(email);
        return this;
    }

    @Step("Ввод пароля")
    public LoginPage setFieldPassword (String password) {
        inputPassword.setValue(password);
        return this;
    }

    @Step("Клик по кнопке \"Войти\"")
    public LoginPage clickToLoginButton () {
        loginBtn.click();
        return this;
    }

    @Step("Авторизация")
    public HomePage login (String email, String password) {
        setFieldEmail(email);
        setFieldPassword(password);
        clickToLoginButton();
        return page(HomePage.class);
    }

    @Step("Проверка, что название ошибки соответствует ожидаемому")
    public LoginPage checkTextError (String textError) {
        textPasswordError.shouldHave(exactText(textError));
        return this;
    }

    @Step("Клик по ссылке \"Зарегистрироваться\"")
    public RegisterPage clickToRegistrationLink() {
        registrationLink.click();
        return page(RegisterPage.class);
    }

    @Step("Клик по ссылке \"Восстановить пароль\"")
    public PasswordRecoveryPage clickToRecoveryLink () {
        passwordRecoveryLink.click();
        return page(PasswordRecoveryPage.class);
    }
}
