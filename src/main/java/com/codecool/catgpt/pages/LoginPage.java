package com.codecool.catgpt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.id("login-username");
    private static final By PASSWORD_INPUT = By.id("login-password");
    private static final By LOGIN_BUTTON = By.id("login-submit");
    private static final By ERROR_MESSAGE = By.id("login-error");
    private static final By SIGNUP_LINK = By.id("login-signup-link");
    private static final By TITLE_HEADING = By.id("login-title");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
        waitForPageLoad();
    }

    public void waitForPageLoad() {
        waitForElementVisible(USERNAME_INPUT);
        waitForElementVisible(PASSWORD_INPUT);
        waitForElementVisible(LOGIN_BUTTON);
    }

    public void enterUsername(String username) {
        typeText(USERNAME_INPUT, username);
    }

    public void enterPassword(String password) {
        typeText(PASSWORD_INPUT, password);
    }

    public void clickLogin() {
        clickElement(LOGIN_BUTTON);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(ERROR_MESSAGE);
    }

    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    public void clickSignupLink() {
        clickElement(SIGNUP_LINK);
    }

    public boolean isLoginButtonEnabled() {
        return driver.findElement(LOGIN_BUTTON).isEnabled();
    }

    public boolean isTitleDisplayed() {
        return isElementDisplayed(TITLE_HEADING);
    }

    public String getTitleText() {
        return getText(TITLE_HEADING);
    }

}
