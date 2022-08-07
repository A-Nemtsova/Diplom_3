package site.nomoreparties.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage extends HeaderPage {

    //локатор названия страницы "Регистрация"
    @FindBy(how = How.XPATH, using = ".//h2[text()='Регистрация']")
    private SelenideElement titleRegisterPage;

    //локатор поля "Имя"
    @FindBy(how = How.XPATH, using = "(.//input)[1]")
    private SelenideElement inputName;

    //локатор поля "Email"
    @FindBy(how = How.XPATH, using = "(.//input)[2]")
    private SelenideElement inputEmail;

    //локатор поля "Пароль"
    @FindBy(how = How.XPATH, using = "(.//input)[3]")
    private SelenideElement inputPassword;

    //локатор текста ошибки "Некорректный пароль"
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement textPasswordError;

    //локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registrationButton;

    //локатор ссылки "Войти"
    @FindBy(how = How.XPATH, using = ".//a[@href='/login']")
    private SelenideElement loginLink;

    //методы страницы регистрации
    @Step("Проверка, что название страницы соответствует ожидаемому")
    public RegisterPage checkFormTitle (String formTitle) {
        titleRegisterPage.shouldHave(exactText(formTitle));
        return this;
    }

    @Step("Ввод имени")
    public RegisterPage setFieldName (String name) { ;
        inputName.setValue(name);
        return this;
    }

    @Step("Ввод email-a")
    public RegisterPage setFieldEmail (String email) { ;
        inputEmail.setValue(email);
        return this;
    }

    @Step("Ввод пароля")
    public RegisterPage setFieldPassword (String password) { ;
        inputPassword.setValue(password);
        return this;
    }

    @Step("Клик по кнопке \"Зарегистрироваться\"")
    public RegisterPage clickToRegistrationButton () {
        registrationButton.click();
        return this;
    }

    @Step("Регистрация с коротким паролем")
    public RegisterPage registrationWithErorr(String name, String email, String password) {
        setFieldName(name);
        setFieldEmail(email);
        setFieldPassword(password);
        clickToRegistrationButton();
        return this;
    }

    @Step("Регистрация с валидными данными")
    public LoginPage registration(String name, String email, String password) {
        setFieldName(name);
        setFieldEmail(email);
        setFieldPassword(password);
        clickToRegistrationButton();
        return (LoginPage) page(LoginPage.class);
    }

    @Step("Проверка, что название ошибки соответствует ожидаемому")
    public RegisterPage checkTextError (String textError) {
        textPasswordError.shouldHave(exactText(textError));
        return this;
    }

    @Step("Клик по ссылке \"Войти\"")
    public LoginPage clickToLoginLink() {
        loginLink.click();
        return (LoginPage) page(LoginPage.class);
    }
}

