package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI {

    static {
        BACK_TO_MAIN_PAGE = "xpath://XCUIElementTypeButton[@name='Back']";
        MY_LISTS_OPTION = "xpath://XCUIElementTypeButton[@name='Saved']";
    }

    public IOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
