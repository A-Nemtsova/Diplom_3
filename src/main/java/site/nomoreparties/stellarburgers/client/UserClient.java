package site.nomoreparties.stellarburgers.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import static io.restassured.RestAssured.*;

public class UserClient extends BaseClient{
    String email;
    String name;
    String password;
    String token;

    public String getRandomEmail() {
        email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
        return email;
    }

    public String getRandomName() {
        name = RandomStringUtils.randomAlphabetic(10);
        return name;
    }

    public String getRandomPassword() {
        password = RandomStringUtils.randomAlphabetic(10);
        return password;
    }

    @Step("Создание пользователя")
    public Response registration(String email, String name, String password) {
        String json = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"" + name + "\"}";
        return given()
                .spec(getSpecForJson(""))
                .body(json)
                .when()
                .post(BASE_URL + "/api/auth/register");
    }

    @Step("Авторизация пользователя")
    public Response loginUser(String email, String password) {
        String json = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}";
        return given()
                .spec(getSpecForJson(""))
                .body(json)
                .when()
                .post(BASE_URL + "/api/auth/login");
    }

    @Step("Регистрация и получение accessToken")
    public String getTokenAfterRegistration(String email, String name, String password) {
        return registration(email, name, password).body().jsonPath().getString("accessToken");
    }

    @Step("Авторизация и получение accessToken")
    public String getTokenAfterLogin(String email, String password) {
        Response response = loginUser(email, password);
        if (response.statusCode() == 200) {
            token = loginUser(email, password).body().jsonPath().getString("accessToken");
        } else {
            token = "";
        }
        return token;
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken) {
        return given()
                .spec(getSpecForJson(accessToken))
                .when()
                .delete(BASE_URL + "/api/auth/user");
    }
}
