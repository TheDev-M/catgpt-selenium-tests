package com.codecool.catgpt;

import com.codecool.catgpt.pages.CatBoxPage;
import com.codecool.catgpt.pages.HomePage;
import com.codecool.catgpt.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codecool.catgpt.config.TestConfig.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for navigation flows between pages
 */
@DisplayName("Navigation Tests")
public class NavigationTest extends BaseTest {
    
    @Test
    @DisplayName("Navigate from Home to Cat Box")
    public void testNavigateFromHomeToCatBox() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(LOGIN_URL);
        loginPage.login(TEST_USERNAME, TEST_PASSWORD);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isAtHomePage(), "Should be on home page after login");
        assertTrue(homePage.isCatBoxButtonDisplayed(), "Cat Box button should be displayed");

        homePage.clickCatBoxButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        CatBoxPage catBoxPage = new CatBoxPage(driver);
        assertTrue(catBoxPage.isAtCatBoxPage(), "Should be on Cat Box page");
    }

    @Test
    @DisplayName("Navigate from Cat Box back to Home")
    public void testNavigateFromCatBoxToHome() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(LOGIN_URL);
        loginPage.login(TEST_USERNAME, TEST_PASSWORD);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        CatBoxPage catBoxPage = new CatBoxPage(driver);
        catBoxPage.navigateTo(CATBOX_URL);
        assertTrue(catBoxPage.isAtCatBoxPage(), "Should be on Cat Box page");

        catBoxPage.clickHomeButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isAtHomePage(), "Should be back on home page");
    }

    @Test
    @DisplayName("Complete navigation flow: Login -> Home -> Cat Box -> Home -> Logout")
    public void testCompleteNavigationFlow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(LOGIN_URL);
        loginPage.login(TEST_USERNAME, TEST_PASSWORD);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isAtHomePage(), "Should be on home page");

        homePage.clickCatBoxButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        CatBoxPage catBoxPage = new CatBoxPage(driver);
        assertTrue(catBoxPage.isAtCatBoxPage(), "Should be on Cat Box page");

        catBoxPage.clickHomeButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertTrue(homePage.isAtHomePage(), "Should be back on home page");

        homePage.clickLogout();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertTrue(getCurrentUrl().contains("/login"),
                "Should redirect to login page after logout");
    }

    @Test
    @DisplayName("User should see username displayed on home page")
    public void testUsernameDisplayedOnHome() {
        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(LOGIN_URL);
        loginPage.login(TEST_USERNAME, TEST_PASSWORD);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isUserLoggedIn(), "User should be logged in");
        String displayedUsername = homePage.getDisplayedUsername();
        assertEquals(TEST_USERNAME, displayedUsername, 
                "Displayed username should match logged in user");
    }
}
