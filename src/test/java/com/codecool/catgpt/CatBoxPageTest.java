package com.codecool.catgpt;

import com.codecool.catgpt.pages.CatBoxPage;
import com.codecool.catgpt.pages.HomePage;
import com.codecool.catgpt.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static com.codecool.catgpt.config.TestConfig.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cat Box Page Tests")
public class CatBoxPageTest extends BaseTest {
    
    private CatBoxPage catBoxPage;
    
    @BeforeEach
    public void setUpCatBoxPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(LOGIN_URL);
        loginPage.login(TEST_USERNAME, TEST_PASSWORD);

        catBoxPage = new CatBoxPage(driver);
        catBoxPage.navigateTo(CATBOX_URL);
    }
    
    @Test
    @DisplayName("Cat Box page should load successfully")
    public void testCatBoxPageLoads() {
        assertTrue(catBoxPage.isAtCatBoxPage(), "Should be on Cat Box page");
        assertTrue(catBoxPage.isSearchInputDisplayed(), "Search input should be displayed");
    }
    
    @Test
    @DisplayName("Search input should have correct placeholder")
    public void testSearchPlaceholder() {
        String placeholder = catBoxPage.getSearchPlaceholder();
        assertTrue(placeholder.contains("Search") || placeholder.contains("name"), 
                "Placeholder should indicate search functionality");
    }
    
    @Test
    @DisplayName("Should be able to search for cats by name")
    public void testSearchByName() {
        catBoxPage.searchByName("Bob");
        catBoxPage.waitForSearchResults();

        assertTrue(catBoxPage.areAllDisplayedCatsMatchingSearch("Bob"), 
                "All displayed cats should have 'Bob' in their name");
    }
    
    @Test
    @DisplayName("Should be able to clear search")
    public void testClearSearch() {
        catBoxPage.searchByName("Test");
        catBoxPage.waitForSearchResults();
        int filteredCount = catBoxPage.getDisplayedCatCount();

        catBoxPage.clearSearch();
        catBoxPage.waitForSearchResults();
        int allCatsCount = catBoxPage.getDisplayedCatCount();

        assertTrue(allCatsCount >= filteredCount, 
                "After clearing search, should display all cats");
    }
    
    @Test
    @DisplayName("Breed select should be displayed")
    public void testBreedSelectDisplayed() {
        assertTrue(catBoxPage.isBreedSelectDisplayed(), 
                "Breed select dropdown should be displayed");
    }
    
    @Test
    @DisplayName("Should navigate back to home when clicking home button")
    public void testNavigateToHome() {
        catBoxPage.clickHomeButton();

        
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isAtHomePage(), "Should navigate to home page");
    }
    
    @Test
    @DisplayName("Search input should be interactive")
    public void testSearchInputInteraction() {
        assertTrue(catBoxPage.isSearchInputDisplayed(), "Search input should be displayed");
        catBoxPage.searchByName("TestCat");
        catBoxPage.waitForSearchResults();
        catBoxPage.clearSearch();
        catBoxPage.waitForSearchResults();
        catBoxPage.searchByName("AnotherCat");
        catBoxPage.waitForSearchResults();

        assertTrue(catBoxPage.getDisplayedCatCount() > 0, 
                "Should display cat cards after search interactions");
    }
}
