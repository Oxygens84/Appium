package tests.android;

import lib.CoreTestCase;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    private static final String
            SEARCH_TEXT_JAVA = "Java (programming language)",
            SEARCH_TEXT_JAVA_RESULT = "oriented programming language";

    @Test
    public void testSearchPrompt(){
        SearchPageObject.initSearchInput();
    }

    @Test
    public void testSearchElementsResult(){
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(SEARCH_TEXT_JAVA);
        SearchPageObject.waitForSearchResult();
    }

    @Test
    public void testSearchTextResult(){
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(SEARCH_TEXT_JAVA);
        SearchPageObject.waitForSearchResult(SEARCH_TEXT_JAVA_RESULT);
    }

    @Test
    public void testEmptySearchResult() {
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appiummmm");
        SearchPageObject.waitForEmptySearchResult();
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelBtnToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelBtnToDisappear();
    }

    @Test
    public void testSwipeUpSearchResult() {
        String searchText = "Appium";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText);
        SearchPageObject.waitForSearchResult(searchText);
        SearchPageObject.clickArticleWithSubstring(searchText);
        ArticlePageObject.swipeToFooter();
    }
}
