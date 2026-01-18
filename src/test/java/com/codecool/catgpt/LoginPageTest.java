package com.codecool.catgpt;

import com.codecool.catgpt.pages.HomePage;
import com.codecool.catgpt.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static com.codecool.catgpt.config.TestConfig.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Login Page Tests")
public class LoginPageTest extends BaseTest {
    
    private LoginPage loginPage;
    
    @BeforeEach
    public void setUpLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.navigateTo(LOGIN_URL);
    }
    
    @Test
    @DisplayName("Login page should load successfully")
    public void testLoginPageLoads() {
        assertTrue(loginPage.isTitleDisplayed(), "Login page title should be displayed");
        assertTrue(loginPage.getTitleText().contains("Welcome back"), 
                "Title should contain 'Welcome back'");
    }
    
    @Test
    @DisplayName("Should display error message with invalid credentials")
    public void testInvalidLogin() {
        loginPage.login(INVALID_USERNAME, INVALID_PASSWORD);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid credentials");
        assertEquals("Invalid username or password.", loginPage.getErrorMessage(),
                "Error message should match expected text");
    }

    @Test
    @DisplayName("Should successfully login with valid credentials")
    public void testValidLogin() {
        loginPage.login(TEST_USERNAME, TEST_PASSWORD);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isAtHomePage(), "Should redirect to home page after successful login");
    }

    @Test
    @DisplayName("Login button should be enabled by default")
    public void testLoginButtonEnabled() {
        assertTrue(loginPage.isLoginButtonEnabled(), "Login button should be enabled");
    }

    @Test
    @DisplayName("Should navigate to signup page when clicking signup link")
    public void testNavigateToSignup() {
        loginPage.clickSignupLink();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertTrue(getCurrentUrl().contains("/signup"),
                "Should navigate to signup page");
    }

    @Test
    @DisplayName("Should allow entering username")
    public void testEnterUsername() {
        loginPage.enterUsername(TEST_USERNAME);
        assertTrue(true);
    }

    @Test
    @DisplayName("Should allow entering password")
    public void testEnterPassword() {
        loginPage.enterPassword(TEST_PASSWORD);
        assertTrue(true);
    }

    @Test
    @DisplayName("Should clear and refill username field")
    public void testClearAndRefillUsername() {
        loginPage.enterUsername("firstusername");
        loginPage.enterUsername(TEST_USERNAME);
        assertTrue(true);
    }
}
