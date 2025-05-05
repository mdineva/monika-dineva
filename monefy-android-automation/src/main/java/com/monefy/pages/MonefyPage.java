package com.monefy.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;

import java.time.Duration;
import java.util.Date;

import static java.lang.Double.parseDouble;

public class MonefyPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    private final By currentBalance = By.id("locator_current_balance");
    private final By addExpenseButton = By.id("locator_add_expense_button");
    private final By addIncomeButton = By.id("locator_add_income_button");
    private final By accountTypeField = By.id("locator_account_type");
    private final By expenseAmountField = By.id("locator_expense_amount");
    private final By incomeAmountField = By.id("locator_income_amount");
    private final By expenseCategoryField = By.id("locator_expense_category");
    private final By incomeCategoryField = By.id("locator_income_category");

    private final By dottedMenu = By.id("locator_dotted_menu");
    private final By getMenuList = By.id("locator_get_list");
    private final By addAccountButton = By.id("locator_add_account_button");
    private final By accountNameField = By.id("locator_account_name");
    private final By saveAccountButton = By.id("locator_save_account_button");
    private final By accountBalance = By.id("locator_account_balance");
    private final By accountBalanceField = By.id("locator_account_balance_field");
    private final By includeInBalanceToggle = By.id("locator_account_balance_toggle");
    private final By initialBalanceDate = By.id("locator_initial_Balance_Date");

    public MonefyPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public double getCurrentBalance() {
        String balanceText = wait.until(ExpectedConditions.visibilityOfElementLocated(currentBalance)).getText();
        return parseDouble(balanceText);
    }

    public void clickAddExpenseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addExpenseButton)).click();
    }

    public void clickAddIncomeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addIncomeButton)).click();
    }

    public void setAccountType(String accountType) {
        wait.until(ExpectedConditions.elementToBeClickable(accountTypeField)).sendKeys(accountType);
    }

    public void setExpenseAmount(String amount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(expenseAmountField)).sendKeys(amount);
    }

    public void setIncomeAmount(String amount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(incomeAmountField)).sendKeys(amount);
    }

    public void setExpenseCategory(String category) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(expenseCategoryField)).sendKeys(category);
    }

    public void setIncomeCategory(String category) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(incomeCategoryField)).sendKeys(category);
    }

    public boolean isAccountExists(String accountName) {
        By accountLocator = By.xpath("//*[@text='" + accountName + "']");
        return isElementPresent(accountLocator);
    }

    public void createAccount(String accountName, Double balance, Date date) {
        wait.until(ExpectedConditions.elementToBeClickable(dottedMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addAccountButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountNameField)).sendKeys(accountName);

        if (balance != null) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(accountBalanceField)).sendKeys(balance.toString());
        }

        if (isElementPresent(includeInBalanceToggle)) {
            WebElement toggle = wait.until(ExpectedConditions.visibilityOfElementLocated(includeInBalanceToggle));
            boolean isToggled = toggle.getAttribute("checked") != null && toggle.getAttribute("checked").equals("true");
            if (!isToggled) { // Enable the toggle if not already enabled
                toggle.click();
            }
        }

        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String dateString = dateFormat.format(date);
            wait.until(ExpectedConditions.visibilityOfElementLocated(initialBalanceDate)).sendKeys(dateString);
        }

        wait.until(ExpectedConditions.elementToBeClickable(addAccountButton)).click();
    }

    public void navigateToAccounts() {
        wait.until(ExpectedConditions.elementToBeClickable(dottedMenu)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(getMenuList)).sendKeys("Accounts");
    }

    public double getAccountBalance(String accountName) {
        WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@text='" + accountName + "']")));
        String balanceText = account.findElement(accountBalance).getText();
        return parseDouble(balanceText);
    }


    public boolean isElementPresent(By locator) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}