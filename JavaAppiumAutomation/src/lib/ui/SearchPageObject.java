package lib.ui;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_ELEMENTS,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_CANCEL_BUTTON,
            SEARCH_EMPTY_RESULT;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHOD */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHOD */

    public void initSearchInput(){
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Search Page: cannot find and click Search init element");
        this.waitForElementPresence(SEARCH_INPUT, "Search Page: cannot find Search input after clicking on Search init element");
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndClear(SEARCH_INPUT, "Search Page: cannot clear search field");
        this.waitForElementAndSetText(SEARCH_INPUT, searchLine, "Search Page: cannot find and type search text into Search input");
    }

    public void waitForSearchResult(){
        this.waitForElementPresence(SEARCH_RESULT_ELEMENTS, "Search Page: cannot find Search result");
    }

    public void waitForSearchResult(String searchText){
        String searchResultXpath = getResultSearchElement(searchText);
        this.waitForElementPresence(searchResultXpath, "Search Page: cannot find Search result for substring " + searchText);
    }

    public void waitForEmptySearchResult(){
        this.waitForElementInvisibilityBy(SEARCH_EMPTY_RESULT, "Search Page: search result is not empty");
    }

    public void waitForCancelBtnToAppear(){
        this.waitForElementPresence(SEARCH_CANCEL_BUTTON, "Search Page: cannot find cancel button");
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

    public int getAmountOfFoundedElements(){
        return this.getAmountOfFoundedElements(SEARCH_RESULT_ELEMENTS);
    }

    public List<WebElement> getListFoundedElements(){
        return this.getFoundedElements(SEARCH_RESULT_ELEMENTS);
    }

    public void checkTextInSearchResult(String searchText){
        List<WebElement> result_elements = getListFoundedElements();
        String error = "";

        for (WebElement element: result_elements) {
            if (!element
                    .getText()
                    .toUpperCase()
                    .contains(searchText.toUpperCase())){
                error += "\nFounded title '" + element.getText() + "' doesn't contain '" + searchText + "'";
            }
        }

        Assert.assertTrue(
                "Search Page: search result contains titles without search text \n" + error,
                error.length() == 0
        );
    }
}
