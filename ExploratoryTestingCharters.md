# Monify Exploratory Testing

Exploratory testing of the Monefy app focused on core financial features, usability, and data accuracy, conducted on an iPhone 16 running iOS 18.4.1.

### Charter 1 - Explore adding and managing expenses and income
**Activities:**
- Add expense with "-" button or directly by selecting the category
- Add income with "+" button, set default category and verify correct acc balance is updated
  - Try to add income with category different from default (paid app option)
- Add multiple expenses and incomes and verify sum under a given expense filter (by default on monthly level and all)
- Add multiple expenses from the same category and verify sum values
- Validate the pie chart % distribution is accurate
- Update, Delete expense/income
- View list view of inputs and sort by date added/value
- Delete all expenses in a given month and verify filters → check if empty months are still shown
- Verify negative, no value, 0 value income/expense edge cases

**Priority:** High – core app functionality. Inaccuracies here directly impact trust in financial tracking.

**Observations:**
- CRUD operations for expense and income works as expected 
- Pie chart % visualization works correctly
- Deleting all entries in a month still leaves the month in view (UX/UI issue)

**Issues:**

| Bug                                                                                                                                                        |    Type    |    Priority |
|:-----------------------------------------------------------------------------------------------------------------------------------------------------------|:----------:|------------:|
| Able to add future income/expense                                                                                                                          | Functional | Medium/High |
| Adding expense with €0 marks field red but no error message                                                                                                |     UX     |      Medium |
| Deleting record has no confirmation prompt                                                                                                                 |     UX     |        High |
| Delete record flow has multiple points of improvements and can be treated as bugs: mutliple selection delete, swipe left iOS gesture, no delete-all option |     UX     |         Low |
| Basic calculator functions are enabled in amount field                                                                                                     | Functional | High/Medium |
| When adding multiple expenses from different categories, some connector lines disappear from the pie chart                                                 |     UX     |         Low |


### Charter 2 - Managing Accounts and Account Balances

**Activities:**
- Set initial balances in default (cash/visa) accounts. Verify proper update after income/expense is added
- Add another account and include/exclude them from the total balance. Verify if balance is properly updated
- Observe the acc balance when filter is set to default monthly representation, and set initial balance date
- Explore CRUD operations on the account
- Delete all accounts and verify behaviour when trying to add income/expense

**Priority:** High – core financial logic. Unclear and inconsistent representation of the acc balance leads to mistrust and errors.

**Observations:** 
- Potential confusion due to balance settings (with or without carryover enabled, and monthly filter selected).
- Lack of feedback when trying to create income/expanse without an account set.

### Charter 3 - Explore filter functionality

**Activities:**
- Use the default “Monthly” filter. Adding activity in multiple months to verify. Verify ifference in calculations if carryover is enabled or not (each month should have its calculation vs. the combined calculation)
- Verify  all other filters
- Check edge cases such as filtering on specific dates with or without transactions, if from to includes the transactions from those days etc.

**Priority:** Medium – Accurate filtering is crucial for a good user experience and essential to maintaining trust in financial analysis.

**Observations:**
- UX can be improved when there is not data matched for a specific filter

**Issues:**

| Bug                                                                                                                                                        |    Type    |    Priority |
|:-----------------------------------------------------------------------------------------------------------------------------------------------------------|:----------:|------------:|
| When trying to select interval for 2 consecutive days to filter on, 3 days are selected on the calendar                                                    | Functional |      High   |

### Charter 4 - Explore Balance and General settings

**Activities:**
- Set up monthly budget and verify correct calculations on already added transactions
- Enable/disable carryover and observe balance changes
- General settings: change of language, currency, First day of week set and first day of month

**Priority:** Medium - Budget functionality can even be considered as high for budget-conscious users. Effective work of general settings improves the user experience. 

**Observations:**
- When budget is set, acc balance is not shown which can lead to confusion
- General settings work as expected

### Charter 5 - Account Transfers

**Activities:**
- Explore amount transfer from one to another account. Covering edge cases to transfer more money than available.
- Verify balances reflect transfer accurately in both list and detailed views
- 
**Priority:** Medium - Useful functionality that improves user clarity and convenience but it doesn't represent a true transactional link and can be substituted by manual balance updates.

**Observations:** 
- Transfers allow negative balances, possibly unintended. Room for improvement
- Inconsistency between account list and detailed views after transfer

| Bug                                                                                                                                              |    Type    |    Priority |
|:-------------------------------------------------------------------------------------------------------------------------------------------------|:----------:|------------:|
| When user transfers more money than available to another acc, the initial account has negative balance                                           | Functional |      High   |
| After transfer is completed, in list accounts values are updated but in detailed account view, balance is not updated (stays with initial value) | Functional |      High   |


## Other issues:
- **High:** Lack of authentication – the app can be accessed without any user login, represents a potential security risk. 
- Medium: UX/UI improvements needed – unclear which features are premium, limited feedback on filter actions, and overall design could benefit from modernization.

## Risks that need to be mitigated are:
- **Data Accuracy and Integrity (High):**
  - Inaccurate calculations
  - Filtering errors that may misrepresent financial data
  - Other inconsistencies (carryovers, budget etc.) that can lead to unrealistic financial status
- **Security Risks (High):**
  - Lack of authentication can potentially exposes financial data to unauthorized access 
  - Potential data leakage without encryption or proper session handling
- **Usability issues (Medium):**
  - Missing input validations
  - Lack of warnings for invalid actions
  - Confusing user flows
  - Insufficient feedback after user actions






