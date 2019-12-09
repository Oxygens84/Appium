package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Wikipedia')]",
        SEARCH_INPUT = "org.wikipedia:id/search_src_text",
        SEARCH_RESULT_ELEMENTS = "org.wikipedia:id/page_list_item_container",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{SUBSTRING}')]",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_EMPTY_RESULT = "//*[@text='No results found']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHOD */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHOD */

    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Search Page: cannot find and click Search init element");
        this.waitForElementPresenceBy(By.id(SEARCH_INPUT), "Search Page: cannot find Search input after clicking on Search init element");
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSetText(By.id(SEARCH_INPUT), searchLine, "Search Page: cannot find and type search text into Search input");
    }

    public void waitForSearchResult(){
        this.waitForElementPresenceBy(By.id(SEARCH_RESULT_ELEMENTS), "Search Page: cannot find Search result");
    }

    public void waitForSearchResult(String searchText){
        String searchResultXpath = getResultSearchElement(searchText);
        this.waitForElementPresenceBy(By.xpath(searchResultXpath), "Search Page: cannot find Search result for substring " + searchText);
    }

    public void waitForEmptySearchResult(){
        int searchResult = getAmountOfFoundedElements(By.id(SEARCH_RESULT_ELEMENTS));
        Assert.assertTrue("Search Page: search result is not empty", searchResult == 0);
        this.waitForElementPresenceBy(By.xpath(SEARCH_EMPTY_RESULT), "Search Page: cannot find No result label");
    }

    public void waitForCancelBtnToAppear(){
        this.waitForElementPresenceBy(By.id(SEARCH_CANCEL_BUTTON), "Search Page: cannot find cancel button");
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Search Page: cannot find and click cancel search button");
    }

    public void waitForCancelBtnToDisappear(){
        this.waitForElementInvisibilityBy(By.id(SEARCH_CANCEL_BUTTON), "Search Page: cancel button didnt disappear");
    }

    public void clickArticleWithSubstring(String searchText){
        String searchResultXpath = getResultSearchElement(searchText);
        this.waitForElementAndClick(By.xpath(searchResultXpath), "Search Page: cannot find and click Article for substring " + searchText);
    }

}
