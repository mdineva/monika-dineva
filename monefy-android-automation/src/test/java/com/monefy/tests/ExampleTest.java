package com.monefy.tests;

import com.monefy.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;
import com.monefy.pages.ExamplePage;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class ExampleTest extends BaseTest {
    @Test
    public void testAddExpense() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //wait.until(ExpectedConditions.elementToBeClickable(By.id("com.monefy.app.lite:id/add_expense_button"))).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.monefy.app.lite:id/expense_amount"))).sendKeys("50");
        //wait.until(ExpectedConditions.elementToBeClickable(By.id("com.monefy.app.lite:id/save_button"))).click();
    }
}
