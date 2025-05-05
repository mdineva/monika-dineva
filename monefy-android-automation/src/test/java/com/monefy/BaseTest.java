package com.monefy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("emulator-5554")
                .setAppPackage("com.monefy.app.lite")
                .setAppActivity("com.monefy.activities.main.MainActivity_")
                .setAutomationName("UiAutomator2")
                .setNoReset(true)
                .setAvd("Pixel_8");

        driver = new AndroidDriver(new URL("http://localhost:4723"), options);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
