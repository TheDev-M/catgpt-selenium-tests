package com.codecool.catgpt;

import com.codecool.catgpt.pages.HomePage;
import com.codecool.catgpt.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static com.codecool.catgpt.config.TestConfig.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for Inventory functionality
 * Tests: opening/closing inventory, viewing items, using items
 */
@DisplayName("Inventory Tests")
public class InventoryTest extends BaseTest {
    
    private HomePage homePage;
    
    @BeforeEach
    public void setUpInventory() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(LOGIN_URL);
        loginPage.login(TEST_USERNAME, TEST_PASSWORD);

        homePage = new HomePage(driver);
    }
    
    @Test
    @DisplayName("Should open inventory drawer when clicking inventory button")
    public void testOpenInventory() {
        assertFalse(homePage.isInventoryOpen(), 
                "Inventory should be closed initially");
        
        homePage.clickInventoryButton();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertTrue(homePage.isInventoryOpen(),
                "Inventory should be open after clicking button");
    }

    @Test
    @DisplayName("Should close inventory drawer when clicking close button")
    public void testCloseInventory() {
        homePage.clickInventoryButton();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertTrue(homePage.isInventoryOpen(), "Inventory should be open");

        homePage.closeInventory();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertFalse(homePage.isInventoryOpen(),
                "Inventory should be closed after clicking close button");
    }

    @Test
    @DisplayName("Inventory drawer should display correct title")
    public void testInventoryTitle() {
        homePage.clickInventoryButton();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String title = homePage.getInventoryTitle();
        assertEquals("Inventory", title, "Inventory title should be 'Inventory'");
    }

    @Test
    @DisplayName("Should toggle inventory drawer on multiple clicks")
    public void testToggleInventory() {
        homePage.clickInventoryButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertTrue(homePage.isInventoryOpen(), "Should open on first click");

        homePage.clickInventoryButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertFalse(homePage.isInventoryOpen(), "Should close on second click");

        homePage.clickInventoryButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertTrue(homePage.isInventoryOpen(), "Should open on third click");
    }

    @Test
    @DisplayName("Inventory drawer should be displayed correctly")
    public void testInventoryDrawerDisplayed() {
        homePage.clickInventoryButton();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertTrue(homePage.isInventoryDrawerDisplayed(), 
                "Inventory drawer should be visible");
    }
}
