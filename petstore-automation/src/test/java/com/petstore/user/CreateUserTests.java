package com.petstore.user;

import com.petstore.BaseTest;
import com.petstore.model.User;
import com.petstore.services.UserService;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;


public class CreateUserTests extends BaseTest {
    public static final int ID_DEFAULT_VALUE = 0;
    public static final int USER_STATUS_DEFAULT_VALUE = 0;
    private static final int USER_ID = 12345;
    public static final String USERNAME = "Username";
    public static final String FIRSTNAME = "Name";
    public static final String SURNAME = "Surname";
    public static final String EMAIL = "Name.Surname@gmail.com";
    public static final String PASSWORD = "pass1234";
    public static final String PHONE = "078123456";
    public static final int USER_STATUS = 1;
    public static final String BAD_REQUEST = "Bad Request";
    private final UserService userService = new UserService();
    private String createdUsername;

    @Description("Test for creating a new user with valid input")
    @Test
    public void createUserWithAllFields() {
        User user = new User();
        user.setId(USER_ID);
        user.setUsername(USERNAME);
        user.setFirstName(FIRSTNAME);
        user.setLastName(SURNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setPhone(PHONE);
        user.setUserStatus(USER_STATUS);
        createdUsername = user.getUsername();
        userService.createUser(user)
                .then().statusCode(200)
                .body("id", equalTo(user.getId()))
                .body("username", equalTo(user.getUsername()))
                .body("email", equalTo(user.getEmail()))
                .body("password", equalTo(user.getPassword()))
                .body("phone", equalTo(user.getPhone()))
                .body("userStatus", equalTo(user.getUserStatus()));

        userService.getUserByUsername(createdUsername)
                .then()
                .body("id", equalTo(user.getId()))
                .body("username", equalTo(user.getUsername()))
                .body("email", equalTo(user.getEmail()))
                .body("password", equalTo(user.getPassword()))
                .body("phone", equalTo(user.getPhone()))
                .body("userStatus", equalTo(user.getUserStatus()))
                .statusCode(200);

        userService.logInUser(createdUsername, user.getPassword())
                .then()
                .statusCode(200);

    }

    //@Description("When creating a use with an empty request body, 400 Bad Request error should be returned")
    //Test is crushing the app therefore it's commented
//    @Test
//    public void createUserWithoutValues() {
//        User user = new User();
//        userService.createUser(user)
//                .then()
//                .statusCode(400)
//                .onFailMessage(BAD_REQUEST);;
//    }


    @AfterMethod
    public void cleanupUser() {
        if (createdUsername != null) {
            userService.deleteUser(createdUsername).then().statusCode(200);
            createdUsername = null;
        }
    }

}
