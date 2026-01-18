package com.codecool.catgpt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object Model for the Home Page
 * Uses ID selectors from the frontend implementation
 */
public class HomePage extends BasePage {

    private static final By LOGOUT_BUTTON = By.id("home-logout-button");
    private static final By CATBOX_BUTTON = By.id("catbox-button");
    private static final By USERNAME_DISPLAY = By.id("home-username-display");

    private static final By INVENTORY_BUTTON = By.id("inventory-button");
    private static final By INVENTORY_DRAWER = By.id("inventory-drawer");
    private static final By INVENTORY_TITLE = By.id("inventory-title");
    private static final By INVENTORY_CLOSE_BUTTON = By.id("inventory-close-button");
    private static final By INVENTORY_CONTENT = By.id("inventory-content");
    private static final By INVENTORY_ERROR = By.id("inventory-error");

    private static final By FALLING_ITEM = By.id("falling-item");

    private static final By RUNNING_CAT = By.id("running-cat");
    private static final By CAUGHT_CAT_MODAL = By.id("caught-cat-modal");
    private static final By CAUGHT_CAT_NICKNAME_INPUT = By.id("caught-cat-nickname-input");
    private static final By CAUGHT_CAT_SAVE_BUTTON = By.id("caught-cat-save-button");
    private static final By CAUGHT_CAT_CANCEL_BUTTON = By.id("caught-cat-cancel-button");
    private static final By CAUGHT_CAT_HINT = By.id("caught-cat-hint");
    private static final By CAUGHT_CAT_ERROR = By.id("caught-cat-error");

    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    

    public void navigateTo(String url) {
        driver.get(url);
        waitForPageLoad();
    }
    

    public void waitForPageLoad() {
        waitForElementVisible(LOGOUT_BUTTON);
    }
    

    public void clickLogout() {
        clickElement(LOGOUT_BUTTON);
    }
    

    public void clickCatBoxButton() {
        clickElement(CATBOX_BUTTON);
    }
    

    public boolean isCatBoxButtonDisplayed() {
        return isElementDisplayed(CATBOX_BUTTON);
    }
    

    public boolean isUserLoggedIn() {
        return isElementDisplayed(USERNAME_DISPLAY);
    }
    

    public String getDisplayedUsername() {
        String fullText = getText(USERNAME_DISPLAY);
        return fullText.replace("Logged in as ", "").trim();
    }

    public boolean isAtHomePage() {
        String url = getCurrentUrl();
        return (url.endsWith("/") || url.matches(".*/\\?.*")) 
                && !url.contains("/login") 
                && !url.contains("/signup")
                && !url.contains("/catbox");
    }
    

    public void clickInventoryButton() {
        clickElement(INVENTORY_BUTTON);
    }
    

    public boolean isInventoryDrawerDisplayed() {
        return isElementDisplayed(INVENTORY_DRAWER);
    }
    

    public void closeInventory() {
        clickElement(INVENTORY_CLOSE_BUTTON);
    }
    

    public boolean isInventoryOpen() {
        return isElementDisplayed(INVENTORY_DRAWER);
    }
    

    public String getInventoryTitle() {
        return getText(INVENTORY_TITLE);
    }
    

    public void useItem(String itemId) {
        By useButtonLocator = By.id("item-use-button-" + itemId);
        clickElement(useButtonLocator);
    }
    

    public String getItemName(String itemId) {
        By itemNameLocator = By.id("item-name-" + itemId);
        return getText(itemNameLocator);
    }
    

    public String getItemAmount(String itemId) {
        By itemAmountLocator = By.id("item-amount-" + itemId);
        return getText(itemAmountLocator);
    }
    

    public boolean isItemDisplayed(String itemId) {
        By itemCardLocator = By.id("item-card-" + itemId);
        return isElementDisplayed(itemCardLocator);
    }
    

    public boolean isFallingItemDisplayed() {
        return isElementDisplayed(FALLING_ITEM);
    }
    

    public void clickFallingItem() {
        clickElement(FALLING_ITEM);
    }
    

    public boolean isRunningCatDisplayed() {
        return isElementDisplayed(RUNNING_CAT);
    }
    

    public void clickRunningCat() {
        clickElement(RUNNING_CAT);
    }


    public boolean isCaughtCatModalDisplayed() {
        return isElementDisplayed(CAUGHT_CAT_MODAL);
    }
    

    public void enterCatNickname(String nickname) {
        typeText(CAUGHT_CAT_NICKNAME_INPUT, nickname);
    }
    

    public void saveCaughtCat() {
        clickElement(CAUGHT_CAT_SAVE_BUTTON);
    }
    

    public void cancelCaughtCat() {
        clickElement(CAUGHT_CAT_CANCEL_BUTTON);
    }
    

    public void catchAndSaveCat(String nickname) {
        clickRunningCat();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        enterCatNickname(nickname);
        saveCaughtCat();
    }
    

    public String getCaughtCatHint() {
        return getText(CAUGHT_CAT_HINT);
    }
    

    public String getCaughtCatError() {
        return getText(CAUGHT_CAT_ERROR);
    }
    

    public boolean isCaughtCatErrorDisplayed() {
        return isElementDisplayed(CAUGHT_CAT_ERROR);
    }
}
