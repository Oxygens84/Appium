package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_ABOUT_WIKI = "id:Learn more about Wikipedia",
            STEP_NEW_WAY_TO_EXPLORE = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREF_LANGUAGE = "id:Add or edit preferred lamguages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "id:Learn more about data collected",
            STEP_NEXT = "id:Next",
            STEP_GET_STARTED = "id:Get started";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink(){
        this.waitForElementPresenceBy(STEP_LEARN_MORE_ABOUT_WIKI, "Welcome Page: cannot find 'Learn more about Wikipedia'");
    }

    public void waitForNewWayToExploreText(){
        this.waitForElementPresenceBy(STEP_NEW_WAY_TO_EXPLORE, "Welcome Page: cannot find 'New ways to explore'");
    }

    public void waitForAddOrEditPreferredLangText(){
        this.waitForElementPresenceBy(STEP_ADD_OR_EDIT_PREF_LANGUAGE, "Add or edit preferred lamguages'");
    }

    public void waitForLearnMoreAboutDataCollectedText(){
        this.waitForElementPresenceBy(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED, "Learn more about data collected'");
    }


    public void clickNextButton(){
        this.waitForElementPresenceBy(STEP_NEXT, "Welcome Page: cannot find Next");
    }

    public void clickGetStartedButton(){
        this.waitForElementPresenceBy(STEP_GET_STARTED, "Welcome Page: cannot find Get started Button");
    }

}
