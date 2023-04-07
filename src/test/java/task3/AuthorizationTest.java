package task3;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestStandTest extends AbstractTest{
//    public static String X_Auth_Token;
//
//    public static String getX_Auth_Token() {
//        return X_Auth_Token;
//    }

    @Test
    @Tag("positive")
    String SucsessfulAuhorizationTest(){
        String X_Auth_Token = given()
                .contentType("multipart/form-data")
                .multiPart("username", "jamesb007")
                .multiPart("password", "ae7b01d6eb")
                .when()
                .post(getBaseUrl() + AUTHORIZATION)
                .then()
                .spec(getResponseSpecification())
                .extract()
                .jsonPath()
                .get("token")
                .toString();
        System.out.println(X_Auth_Token);
        return  X_Auth_Token;
    }
}
