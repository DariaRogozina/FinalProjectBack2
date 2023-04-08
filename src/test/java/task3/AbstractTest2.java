package task3;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class AbstractTest2 {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String X_Auth_Token;
    private static String baseUrl;
    public static final String GET_POSTS = "api/posts";
    public static final String AUTHORIZATION = "gateway/login";
    private static ResponseSpecification responseSpecification;
//    private static RequestSpecification requestSpecification;

    @BeforeAll
    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        configFile = new FileInputStream("src/main/resources/test.properties");
        prop.load(configFile);
        baseUrl = prop.getProperty("base_url");
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();
        RestAssured.filters(new AllureRestAssured());
        X_Auth_Token = given()
                .contentType("multipart/form-data")
                .multiPart("username", "jamesb007")
                .multiPart("password", "ae7b01d6eb")
                .when()
                .post(getBaseUrl() + AUTHORIZATION)
                .then()
                .assertThat()
                .statusLine("HTTP/1.1 200 OK")
                .contentType("text/plain;charset=UTF-8")
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("token")
                .toString();
    }
    public static String getX_Auth_Token() {
        return X_Auth_Token;
    }
    public static String getBaseUrl() {
        return baseUrl;
    }
    public ResponseSpecification getResponseSpecification() {
        return responseSpecification;
    }
}

