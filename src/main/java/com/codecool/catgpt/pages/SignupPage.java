package com.codecool.catgpt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object Model for the Signup Page
 * Uses ID selectors from the frontend implementation
 */
public class SignupPage extends BasePage {

    private static final By USERNAME_INPUT = By.id("signup-username");
    private static final By DESCRIPTION_TEXTAREA = By.id("signup-description");
    private static final By PASSWORD_INPUT = By.id("signup-password");
    private static final By CONFIRM_PASSWORD_INPUT = By.id("signup-confirm-password");
    private static final By SIGNUP_BUTTON = By.id("signup-submit");
    private static final By ERROR_MESSAGE = By.id("signup-error");
    private static final By LOGIN_LINK = By.id("signup-login-link");
    private static final By TITLE_HEADING = By.id("signup-title");
    private static final By SUBTITLE = By.id("signup-subtitle");

    
    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
        waitForPageLoad();
    }

    public void waitForPageLoad() {
        waitForElementVisible(USERNAME_INPUT);
        waitForElementVisible(PASSWORD_INPUT);
        waitForElementVisible(SIGNUP_BUTTON);
    }

    public void enterUsername(String username) {
        typeText(USERNAME_INPUT, username);
    }
    

    public void enterDescription(String description) {
        typeText(DESCRIPTION_TEXTAREA, description);
    }
    

    public void enterPassword(String password) {
        typeText(PASSWORD_INPUT, password);
    }
    

    public void enterConfirmPassword(String password) {
        typeText(CONFIRM_PASSWORD_INPUT, password);
    }
    

    public void clickSignup() {
        clickElement(SIGNUP_BUTTON);
    }
    

    public void signup(String username, String password) {
        signup(username, "", password, password);
    }

    public void signup(String username, String description, String password, String confirmPassword) {
        enterUsername(username);
        if (description != null && !description.isEmpty()) {
            enterDescription(description);
        }
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickSignup();
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(ERROR_MESSAGE);
    }
    

    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }
    

    public void clickLoginLink() {
        clickElement(LOGIN_LINK);
    }
    

    public boolean isSignupButtonEnabled() {
        return driver.findElement(SIGNUP_BUTTON).isEnabled();
    }
    

    public boolean isTitleDisplayed() {
        return isElementDisplayed(TITLE_HEADING);
    }
    

    public String getTitleText() {
        return getText(TITLE_HEADING);
    }


    public boolean isSubtitleDisplayed() {
        return isElementDisplayed(SUBTITLE);
    }
}
