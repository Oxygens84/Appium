package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {

    static {
        BACK_TO_MAIN_PAGE = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        MY_LISTS_OPTION = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
