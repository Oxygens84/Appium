package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {

    static {
        OPEN_MAIN_MENU = "css:#mw-mf-main-menu-button";
        MY_LISTS_OPTION = "css:a[data-event-name='menu.watchlist']";
        AUTH_OPTION = "css:a[data-event-name='menu.login']";
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
