package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Wikipedia')]",
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
        SEARCH_RESULT_ELEMENTS = "id:org.wikipedia:id/page_list_item_container",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{SUBSTRING}')]",
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
        SEARCH_EMPTY_RESULT = "xpath://*[@text='No results found']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHOD */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHOD */

    public void initSearchInput(){
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Search Page: cannot find and click Search init element");
        this.waitForElementPresenceBy(SEARCH_INPUT, "Search Page: cannot find Search input after clicking on Search init element");
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSetText(SEARCH_INPUT, searchLine, "Search Page: cannot find and type search text into Search input");
    }

    public void waitForSearchResult(){
        this.waitForElementPresenceBy(SEARCH_RESULT_ELEMENTS, "Search Page: cannot find Search result");
    }

    public void waitForSearchResult(String searchText){
        String searchResultXpath = getResultSearchElement(searchText);
        this.waitForElementPresenceBy(searchResultXpath, "Search Page: cannot find Search result for substring " + searchText);
    }

    public void waitForEmptySearchResult(){
        int searchResult = getAmountOfFoundedElements(SEARCH_RESULT_ELEMENTS);
        Assert.assertTrue("Search Page: search result is not empty", searchResult == 0);
        this.waitForElementPresenceBy(SEARCH_EMPTY_RESULT, "Search Page: cannot find No result label");
    }

    public void waitForCancelBtnToAppear(){
        this.waitForElementPresenceBy(SEARCH_CANCEL_BUTTON, "Search Page: cannot find cancel button");
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Search Page: cannot find and click cancel search button");
    }

    public void waitForCancelBtnToDisappear(){
        this.waitForElementInvisibilityBy(SEARCH_CANCEL_BUTTON, "Search Page: cancel button didnt disappear");
    }

    public void clickArticleWithSubstring(String searchText){
        String searchResultXpath = getResultSearchElement(searchText);
        this.waitForElementAndClick(searchResultXpath, "Search Page: cannot find and click Article for substring " + searchText);
    }

}
