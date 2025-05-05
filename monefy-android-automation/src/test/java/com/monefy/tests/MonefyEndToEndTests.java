package com.monefy.tests;

import com.monefy.BaseTest;
import com.monefy.pages.MonefyPage;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Date;


public class MonefyEndToEndTests extends BaseTest {
    private MonefyPage monefyPage;
    Date date = new Date();


    //I was unable to open the app on the emulator, or inspect the element, the test is created by assumption)
    @Test
    public void addNewExpense() {

        monefyPage = new MonefyPage(driver);

        double currentBalance = monefyPage.getCurrentBalance();

        if (!monefyPage.isAccountExists("Cash")) {
            monefyPage.createAccount("Cash", 1000.00, date);
        }
        String newAmount = "50";
        monefyPage.clickAddExpenseButton();
        monefyPage.setAccountType("Cash");
        monefyPage.setExpenseAmount(newAmount);
        monefyPage.setExpenseCategory("Entertainment");

        double updatedBalance = monefyPage.getCurrentBalance();
        double expectedBalance = currentBalance - Double.parseDouble(newAmount);
        Assert.assertEquals(updatedBalance, expectedBalance);

        monefyPage.navigateToAccounts();
        double cashAccountBalance = monefyPage.getAccountBalance("Cash");
        Assert.assertEquals(cashAccountBalance, expectedBalance);

    }

    @Test
    public void addNewIncome() {
        monefyPage = new MonefyPage(driver);
        double currentBalance = monefyPage.getCurrentBalance();

        if (!monefyPage.isAccountExists("Cash")) {
            monefyPage.createAccount("Cash", 1000.00, date);
        }

        String newAmount = "50";
        monefyPage.clickAddIncomeButton();
        monefyPage.setAccountType("Cash");
        monefyPage.setIncomeAmount(newAmount);
        monefyPage.setIncomeCategory("Savings");

        double updatedBalance = monefyPage.getCurrentBalance();
        double expectedBalance = currentBalance + Double.parseDouble(newAmount);
        Assert.assertEquals(updatedBalance, expectedBalance);

        monefyPage.navigateToAccounts();
        double cashAccountBalance = monefyPage.getAccountBalance("Cash");
        Assert.assertEquals(cashAccountBalance, expectedBalance);
    }

    @Test
    public void testCreateAccountWithBalanceAndInclude() {
        monefyPage = new MonefyPage(driver);

        double initialBalance = monefyPage.getCurrentBalance();

        String newAccountName = "Savings";
        Double initialAccountBalance = 1000.00;
        if (!monefyPage.isAccountExists(newAccountName)) {
            monefyPage.createAccount(newAccountName, initialAccountBalance, date);
        }

        monefyPage.navigateToAccounts();
        double savingsAccountBalance = monefyPage.getAccountBalance(newAccountName);
        Assert.assertEquals(savingsAccountBalance, initialAccountBalance);

        double updatedBalance = monefyPage.getCurrentBalance();
        double expectedTotalBalance = initialBalance + initialAccountBalance;
        Assert.assertEquals(updatedBalance, expectedTotalBalance);
    }

}
