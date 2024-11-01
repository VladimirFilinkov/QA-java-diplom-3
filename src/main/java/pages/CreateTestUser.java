package pages;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.qameta.allure.model.Parameter.Mode.HIDDEN;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import java.time.LocalDateTime;

public class CreateTestUser {
    private String name;
    private String password;
    private String email;

    static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/";
    public static final String REGISTER_PATH = "auth/register";
    public static final String DELETE_PATH = "auth/user";
    public static final String AUTH_PATH = "auth/login";

    // Конструктор для создания пользователя
    public CreateTestUser(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    // Генерируем нового пользователя
    public static CreateTestUser randomUser() {
                return new CreateTestUser(
                "Vladimir" + LocalDateTime.now().getNano(),
                "P@ssword123",
                "mail" + LocalDateTime.now().getNano() + "@mail.ru"
        );
    }

    @Step("Создаем пользователя и извлекаем токен")
    public static String createUser(CreateTestUser user) {
        String requestBody = "{"
                + "\"email\": \"" + user.getEmail() + "\","
                + "\"password\": \"" + user.getPassword() + "\","
                + "\"name\": \"" + user.getName() + "\""
                + "}";

        ValidatableResponse response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(requestBody)
                .when()
                .post(REGISTER_PATH)
                .then()
                .log().all()
                .statusCode(HTTP_OK);
        return response.extract().path("accessToken");
    }

    //Логинимся пользователем и получаем токен
    public static String logInAndGetToken(CreateTestUser user) {
        String requestBodyFromPostman = "{"
                + "\"email\": \"" + user.getEmail() + "\","
                + "\"password\": \"" + user.getPassword() + "\""
                + "}";
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(requestBodyFromPostman)
                .when()
                .post(AUTH_PATH)
                .then()
                .extract()
                .path("accessToken");
    }

    @Step("Удаляем тестового пользователя")
    public static ValidatableResponse deleteUser(@Param(mode = HIDDEN) String accessToken) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .when()
                .delete(BASE_URL + DELETE_PATH)
                .then()
                .log().all();
    }
}