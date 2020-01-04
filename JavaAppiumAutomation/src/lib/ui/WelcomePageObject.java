package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_ABOUT_WIKI = "xpath://XCUIElementTypeButton[@name='Learn more about Wikipedia']",
            STEP_NEW_WAY_TO_EXPLORE = "xpath://XCUIElementTypeStaticText[@name='New ways to explore']",
            STEP_ADD_OR_EDIT_PREF_LANGUAGE = "xpath://XCUIElementTypeButton[@name='Add or edit preferred languages']",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "xpath://XCUIElementTypeButton[@name='Learn more about data collected']",
            STEP_NEXT = "xpath://XCUIElementTypeButton[@name='Next']",
            STEP_GET_STARTED = "xpath://XCUIElementTypeButton[@name='Get started']",
            STEP_SKIP = "xpath://XCUIElementTypeButton[@name='Skip']";


    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink(){
        this.waitForElementPresence(STEP_LEARN_MORE_ABOUT_WIKI, "Welcome Page: cannot find 'Learn more about Wikipedia'");
    }

    public void waitForNewWayToExploreText(){
        this.waitForElementPresence(STEP_NEW_WAY_TO_EXPLORE, "Welcome Page: cannot find 'New ways to explore'");
    }

    public void waitForAddOrEditPreferredLangText(){
        this.waitForElementPresence(STEP_ADD_OR_EDIT_PREF_LANGUAGE, "Add or edit preferred lamguages'");
    }

    public void waitForLearnMoreAboutDataCollectedText(){
        this.waitForElementPresence(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED, "Learn more about data collected'");
    }


    public void clickNextButton(){
        this.waitForElementAndClick(STEP_NEXT, "Welcome Page: cannot find Next");
    }

    public void clickGetStartedButton(){
        this.waitForElementAndClick(STEP_GET_STARTED, "Welcome Page: cannot find Get started Button");
    }

    public void clickSkip(){
        this.waitForElementAndClick(STEP_SKIP, "Welcome Page: cannot find Skip Button");
    }
}
