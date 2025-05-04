# Petstore API Automation Framework
This project provides automated tests for CRUD operations on the USER endpoint of the Petstore API. It leverages the Page Object Model (POM) pattern with REST Assured, TestNG, and Allure for a clean structure, robust test execution, and detailed reporting.

## 🛠️ Tech Stack

- **Java 17** – Programming language
- **REST-assured** – For API testing
- **TestNG** – Test execution and assertions
- **Jackson** – JSON serialization/deserialization
- **Hamcrest** – Matchers and assertions
- **Allure** – Rich test reports
- **Maven** – Build and dependency management
- **Docker** - For running the API service locally

## 📋 Prerequisites
- **Java 17 JDK** (e.g., OpenJDK or Oracle JDK)
- **Maven** (3.6.3 or higher)
- **Docker** (for local API setup)
- **Allure** (for reporting)
- **Git** (for cloning the repository)
- **An IDE** (e.g., IntelliJ IDEA, Eclipse) is recommended

### Verify installations:
<pre>
java -version  # Should show Java 17
mvn -version   # Should show Maven version
docker --version
allure --version
</pre>


## ⚙️ Setup Instructions

1. **Clone the repository**
<pre> git clone https://github.com/your-repo/petstore-automation.git cd petstore-automation </pre>

2. **Set up Petstore API locally using Docker**
<pre> docker pull swaggerapi/petstore3:unstable
 docker run  --name swaggerapi-petstore3 -d -p 8080:8080 swaggerapi/petstore3:unstable </pre>
- Validate the container is running: <pre> docker ps </pre>
- Stop or restart the container if needed: <pre>docker stop swaggerapi-petstore3 
docker restart swaggerapi-petstore3 </pre>

3. **Install dependencies**
   Ensure Maven and Java 17 are installed, then build the project:
   <pre> mvn clean install </pre>

4. Install Allure (if not installed)
   Allure CLI is required for allure serve or allure generate. Install it based on your OS:
   - macOS <pre> brew install allure </pre>
   - Windows: Download from Allure Releases and add to PATH. 
   - Linux: Use package manager or download manually (see Allure documentation). 
   - **Note:** If Allure CLI is not installed, use mvn allure:serve for reports (requires Allure Maven plugin).


## 🚀 Running Tests
1. To execute the test suite <pre> mvn clean test </pre>
   Tests follow the **/*Tests.java naming convention and are located in src/test/java.
2. To generate and view an Allure report 
   <pre> allure serve target/allure-results 
    allure generate target/allure-results -o target/allure-report </pre>


## ✍️ Approach & Design
- **Page Object Model (POM)**: The API endpoints (e.g., /user) are encapsulated in classes like UserPage.java, which define methods for GET, POST, PUT, and DELETE operations, promoting reusability and maintainability.
- **Test Classes**: Each CRUD operation has a separate test class (CreateUserTests, UpdateUserTests, etc.) to keep tests focused and modular. 
- **Base Test Setup**: BaseTest.java sets up reusable configurations such as base URI. 
- **Model Layer**: Java POJOs in the model package (e.g., User.java) are used for request/response data binding using Jackson. 
- **Allure Reports**: Integrated via the Allure TestNG adapter and AspectJ Weaver for interactive HTML reports with test details.
