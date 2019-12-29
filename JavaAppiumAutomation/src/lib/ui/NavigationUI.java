package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            BACK_TO_MAIN_PAGE,
            MY_LISTS_OPTION;

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
