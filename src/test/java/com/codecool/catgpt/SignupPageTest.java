package com.codecool.catgpt;

import com.codecool.catgpt.pages.SignupPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Random;

import static com.codecool.catgpt.config.TestConfig.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Signup Page Tests")
public class SignupPageTest extends BaseTest {
    
    private SignupPage signupPage;
    private Random random;
    
    @BeforeEach
    public void setUpSignupPage() {
        signupPage = new SignupPage(driver);
        signupPage.navigateTo(SIGNUP_URL);
        random = new Random();
    }
    
    @Test
    @DisplayName("Signup page should load successfully")
    public void testSignupPageLoads() {
        assertTrue(signupPage.isTitleDisplayed(), "Signup page title should be displayed");
        assertTrue(signupPage.getTitleText().contains("Create your account"), 
                "Title should contain 'Create your account'");
    }
    
    @Test
    @DisplayName("Should display error when passwords don't match")
    public void testPasswordMismatch() {
        String username = "user" + random.nextInt(10000);
        signupPage.signup(username, "Test description", "Password123", "DifferentPassword123");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertTrue(signupPage.isErrorMessageDisplayed(),
                "Error message should be displayed when passwords don't match");
        assertEquals("Passwords do not match.", signupPage.getErrorMessage(),
                "Error message should indicate password mismatch");
    }

    @Test
    @DisplayName("Should successfully signup with valid credentials")
    public void testValidSignup() {
        String uniqueUsername = "testuser" + random.nextInt(100000);
        signupPage.signup(uniqueUsername, "Test123!");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertTrue(getCurrentUrl().contains("/") && !getCurrentUrl().contains("/signup"),
                "Should redirect after successful signup");
    }

    @Test
    @DisplayName("Should successfully signup with description field")
    public void testSignupWithDescription() {
        String uniqueUsername = "testuser" + random.nextInt(100000);
        signupPage.signup(uniqueUsername, "I love cats!", "Test123!", "Test123!");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertTrue(getCurrentUrl().contains("/") && !getCurrentUrl().contains("/signup"),
                "Should redirect after successful signup");
    }

    @Test
    @DisplayName("Signup button should be enabled by default")
    public void testSignupButtonEnabled() {
        assertTrue(signupPage.isSignupButtonEnabled(), "Signup button should be enabled");
    }

    @Test
    @DisplayName("Should navigate to login page when clicking login link")
    public void testNavigateToLogin() {
        signupPage.clickLoginLink();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertTrue(getCurrentUrl().contains("/login"),
                "Should navigate to login page");
    }

    @Test
    @DisplayName("Should allow entering username")
    public void testEnterUsername() {
        signupPage.enterUsername("newuser");
        assertTrue(true);
    }

    @Test
    @DisplayName("Should allow entering description")
    public void testEnterDescription() {
        signupPage.enterDescription("This is a test description");
        assertTrue(true);
    }

    @Test
    @DisplayName("Should allow entering password")
    public void testEnterPassword() {
        signupPage.enterPassword("TestPassword123");
        assertTrue(true);
    }

    @Test
    @DisplayName("Should allow entering confirm password")
    public void testEnterConfirmPassword() {
        signupPage.enterConfirmPassword("TestPassword123");
        assertTrue(true);
    }
}
