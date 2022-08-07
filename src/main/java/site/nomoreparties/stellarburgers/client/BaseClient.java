package site.nomoreparties.stellarburgers.client;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseClient {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public static RequestSpecification getSpecForJson(String accessToken) {
        return  new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", accessToken)
                .setBaseUri(BASE_URL)
                .build();
    }
}
