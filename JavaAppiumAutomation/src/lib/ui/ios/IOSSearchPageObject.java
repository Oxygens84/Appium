package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject  extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeNavigationBar[@name='Wikipedia, scroll to top of Explore']";
        SEARCH_RESULT_ELEMENTS = "xpath://XCUIElementTypeOther[2]//xpath://XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "xpath://*[@name='Close']";
        SEARCH_EMPTY_RESULT = "xpath://*[@name='No results found']";
    }

    public IOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
