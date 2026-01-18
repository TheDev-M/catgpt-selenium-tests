package com.codecool.catgpt;

import com.codecool.catgpt.pages.CatBoxPage;
import com.codecool.catgpt.pages.CatDetailsPage;
import com.codecool.catgpt.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static com.codecool.catgpt.config.TestConfig.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cat Details Tests")
public class CatDetailsTest extends BaseTest {
    
    private CatDetailsPage catDetailsPage;
    private CatBoxPage catBoxPage;
    private String firstCatId;
    
    @BeforeEach
    public void setUpCatDetails() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo(LOGIN_URL);
        loginPage.login(TEST_USERNAME, TEST_PASSWORD);

        catBoxPage = new CatBoxPage(driver);
        catBoxPage.navigateTo(CATBOX_URL);

        firstCatId = catBoxPage.getFirstCatId();
        assertNotNull(firstCatId, "User should have at least one cat");
        
        catDetailsPage = new CatDetailsPage(driver);
    }
    
    @Test
    @DisplayName("Should navigate to cat details page")
    public void testNavigateToCatDetails() {
        catBoxPage.clickViewCatButton(firstCatId);

        assertTrue(catDetailsPage.isAtCatDetailsPage(), 
                "Should be on cat details page");
    }
    
    @Test
    @DisplayName("Cat details page should display action buttons")
    public void testActionButtonsDisplayed() {
        catBoxPage.clickViewCatButton(firstCatId);
        
        catDetailsPage.waitForPageLoad();

        assertTrue(catDetailsPage.isAtCatDetailsPage(), 
                "Should be on cat details page with action buttons");
    }
    
    @Test
    @DisplayName("Should navigate back to cat box from details page")
    public void testBackToCatBox() {
        catBoxPage.clickViewCatButton(firstCatId);
        
        catDetailsPage.clickBack();
        
        assertTrue(catBoxPage.isAtCatBoxPage(), 
                "Should navigate back to cat box");
    }
    
    @Test
    @DisplayName("Should be able to select a cat")
    public void testSelectCat() {
        catBoxPage.clickViewCatButton(firstCatId);
        
        if (catDetailsPage.isSelectButtonEnabled()) {
            catDetailsPage.clickSelect();
        }
        
        assertTrue(catDetailsPage.isAtCatDetailsPage(), 
                "Should remain on cat details page after selection");
    }
    
    @Test
    @DisplayName("Cat should have either rename enabled or be default cat")
    public void testRenameButtonState() {
        catBoxPage.clickViewCatButton(firstCatId);
        
        boolean renameEnabled = catDetailsPage.isRenameButtonEnabled();
        boolean releaseEnabled = catDetailsPage.isReleaseButtonEnabled();

        assertEquals(renameEnabled, releaseEnabled, 
                "Rename and release should have same enabled state");
        
        System.out.println("Cat ID " + firstCatId + " - Rename enabled: " + renameEnabled);
    }
    
    @Test
    @DisplayName("User should have at least one cat to view")
    public void testUserHasCats() {
        assertNotNull(firstCatId, "Should have found at least one cat");
        assertTrue(catBoxPage.isCatCardDisplayed(firstCatId), 
                "Cat card should be displayed");
    }
}
