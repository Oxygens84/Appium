package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUiFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.openqa.selenium.ScreenOrientation;
import lib.ui.*;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    protected lib.ui.MainPageObject MainPageObject;
    protected lib.ui.SearchPageObject SearchPageObject;
    protected lib.ui.ArticlePageObject ArticlePageObject;
    protected lib.ui.NavigationUI NavigationUI;
    protected lib.ui.MyListPageObject MyListPageObject;
    protected lib.ui.WelcomePageObject WelcomePageObject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenToPortraitode();
        this.skipWelcomePageForIOS();

        MainPageObject = new MainPageObject(driver);
        SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI = NavigationUiFactory.get(driver);
        MyListPageObject = MyListPageObjectFactory.get(driver);
        WelcomePageObject = new WelcomePageObject(driver);
    }

    @Override
    protected void tearDown() throws Exception {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenToLandscapeMode(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void rotateScreenToPortraitode(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void runAppInBackground(){
        driver.runAppInBackground(5);
    }

    private void skipWelcomePageForIOS(){
        if (Platform.getInstance().isIOS()){
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
}
