package site.nomoreparties.stellarburgers.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import site.nomoreparties.stellarburgers.model.RequestForLogin;
import site.nomoreparties.stellarburgers.model.RequestForRegistration;

import static io.restassured.RestAssured.*;

public class UserClient extends BaseClient{
    String email;
    String name;
    String password;
    String token;

    @Step("Создание пользователя")
    public Response registration(RequestForRegistration requestForRegistration) {
        return given()
                .spec(getSpecForJson(""))
                .body(requestForRegistration)
                .when()
                .post(BASE_URL + "/api/auth/register");
    }

    @Step("Авторизация пользователя")
    public Response loginUser(RequestForLogin requestForLogin) {
        return given()
                .spec(getSpecForJson(""))
                .body(requestForLogin)
                .when()
                .post(BASE_URL + "/api/auth/login");
    }

    @Step("Регистрация и получение accessToken")
    public String getTokenAfterRegistration(RequestForRegistration requestForRegistration) {
        return registration(requestForRegistration).body().jsonPath().getString("accessToken");
    }

    @Step("Авторизация и получение accessToken")
    public String getTokenAfterLogin(RequestForLogin requestForLogin) {
        Response response = loginUser(requestForLogin);
        if (response.statusCode() == 200) {
            token = loginUser(requestForLogin).body().jsonPath().getString("accessToken");
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
