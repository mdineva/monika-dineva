package com.petstore;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public static final String URL = "http://localhost:8080/api/v3";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
