package com.petstore.user;

import com.petstore.BaseTest;
import com.petstore.model.User;
import com.petstore.services.UserService;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetUserTests extends BaseTest {
    private final UserService userService = new UserService();
    private String createdUsername;
    private static final int USER_ID = 12345;
    public static final String USERNAME = "User";
    public static final String FIRSTNAME = "Name";
    public static final String SURNAME = "Surname";
    public static final String EMAIL = "Name.Surname@gmail.com";
    public static final String PASSWORD = "pass1234";
    public static final String PHONE = "078123456";
    public static final int USER_STATUS = 1;
    public static final String USER_NOT_FOUND = "User not found";

    @Description("Get existing user by valid username")
    @Test
    public void getUserByUsername() {
        User user = createDefaultUser();
        createdUsername = user.getUsername();
        userService.createUser(user)
                .then().statusCode(200);

        userService.getUserByUsername(createdUsername)
                .then()
                .statusCode(200)
                .body("id", equalTo(user.getId()))
                .body("username", equalTo(user.getUsername()))
                .body("firstName", equalTo(user.getFirstName()))
                .body("lastName", equalTo(user.getLastName()))
                .body("email", equalTo(user.getEmail()))
                .body("password", equalTo(user.getPassword()))
                .body("phone", equalTo(user.getPhone()))
                .body("userStatus", equalTo(user.getUserStatus()));

    }

    @Description("When getting non existing user, 404 User not found error msg is returned")
    @Test
    public void getNonExistingUser() {
        userService.getUserByUsername(System.currentTimeMillis() + "Test")
                .then()
                .statusCode(404)
                .onFailMessage(USER_NOT_FOUND);
    }


    private User createDefaultUser() {
        User user = new User();
        user.setId(USER_ID);
        user.setUsername(USERNAME);
        user.setFirstName(FIRSTNAME);
        user.setLastName(SURNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setPhone(PHONE);
        user.setUserStatus(USER_STATUS);
        return user;
    }
}
