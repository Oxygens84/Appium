package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            BACK_TO_MAIN_PAGE,
            OPEN_MAIN_MENU,
            MY_LISTS_OPTION,
            AUTH_OPTION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void returnToSearch(){
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(BACK_TO_MAIN_PAGE, "Article Page: cannot find and click X button to return on Main page");
        }
    }

    public void goToMyLists(){
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(MY_LISTS_OPTION, "Article Page: cannot find and click 'My saved article' option", 5);
        } else {
            this.waitForElementAndClick(MY_LISTS_OPTION, "Article Page: cannot find and click 'My lists' option");
        }
    }

    public void goToAuth(){
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(AUTH_OPTION, "Article Page: cannot find and click 'Log in", 5);
        } else {
            this.waitForElementAndClick(AUTH_OPTION, "Article Page: cannot find and click 'Log in' option");
        }
    }

    public void openMainMenu(){
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_MAIN_MENU, "Article Page: cannot find and click 'Main menu' button");
        }
    }
}
