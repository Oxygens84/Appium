package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {

    static {
        BACK_TO_MAIN_PAGE = "xpath://XCUIElementTypeButton[@name='Back']";
        MY_LISTS_OPTION = "xpath://XCUIElementTypeButton[@name='Saved']";
    }

    public IOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
