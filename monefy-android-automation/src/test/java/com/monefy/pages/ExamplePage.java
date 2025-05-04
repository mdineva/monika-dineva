package com.monefy.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.PageFactory;

public class ExamplePage {
    private AndroidDriver driver;

    public ExamplePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
