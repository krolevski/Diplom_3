package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.User;

import static io.restassured.RestAssured.given;

public class UserApi {
    final static String LOGIN_USER = "/api/auth/login";
    final static String DELETE_USER = "/api/auth/user";
    final static String CREATE_USER = "/api/auth/register";

    @Step("Авторизация пользователя")
    public Response authUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(LOGIN_USER);
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        given()
                .header("authorization", accessToken)
                .when()
                .delete(DELETE_USER);
    }

    @Step("Создание пользователя")
    public Response creatingUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(CREATE_USER);
    }
}
