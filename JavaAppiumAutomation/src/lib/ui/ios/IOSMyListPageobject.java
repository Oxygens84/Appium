package lib.ui.ios;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListPageobject extends MyListPageObject {

    static {
        ADD_ARTICLE = "xpath://XCUIElementTypeButton[@name='Save for later']";
        SEARCH_ELEMENT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_ELEMENTS = "xpath://XCUIElementTypeCell";
    }

    public IOSMyListPageobject(RemoteWebDriver driver) {
        super(driver);
    }

}
