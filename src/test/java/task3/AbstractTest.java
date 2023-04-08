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
public class AbstractTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
//    private static String apiKey;
    private static String baseUrl;

    public static final String AUTHORIZATION = "gateway/login";
    private static ResponseSpecification responseSpecification;

    @BeforeAll
    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        configFile = new FileInputStream("src/main/resources/test.properties");
        prop.load(configFile);

        baseUrl = prop.getProperty("base_url");
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType("text/plain;charset=UTF-8")
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();
        RestAssured.filters(new AllureRestAssured());
    }
    public static String getBaseUrl() {
        return baseUrl;
    }
    public ResponseSpecification getResponseSpecification(){
        return responseSpecification;
    }
}
