package com.codecool.catgpt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object Model for the Cat Details Page
 * Uses ID selectors from the frontend implementation
 */
public class CatDetailsPage extends BasePage {

    // Action button locators
    private static final By SELECT_BUTTON = By.id("cat-select-button");
    private static final By RENAME_BUTTON = By.id("cat-rename-button");
    private static final By RELEASE_BUTTON = By.id("cat-release-button");
    private static final By BACK_BUTTON = By.id("cat-back-button");

    // Rename form locators
    private static final By RENAME_FORM = By.id("cat-rename-form");
    private static final By RENAME_INPUT = By.id("cat-rename-input");
    private static final By RENAME_HINT = By.id("cat-rename-hint");
    private static final By RENAME_ERROR = By.id("cat-rename-error");
    private static final By RENAME_SUBMIT = By.id("cat-rename-submit");
    private static final By RENAME_CANCEL = By.id("cat-rename-cancel");

    // Release modal locators
    private static final By RELEASE_MODAL = By.id("cat-release-modal");
    private static final By RELEASE_MODAL_TITLE = By.id("cat-release-modal-title");
    private static final By RELEASE_MODAL_MESSAGE = By.id("cat-release-modal-message");
    private static final By RELEASE_CONFIRM = By.id("cat-release-confirm");
    private static final By RELEASE_CANCEL = By.id("cat-release-cancel");
    
    public CatDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String baseUrl, String catId) {
        driver.get(baseUrl + "/catbox/" + catId);
        waitForPageLoad();
    }


    public void waitForPageLoad() {
        waitForElementVisible(SELECT_BUTTON);
    }
    

    public void clickSelect() {
        clickElement(SELECT_BUTTON);
    }
    

    public void clickRename() {
        clickElement(RENAME_BUTTON);
    }
    

    public void clickRelease() {
        clickElement(RELEASE_BUTTON);
    }
    

    public void clickBack() {
        clickElement(BACK_BUTTON);
    }
    

    public boolean isSelectButtonEnabled() {
        return driver.findElement(SELECT_BUTTON).isEnabled();
    }


    public boolean isRenameButtonEnabled() {
        return driver.findElement(RENAME_BUTTON).isEnabled();
    }
    

    public boolean isReleaseButtonEnabled() {
        return driver.findElement(RELEASE_BUTTON).isEnabled();
    }


    public boolean isRenameFormDisplayed() {
        return isElementDisplayed(RENAME_FORM);
    }

    public void enterNewCatName(String name) {
        typeText(RENAME_INPUT, name);
    }


    public void submitRename() {
        clickElement(RENAME_SUBMIT);
    }


    public void cancelRename() {
        clickElement(RENAME_CANCEL);
    }


    public void renameCat(String newName) {
        clickRename();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        enterNewCatName(newName);
        submitRename();
    }


    public String getRenameHint() {
        return getText(RENAME_HINT);
    }


    public String getRenameError() {
        return getText(RENAME_ERROR);
    }


    public boolean isRenameErrorDisplayed() {
        return isElementDisplayed(RENAME_ERROR);
    }


    public boolean isReleaseModalDisplayed() {
        return isElementDisplayed(RELEASE_MODAL);
    }


    public String getReleaseModalTitle() {
        return getText(RELEASE_MODAL_TITLE);
    }


    public String getReleaseModalMessage() {
        return getText(RELEASE_MODAL_MESSAGE);
    }


    public void confirmRelease() {
        clickElement(RELEASE_CONFIRM);
    }


    public void cancelRelease() {
        clickElement(RELEASE_CANCEL);
    }


    public void releaseCat() {
        clickRelease();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        confirmRelease();
    }
    

    public boolean isAtCatDetailsPage() {
        return getCurrentUrl().contains("/catbox/") && 
               !getCurrentUrl().endsWith("/catbox") &&
               !getCurrentUrl().endsWith("/catbox/");
    }
}
