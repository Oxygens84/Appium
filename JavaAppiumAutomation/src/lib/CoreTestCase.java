package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.factories.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import lib.ui.*;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;
    protected lib.ui.MainPageObject MainPageObject;
    protected lib.ui.SearchPageObject SearchPageObject;
    protected lib.ui.ArticlePageObject ArticlePageObject;
    protected lib.ui.NavigationUI NavigationUI;
    protected lib.ui.MyListPageObject MyListPageObject;
    protected lib.ui.WelcomePageObject WelcomePageObject;
    protected AuthorizationPageObject AuthorizationPageObject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        if (Platform.getInstance().isMW()) {
            driver.manage().window().setSize(new Dimension(360, 640));
        }

        this.rotateScreenToPortraitMode();
        this.skipWelcomePageForIOS();
        this.openWikiWebPageForMobileWeb();

        MainPageObject = new MainPageObject(driver);
        SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI = NavigationUiFactory.get(driver);
        MyListPageObject = MyListPageObjectFactory.get(driver);
        WelcomePageObject = new WelcomePageObject(driver);
        AuthorizationPageObject = AuthPageObjectFactory.get(driver);
    }

    @Override
    protected void tearDown() throws Exception {
        this.rotateScreenToPortraitMode();
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenToLandscapeMode(){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver)this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenToLandscapeMode is not acceptable for platform " + Platform.getInstance().getPlatformValue());
        }
    }

    protected void rotateScreenToPortraitMode(){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver)this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenToPortraitMode is not acceptable for platform " + Platform.getInstance().getPlatformValue());
        }
    }

    protected void runAppInBackground(){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver)this.driver;
            driver.runAppInBackground(5);
        } else {
            System.out.println("Method runAppInBackground is not acceptable for platform " + Platform.getInstance().getPlatformValue());
        }
    }

    private void skipWelcomePageForIOS(){
        if (Platform.getInstance().isIOS()){
            AppiumDriver driver = (AppiumDriver)this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }

    protected void openWikiWebPageForMobileWeb(){
        if (Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb is not acceptable for platform " + Platform.getInstance().getPlatformValue());
        }
    }
}
