package com.codecool.catgpt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CatBoxPage extends BasePage {

    private static final By HOME_BUTTON = By.id("home-button");
    private static final By SEARCH_INPUT = By.id("catbox-search-input");
    private static final By BREED_SELECT = By.id("catbox-breed-select");
    
    public CatBoxPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
        waitForPageLoad();
    }

    public void waitForPageLoad() {
        waitForElementVisible(SEARCH_INPUT);
    }

    public void searchByName(String name) {
        typeText(SEARCH_INPUT, name);
    }


    public void clearSearch() {
        WebElement search = waitForElementVisible(SEARCH_INPUT);
        search.clear();
    }


    public void clickHomeButton() {
        clickElement(HOME_BUTTON);
    }


    public boolean isSearchInputDisplayed() {
        return isElementDisplayed(SEARCH_INPUT);
    }

    public boolean isBreedSelectDisplayed() {
        return isElementDisplayed(BREED_SELECT);
    }

    public boolean isAtCatBoxPage() {
        return getCurrentUrl().contains("/catbox");
    }

    public String getSearchPlaceholder() {
        WebElement search = driver.findElement(SEARCH_INPUT);
        return search.getDomAttribute("placeholder");
    }

    public By getCatCardById(String catId) {
        return By.id("cat-card-" + catId);
    }


    public void clickViewCatButton(String catId) {
        By viewButtonLocator = By.id("cat-view-button-" + catId);
        clickElement(viewButtonLocator);
    }

    public boolean isCatCardDisplayed(String catId) {
        return isElementDisplayed(getCatCardById(catId));
    }
    

    public String getFirstCatId() {
        List<WebElement> catCards = driver.findElements(By.cssSelector("[id^='cat-card-']"));
        if (catCards.isEmpty()) {
            return null;
        }

        String cardId = catCards.getFirst().getDomAttribute("id");
        assert cardId != null;
        return cardId.replace("cat-card-", "");
    }

    public List<String> getAllCatIds() {
        List<WebElement> catCards = driver.findElements(By.cssSelector("[id^='cat-card-']"));
        List<String> catIds = new java.util.ArrayList<>();
        
        for (WebElement card : catCards) {
            String cardId = card.getDomAttribute("id");
            assert cardId != null;
            String catId = cardId.replace("cat-card-", "");
            catIds.add(catId);
        }
        
        return catIds;
    }
}
