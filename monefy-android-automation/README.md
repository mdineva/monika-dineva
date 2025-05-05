# Petstore API Automation Framework
This project provides automated tests for the Monefy Android app using Appium. It leverages the Page Object Model (POM) pattern with Appium and TestNG for a clean structure and robust test execution.

## 🛠️ Tech Stack

- **Java 17** – Programming language
- **TestNG** – Test execution and assertions
- **Maven** - Build and dependency management
- **Appium** – For Android automation testing
- **Selenium WebDriver** – For UI element interactions via Appium
- **Android SDK** – For emulator and ADB setup

## 📋 Prerequisites
- **Java 17 JDK** (e.g., OpenJDK or Oracle JDK)
- **Maven** (3.6.3 or higher)
- **Android SDK** (with emulator and ADB tools)
- **Appium** (2.18.0 or higher)
- **An IDE** (e.g., IntelliJ IDEA, Eclipse) is recommended

### Verify installations:
<pre>
java -version  # Should show Java 17
mvn -version   # Should show Maven version
adb --version # Should show Android Debug Bridge version
appium --version  # Should show Appium version
</pre>


## ⚙️ Setup Instructions

1. **Set up Android Emulator**
- Ensure the Android SDK is installed and an emulator is configured (e.g., Pixel_6_API_33). 
- Start the emulator:
<pre> emulator -avd Pixel_8 </pre>
- Verify the emulator is running:
<pre>adb devices</pre>
-Install the Monefy app on the emulator via the Play Store.

2. **Set up Appium Server**
- Install Appium globally (if not installed)
  <pre> npm install -g appium</pre>


## 🚀 Running Tests
1. In IntelliJ IDEA, right-click src/test/java/com/monefy/tests/MonefyEndToEndTests.java > Run 'MonefyEndToEndTests'.
- Alternatively, use Maven (if configured): 
  <pre>mvn clean test</pre>


## ✍️ Approach & Design
- **Page Object Model (POM)**: The app’s UI screens (e.g., main screen, account creation) are encapsulated in classes like MonefyPage.java, which define methods for actions like adding expenses, creating accounts, and verifying balances, promoting reusability and maintainability.
- **Test Classes**: Each test scenario has a separate method in MonefyEndToEndTests.java (e.g., testAddExpense, testCreateAccountWithBalanceAndInclude) to keep tests focused and modular.
- **Base Test Setup**: BaseTest.java sets up reusable configurations such as the Appium driver and capabilities (e.g., appPackage, appActivity).
- **Locators and Actions**: All locators and interactions are managed in MonefyPage.java, keeping test logic clean and separate from UI details.
