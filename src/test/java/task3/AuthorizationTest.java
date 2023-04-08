package task3;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AuthorizationTest extends AbstractTest{

    @Test
    @Tag("positive")
    void SucsessfulAuthorizationTest1(){
        given()
                .contentType("multipart/form-data")
                .multiPart("username", "Hom")
                .multiPart("password", "d82c0c322e")
                .when()
                .post(getBaseUrl() + AUTHORIZATION)
                .then()
                .spec(getResponseSpecification());
    }
    @Test
    @Tag("positive")
    void SucsessfulAuthorizationTest2(){
        given()
                .contentType("multipart/form-data")
                .multiPart("username", "login12345login67890")
                .multiPart("password", "88ff97c1c1")
                .when()
                .post(getBaseUrl() + AUTHORIZATION)
                .then()
                .spec(getResponseSpecification());
    }
    @Test
    @Tag("negative")
    @Description("со спецсимволами")
    void NotValidAuthorizationTest1(){
       JsonPath response =  given()
                .contentType("multipart/form-data")
                .multiPart("username", "ghjf.kl1,5")
                .multiPart("password", "ac420365cb")
                .when()
                .post(getBaseUrl() + AUTHORIZATION)
               .body()
                .jsonPath();
        assertThat(response.get("code"), equalTo(401));
        assertThat(response.get("error"), equalTo("Invalid credentials."));
    }
    @Test
    @Tag("negative")
    @Description("русский язык")
    void NotValidAuthorizationTest2(){
        JsonPath response =  given()
                .contentType("multipart/form-data")
                .multiPart("username", "русский")
                .multiPart("password", "72f72a0734")
                .when()
                .post(getBaseUrl() + AUTHORIZATION)
                .body()
                .jsonPath();
        assertThat(response.get("code"), equalTo(401));
        assertThat(response.get("error"), equalTo("Invalid credentials."));
    }
    @Test
    @Tag("negative")
    @Description("21 символ")
    void NotValidAuthorizationTest3(){
        JsonPath response =  given()
                .contentType("multipart/form-data")
                .multiPart("username", "jhgfdvbn67jug281hjk81")
                .multiPart("password", "161d25d16f")
                .when()
                .post(getBaseUrl() + AUTHORIZATION)
                .body()
                .jsonPath();
        assertThat(response.get("code"), equalTo(401));
        assertThat(response.get("error"), equalTo("Invalid credentials."));
    }
    @Test
    @Tag("negative")
    @Description("не введен логин")
    void NotValidAuthorizationTest4(){
        JsonPath response =  given()
                .contentType("multipart/form-data")
                .multiPart("username", "")
                .multiPart("password", "d82c0c322e")
                .when()
                .post(getBaseUrl() + AUTHORIZATION)
                .body()
                .jsonPath();
        assertThat(response.get("code"), equalTo(401));
        assertThat(response.get("error"), equalTo("Invalid credentials."));
    }
    @Test
    @Tag("negative")
    @Description("не введен пароль")
    void NotValidAuthorizationTest5(){
        JsonPath response =  given()
                .contentType("multipart/form-data")
                .multiPart("username", "Agent007")
                .multiPart("password", "")
                .when()
                .post(getBaseUrl() + AUTHORIZATION)
                .body()
                .jsonPath();
        assertThat(response.get("code"), equalTo(401));
        assertThat(response.get("error"), equalTo("Invalid credentials."));
    }
}
