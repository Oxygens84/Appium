package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class IOSMyListPageobject extends MyListPageObject {

    static {
        ADD_ARTICLE = "xpath://XCUIElementTypeButton[@name='Save for later']";
        SEARCH_ELEMENT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_ELEMENTS = "xpath://XCUIElementTypeCell";
    }

    public IOSMyListPageobject(AppiumDriver driver) {
        super(driver);
    }

}
