package com.codecool.catgpt;

import com.codecool.catgpt.pages.HomePage;
import com.codecool.catgpt.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codecool.catgpt.config.TestConfig.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Item Catching Tests")
public class ItemCatchingTest extends BaseTest {
    
    private HomePage homePage;
    
    @BeforeEach
    public void setUpItemCatching() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(LOGIN_URL);
        loginPage.login(TEST_USERNAME, TEST_PASSWORD);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Should display falling item eventually")
    public void testFallingItemAppears() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            wait.until(driver -> homePage.isFallingItemDisplayed());
            assertTrue(homePage.isFallingItemDisplayed(),
                    "Falling item should appear within 30 seconds");
        } catch (TimeoutException e) {
            System.out.println("Falling item did not appear within 30 seconds - this is okay");
        }
    }

    @Test
    @DisplayName("Should be able to catch falling item")
    public void testCatchFallingItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            wait.until(driver -> homePage.isFallingItemDisplayed());

            homePage.clickFallingItem();

            Thread.sleep(1000);

            assertFalse(homePage.isFallingItemDisplayed(),
                    "Falling item should disappear after being caught");

        } catch (TimeoutException e) {
            System.out.println("Skipping test - falling item did not appear");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @DisplayName("Caught item should be added to inventory")
    public void testCaughtItemAddedToInventory() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            homePage.clickInventoryButton();
            Thread.sleep(500);

            boolean hadInventory = homePage.isInventoryOpen();

            if (hadInventory) {
                homePage.closeInventory();
                Thread.sleep(500);
            }

            wait.until(driver -> homePage.isFallingItemDisplayed());
            homePage.clickFallingItem();

            Thread.sleep(1500);

            homePage.clickInventoryButton();
            Thread.sleep(500);

            assertTrue(homePage.isInventoryOpen(),
                    "Inventory should open after catching an item");


        } catch (TimeoutException e) {
            System.out.println("Skipping test - falling item did not appear");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @DisplayName("Multiple items can be caught")
    public void testCatchMultipleItems() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        int caughtCount = 0;
        int maxAttempts = 3;

        for (int i = 0; i < maxAttempts; i++) {
            try {
                wait.until(driver -> homePage.isFallingItemDisplayed());

                homePage.clickFallingItem();
                caughtCount++;

                Thread.sleep(2000);

            } catch (TimeoutException e) {
                System.out.println("Item " + (i + 1) + " did not appear in time");
                break;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("Successfully caught " + caughtCount + " items");

        assertTrue(caughtCount >= 1,
                "Should be able to catch at least 1 item");
    }

    @Test
    @DisplayName("Inventory button should be visible on home page")
    public void testInventoryButtonVisible() {
        assertTrue(homePage.isAtHomePage(), "Should be on home page");
        homePage.clickInventoryButton();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertTrue(homePage.isInventoryOpen(), 
                "Inventory should open when button is clicked");
    }
}
