package com.petstore.pages;

import com.petstore.model.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserPage {

    private final String USER_ENDPOINT = "/user";
    private final String LOGIN = USER_ENDPOINT + "/login";
    public final String USERNAME = "/{username}";

    public Response createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(USER_ENDPOINT);
    }

    public Response getUserByUsername(String username) {
        return given()
                .pathParam("username", username)
                .when()
                .get(USER_ENDPOINT + USERNAME);
    }

    public Response updateUser(String username, User user) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("username", username)
                .body(user)
                .when()
                .put(USER_ENDPOINT + USERNAME);
    }

    public Response deleteUser(Object username) {
        return given()
                .pathParam("username", username)
                .when()
                .delete(USER_ENDPOINT + USERNAME);
    }

    public Response logInUser(String username, String password) {
        return given()
                .queryParams("username", username, "password", password)
                .when()
                .get(LOGIN);
    }
}
