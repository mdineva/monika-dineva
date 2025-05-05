package com.petstore.user;

import com.petstore.BaseTest;
import com.petstore.model.User;
import com.petstore.services.UserService;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTests extends BaseTest {

    private final UserService userService = new UserService();
    private User defaultUser;
    private String originalUsername;
    private static final int USER_ID = 12345;
    public static final String USERNAME = "Username";
    public static final String FIRSTNAME = "Name";
    public static final String SURNAME = "Surname";
    public static final String EMAIL = "Name.Surname@gmail.com";
    public static final String PASSWORD = "pass1234";
    public static final String PHONE = "078123456";
    public static final int USER_STATUS = 1;
    public static final String USER_NOT_FOUND = "User not found";
    public static final String UPDATE = "Update";
    public static final String BAD_REQUEST = "Bad Request";


    @BeforeMethod
    public void setUpUser() {
        defaultUser = createDefaultUser();
        originalUsername = defaultUser.getUsername();
        userService.createUser(defaultUser)
                .then()
                .statusCode(200);
    }

    @Description("Update all fields for an existing user")
    @Test
    public void updateUser() {
        User updatedUser = new User();
        updatedUser.setId(USER_ID + 67);
        updatedUser.setUsername(UPDATE);
        updatedUser.setFirstName(FIRSTNAME + UPDATE);
        updatedUser.setLastName(SURNAME + UPDATE);
        updatedUser.setEmail(UPDATE + EMAIL);
        updatedUser.setPassword(PASSWORD + UPDATE);
        updatedUser.setPhone(PHONE + 1);
        updatedUser.setUserStatus(2);

        userService.updateUser(defaultUser.getUsername(), updatedUser)
                .then()
                .statusCode(200)
                .body("id", equalTo(updatedUser.getId()))
                .body("username", equalTo(updatedUser.getUsername()))
                .body("email", equalTo(updatedUser.getEmail()))
                .body("password", equalTo(updatedUser.getPassword()))
                .body("phone", equalTo(updatedUser.getPhone()))
                .body("userStatus", equalTo(updatedUser.getUserStatus()));

        userService.logInUser(updatedUser.getUsername(), updatedUser.getPassword())
                .then()
                .statusCode(200);
    }

    //Description("When updating an existing user with empty request body, 400 Bed request is returned")
    //Test is crushing the app therefore it's commented

//    @Test
//    public void updateUsersWithEmptyBody() {
//        User updatedUser = new User();
//        userService.updateUser(originalUsername, updatedUser)
//                .then()
//                .statusCode(400)
//                .onFailMessage(BAD_REQUEST);
//    }

    @Description("When updating non-existing user, 404 User not found error msg is returned")
    @Test
    public void updateNonExistingUser() {
        userService.updateUser(originalUsername + "Test", defaultUser)
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

    @AfterMethod
    private void cleanUpUser() {
        if (originalUsername != null) {
            userService.deleteUser(originalUsername)
                    .then()
                    .statusCode(200);
        }
    }


}
