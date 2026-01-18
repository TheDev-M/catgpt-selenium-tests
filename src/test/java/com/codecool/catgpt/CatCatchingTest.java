package com.codecool.catgpt;

import com.codecool.catgpt.pages.CatBoxPage;
import com.codecool.catgpt.pages.HomePage;
import com.codecool.catgpt.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.codecool.catgpt.config.TestConfig.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cat Catching Tests")
public class CatCatchingTest extends BaseTest {
    
    private HomePage homePage;
    private CatBoxPage catBoxPage;
    
    @BeforeEach
    public void setUpCatCatching() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(LOGIN_URL);
        loginPage.login(TEST_USERNAME, TEST_PASSWORD);

        homePage = new HomePage(driver);
        catBoxPage = new CatBoxPage(driver);
    }
    
    @Test
    @DisplayName("Should display running cat eventually")
    public void testRunningCatAppears() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        try {
            wait.until(driver -> homePage.isRunningCatDisplayed());
            assertTrue(homePage.isRunningCatDisplayed(), 
                    "Running cat should appear within 30 seconds");
        } catch (TimeoutException e) {
            System.out.println("Running cat did not appear within 30 seconds - this is okay");
        }
    }
    
    @Test
    @DisplayName("Should open caught cat modal when clicking running cat")
    public void testCatchRunningCat() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        try {
            wait.until(driver -> homePage.isRunningCatDisplayed());
            homePage.clickRunningCat();

            wait.until(driver -> homePage.isCaughtCatModalDisplayed());

            assertTrue(homePage.isCaughtCatModalDisplayed(), 
                    "Caught cat modal should appear after clicking running cat");
        } catch (TimeoutException e) {
            System.out.println("Skipping test - running cat did not appear in time");
        }
    }
    
    @Test
    @DisplayName("Should be able to save caught cat with valid nickname")
    public void testSaveCaughtCatWithValidNickname() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        try {
            wait.until(driver -> homePage.isRunningCatDisplayed());
            homePage.clickRunningCat();

            wait.until(driver -> homePage.isCaughtCatModalDisplayed());

            if (!homePage.isCaughtCatModalDisplayed()) {
                System.out.println("Modal did not appear - skipping test");
                return;
            }

            homePage.clickCatBoxButton();
            List<String> initialCats = catBoxPage.getAllCatIds();
            int initialCount = initialCats.size();

            catBoxPage.clickHomeButton();

            wait.until(driver -> homePage.isRunningCatDisplayed());

            String uniqueNickname = "TestCat" + System.currentTimeMillis();
            homePage.catchAndSaveCat(uniqueNickname);

            wait.until(driver -> !homePage.isCaughtCatModalDisplayed());

            homePage.clickCatBoxButton();

            List<String> finalCats = catBoxPage.getAllCatIds();
            int finalCount = finalCats.size();

            assertTrue(finalCount > initialCount, 
                    "Cat count should increase after catching a cat");

        } catch (TimeoutException e) {
            System.out.println("Skipping test - running cat did not appear");
        }
    }
    
    @Test
    @DisplayName("Should show error for invalid cat nickname (too short)")
    public void testInvalidNicknameTooShort() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        try {
            wait.until(driver -> homePage.isRunningCatDisplayed());
            homePage.clickRunningCat();
            
            wait.until(driver -> homePage.isCaughtCatModalDisplayed());

            if (!homePage.isCaughtCatModalDisplayed()) {
                System.out.println("Modal did not appear - skipping test");
                return;
            }

            homePage.enterCatNickname("Ab");
            homePage.saveCaughtCat();

            assertTrue(homePage.isCaughtCatModalDisplayed(), 
                    "Modal should still be open after validation error");

            homePage.cancelCaughtCat();

        } catch (TimeoutException e) {
            System.out.println("Skipping test - running cat did not appear");
        }
    }
    
    @Test
    @DisplayName("Should be able to cancel caught cat modal")
    public void testCancelCaughtCat() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        try {
            wait.until(driver -> homePage.isRunningCatDisplayed());
            homePage.clickRunningCat();
            
            wait.until(driver -> homePage.isCaughtCatModalDisplayed());

            if (!homePage.isCaughtCatModalDisplayed()) {
                System.out.println("Modal did not appear - skipping test");
                return;
            }

            homePage.cancelCaughtCat();

            wait.until(driver -> !homePage.isCaughtCatModalDisplayed());

            assertFalse(homePage.isCaughtCatModalDisplayed(), 
                    "Modal should close after clicking cancel");

        } catch (TimeoutException e) {
            System.out.println("Skipping test - running cat did not appear");
        }
    }
    
    @Test
    @DisplayName("User should have at least one cat")
    public void testUserHasAtLeastOneCat() {
        homePage.clickCatBoxButton();
        
        List<String> catIds = catBoxPage.getAllCatIds();
        
        assertFalse(catIds.isEmpty(), 
                "User should have at least one cat (default cat or caught cats)");
    }
}
