package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
            BACK_TO_MAIN_PAGE = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            MY_LISTS_OPTION = "xpath://android.widget.FrameLayout[@content-desc='My lists']";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void returnToSearch(){
        this.waitForElementAndClick(BACK_TO_MAIN_PAGE, "Article Page: cannot find and click X button to return on Main page");
    }

    public void goToMyLists(){
        this.waitForElementAndClick(MY_LISTS_OPTION, "Article Page: cannot find and click 'My lists' option");
    }
}
