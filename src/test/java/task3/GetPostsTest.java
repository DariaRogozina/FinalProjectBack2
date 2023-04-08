package task3;
import dto.dto.Datum;
import dto.dto.Posts;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetPostsTest extends AbstractTest2 {
    @Test
    @Tag("positive")
    void GetPostsPage1Test() {
        given()
                .header("X-Auth-Token", getX_Auth_Token())
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", 1)
                .when()
                .get(getBaseUrl() + GET_POSTS)
                .then()
                .spec(getResponseSpecification());
    }
    @Test
    @Tag("positive")
    void GetPostsPage0Test() {
        given()
                .header("X-Auth-Token", getX_Auth_Token())
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "DESC")
                .queryParam("page", 0)
                .when()
                .get(getBaseUrl() + GET_POSTS)
                .then()
                .spec(getResponseSpecification());
    }
    @Test
    @Tag("positive")
    void GetPostsAllTest() {
        given()
                .header("X-Auth-Token", getX_Auth_Token())
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ALL")
                .queryParam("page", 10)
                .when()
                .get(getBaseUrl() + GET_POSTS)
                .then()
                .spec(getResponseSpecification());
    }
    @Test
    @Tag("positive")
    void GetPostsPage4000Test() {
        Posts response = given()
                .header("X-Auth-Token", getX_Auth_Token())
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", 4000)
                .when()
                .get(getBaseUrl() + GET_POSTS)
                .then()
                .extract()
                .response()
                .body()
                .as(Posts.class);
        assertThat(response.getData(), equalTo(new ArrayList<>()));
        assertThat(response.getMeta().getNextPage(), equalTo(null));
    }
    @Test
    @Tag("positive")
    void GetPostsDescTest() {
       given()
                .header("X-Auth-Token", getX_Auth_Token())
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "DESC ")
                .queryParam("page", 10)
                .when()
                .get(getBaseUrl() + GET_POSTS)
                .then()
               .spec(getResponseSpecification());
    }
    @Test
    @Tag("positive")
    void GetPostsDescWithoutSortTest() {
        given()
                .header("X-Auth-Token", getX_Auth_Token())
                .queryParam("owner", "notMe")
                .queryParam("order", "DESC ")
                .queryParam("page", 10)
                .when()
                .get(getBaseUrl() + GET_POSTS)
                .then()
                .spec(getResponseSpecification());
    }
    @Test
    @Tag("positive")
    void NotautorizedTest() {
        given()
                .queryParam("owner", "notMe")
                .queryParam("order", "DESC ")
                .queryParam("page", 10)
                .when()
                .get(getBaseUrl() + GET_POSTS)
                .then()
                .statusCode(401);
    }
    @Test
    @Tag("positive")
    void GetMyPostsPage1Test() {
        Posts response = given()
                .header("X-Auth-Token", getX_Auth_Token())
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", 1)
                .when()
                .get(getBaseUrl() + GET_POSTS)
                .then()
                .extract()
                .response()
                .body()
                .as(Posts.class);
        assertThat(response.getData().get(0).getTitle(), equalTo("первый пост"));
        assertThat(response.getData().get(1).getTitle(), equalTo("Пост второй"));
        assertThat(response.getData().get(1).getDescription(), equalTo("Программы так и пишутся"));
        assertThat(response.getData().get(1).getMainImage().getCdnUrl(), equalTo("http://test-stand.gb.ru/files/public/image/0351358c4b8813a7f740bd8c36412c27.jpg"));
    }
    @Test
    @Tag("positive")
    void GetMyPostsPage0Test() {
        given()
                .header("X-Auth-Token", getX_Auth_Token())
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", 0)
                .when()
                .get(getBaseUrl() + GET_POSTS)
                .then()
                .spec(getResponseSpecification());
    }
    @Test
    @Tag("positive")
    void GetMyPostsDescTest() {
        Posts response = given()
                .header("X-Auth-Token", getX_Auth_Token())
                .queryParam("sort", "createdAt")
                .queryParam("order", "DESC")
                .queryParam("page", 1)
                .when()
                .get(getBaseUrl() + GET_POSTS)
                .then()
                .extract()
                .response()
                .body()
                .as(Posts.class);
        assertThat(response.getData().get(0).getTitle(), equalTo("Пост пятнадцатый"));
        assertThat(response.getData().get(0).getMainImage().getCdnUrl(), equalTo(""));
        assertThat(response.getData().get(1).getTitle(), equalTo(""));
        assertThat(response.getData().get(1).getDescription(), equalTo("Стресс-тесты они такие"));
        assertThat(response.getData().get(1).getMainImage().getCdnUrl(), equalTo("http://test-stand.gb.ru/files/public/image/e14d05de05ddfe25ad416c89e7ab3f76.jpg"));
    }
    @Test
    @Tag("positive")
    void GetMyPostsNoPageTest() {
        Posts response = given()
                .header("X-Auth-Token", getX_Auth_Token())
                .queryParam("sort", "createdAt")
                .queryParam("order", "DESC")
                .queryParam("page", 10)
                .when()
                .get(getBaseUrl() + GET_POSTS)
                .then()
                .extract()
                .response()
                .body()
                .as(Posts.class);
        assertThat(response.getData(), equalTo(new ArrayList<>()));
        assertThat(response.getMeta().getNextPage(), equalTo(null));
    }

    @Test
    @Tag("positive")
    void GetMyPostsDescWithoutSortTest() {
        given()
                .header("X-Auth-Token", getX_Auth_Token())
                .queryParam("order", "DESC ")
                .queryParam("page", 1)
                .when()
                .get(getBaseUrl() + GET_POSTS)
                .then()
                .spec(getResponseSpecification());
    }
    @Test
    @Tag("positive")
    void NotautorizedMyTest() {
        given()
                .queryParam("order", "DESC ")
                .queryParam("page", 1)
                .when()
                .get(getBaseUrl() + GET_POSTS)
                .then()
                .statusCode(401);
    }
}
