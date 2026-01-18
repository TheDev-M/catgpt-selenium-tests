package com.codecool.catgpt;

import com.codecool.catgpt.pages.HomePage;
import com.codecool.catgpt.pages.LoginPage;
import com.codecool.catgpt.pages.SignupPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.codecool.catgpt.config.TestConfig.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("End-to-End Tests")
public class EndToEndTest extends BaseTest {
    
    private final Random random = new Random();
    
    @Test
    @DisplayName("Complete user journey: Signup -> Login -> Logout")
    public void testCompleteUserJourney() {
        String uniqueUsername = "e2euser" + random.nextInt(100000);
        String password = "E2ETest123!";
        
        SignupPage signupPage = new SignupPage(driver);
        signupPage.navigateTo(SIGNUP_URL);
        signupPage.signup(uniqueUsername, "E2E test user", password, password);

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isAtHomePage(), "Should be on home page after signup");

        homePage.clickLogout();

        assertTrue(getCurrentUrl().contains("/login"),
                "Should redirect to login page after logout");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(uniqueUsername, password);

        assertTrue(homePage.isAtHomePage(), "Should be on home page after login");
    }
    
    @Test
    @DisplayName("User cannot access home page without authentication")
    public void testAuthenticationRequired() {
        driver.get(HOME_URL);

        assertTrue(getCurrentUrl().contains("/login"),
                "Should redirect to login page when not authenticated");
    }
    
    @Test
    @DisplayName("Navigate from login to signup and back")
    public void testNavigationBetweenAuthPages() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(LOGIN_URL);
        assertTrue(getCurrentUrl().contains("/login"), "Should be on login page");

        loginPage.clickSignupLink();
        assertTrue(getCurrentUrl().contains("/signup"), "Should be on signup page");

        SignupPage signupPage = new SignupPage(driver);
        signupPage.clickLoginLink();
        assertTrue(getCurrentUrl().contains("/login"), "Should be back on login page");
    }
}
