package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject  extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_RESULT_ELEMENTS = "css:li.page-summary";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "css:li.page-summary[title*='{SUBSTRING}']";
        SEARCH_CANCEL_BUTTON = "css:div>button.cancel";
        SEARCH_EMPTY_RESULT = "css:p.without-results";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
