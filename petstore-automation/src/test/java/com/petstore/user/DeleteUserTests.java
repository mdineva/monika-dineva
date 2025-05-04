package com.petstore.user;

import com.petstore.BaseTest;
import com.petstore.model.User;
import com.petstore.pages.UserPage;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class DeleteUserTests extends BaseTest {
    private final UserPage userPage = new UserPage();
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
    public static final String INVALID_USERNAME_SUPPLIED = "Invalid username supplied";

    @Description("Delete already existing user")
    @Test
    public void deleteExistingUSer() {
        User user = createDefaultUser();
        createdUsername = user.getUsername();
        userPage.createUser(user)
                .then().statusCode(200);

        userPage.deleteUser(createdUsername)
                .then()
                .statusCode(200);

    }

    @Description("When deleting non existing user, a 404 User not found error msg is returned")
    @Test
    public void deleteNonExistingUser() {
        userPage.deleteUser(System.currentTimeMillis() + "Random")
                .then()
                .statusCode(404)
                .onFailMessage(USER_NOT_FOUND);
    }

    @Description("When deleting user with invalid username, a 400 Invalid username supplied error msg is returned")
    @Test
    public void deleteUserWithInvalidUsername() {
        userPage.deleteUser(System.currentTimeMillis())
                .then()
                .statusCode(400)
                .onFailMessage(INVALID_USERNAME_SUPPLIED);
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
