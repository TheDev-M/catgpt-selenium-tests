# CatGPT Selenium Test Suite

This project contains automated Selenium tests for the CatGPT application using the Page Object Model (POM) design pattern.

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- Firefox browser installed
- CatGPT application running on `http://localhost:3000`

## Project Structure

```
catgpt-selenium-tests/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── codecool/
│   │               └── catgpt/
│   │                   ├── config/
│   │                   │   └── TestConfig.java        # Configuration constants
│   │                   └── pages/
│   │                       ├── BasePage.java          # Base POM class
│   │                       ├── LoginPage.java         # Login page POM
│   │                       ├── SignupPage.java        # Signup page POM
│   │                       ├── HomePage.java          # Home page POM
│   │                       ├── CatBoxPage.java        # Cat Box page POM
│   │                       └── CatDeatilsPage.java    # Cat Details page POM
│   └── test/
│       └── java/
│           └── com/
│               └── codecool/
│                   └── catgpt/
│                       ├── BaseTest.java              # Base test class
│                       ├── LoginPageTest.java         # Login tests
│                       ├── SignupPageTest.java        # Signup tests
│                       ├── CatBoxPageTest.java        # Cat Box tests
│                       ├── CatDetailsTest.java        # Cat Details tests
│                       ├── InventoryTest.java         # Inventory tests
│                       ├── CatCatchingTest.java       # Cat Cathing tests
│                       ├── ItemCathingTest.java       # Item Catching tests
│                       ├── NavigationTest.java        # Navigation tests
│                       └── EndToEndTest.java          # E2E tests
└── pom.xml
```

## Selector Strategy

The Page Object Models use **ID-based selectors** for maximum stability and clarity.

### ID-Based Approach:
All critical elements in the CatGPT frontend have been given unique `id` attributes specifically for testing:

**Benefits:**
1. **Fast and Reliable** – ID selectors are the fastest CSS selectors
2. **Unique** – IDs are guaranteed to be unique on each page
3. **Stable** – Resistant to CSS/styling changes
4. **Clear** - Descriptive IDs make tests self-documenting
5. **Maintainable** – Easy to find and update

## Configuration

Update `TestConfig.java` to modify:
- **BASE_URL**: Application base URL (default: `http://localhost:3000`)
- **LOGIN_URL**: Login page URL
- **SIGNUP_URL**: Signup page URL
- **HOME_URL**: Home page URL
- **CATBOX_URL**: Cat Box page URL
- **TEST_USERNAME**: Username for existing test user
- **TEST_PASSWORD**: Password for existing test user
- **Timeouts**: Implicit, explicit, and page load timeouts

## Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific test class
```bash
mvn test -Dtest=LoginPageTest
mvn test -Dtest=SignupPageTest
mvn test -Dtest=CatBoxPageTest
mvn test -Dtest=NavigationTest
mvn test -Dtest=EndToEndTest
```

### Run specific test method
```bash
mvn test -Dtest=LoginPageTest#testValidLogin
mvn test -Dtest=NavigationTest#testCompleteNavigationFlow
```

## Dependencies

- **Selenium WebDriver 4.27.0**: Browser automation
- **WebDriverManager 5.9.2**: Automatic driver management
- **JUnit 5.11.4**: Testing framework
- **SLF4J 2.0.16**: Logging

## Test Data Requirements

Before running tests, ensure:
1. The CatGPT application is running on `http://localhost:3000`
2. A test user exists with credentials defined in `TestConfig.java`:
   - Username: `testuser`
   - Password: `Test1234!`
3. The application database is accessible and functioning
4. The backend API is running and responding

## Troubleshooting

### FirefoxDriver issues
- WebDriverManager automatically downloads the correct FirefoxDriver version
- Ensure Firefox browser is installed and up to date

### Test failures
- Verify the CatGPT application is running on `http://localhost:3000`
- Check that the BASE_URL in TestConfig matches your setup
- Ensure test credentials are valid (create test user if needed)
- Check browser console for JavaScript errors
- Verify backend API is running

### Selector issues
- The selectors are based on the actual frontend code
- If frontend changes, selectors may need updates
- Check browser DevTools to verify element attributes
- Use `aria-label`, `autocomplete`, and `type` attributes when available

### Timeout issues
- Increase timeout values in `TestConfig.java` if needed
- Check network speed and application performance
- Verify backend API response times
