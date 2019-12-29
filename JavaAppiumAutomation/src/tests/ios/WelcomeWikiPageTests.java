package tests.ios;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import org.junit.Test;

public class WelcomeWikiPageTests extends CoreTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenToPortraitode();

        WelcomePageObject = new WelcomePageObject(driver);
    }

    @Test
    public void testPassThroughWelcome(){

        if (Platform.getInstance().isAndroid()) {
            return;
        }
        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForNewWayToExploreText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForAddOrEditPreferredLangText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForLearnMoreAboutDataCollectedText();
        WelcomePageObject.clickGetStartedButton();
}

}
