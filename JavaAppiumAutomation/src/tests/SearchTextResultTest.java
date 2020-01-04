package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchTextResultTest extends CoreTestCase {


    private static final String
            SEARCH_TEXT_JAVA = "Java";

    @Test
    public void testSearchResultCheck(){

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(SEARCH_TEXT_JAVA);
        SearchPageObject.waitForSearchResult();
        SearchPageObject.checkTextInSearchResult(SEARCH_TEXT_JAVA);

    }

}
