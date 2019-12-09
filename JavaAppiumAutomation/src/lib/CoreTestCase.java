package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import lib.ui.*;

import java.net.URL;
import java.nio.file.FileSystems;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String appiumUrl = "http://127.0.0.1:4723/wd/hub";

    protected lib.ui.MainPageObject MainPageObject;
    protected lib.ui.SearchPageObject SearchPageObject;
    protected lib.ui.ArticlePageObject ArticlePageObject;
    protected lib.ui.NavigationUI NavigationUI;
    protected lib.ui.MyListPageObject MyListPageObject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app", FileSystems.getDefault().getPath(".").toAbsolutePath().normalize().toString() + "/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL(appiumUrl), capabilities);
        rotateScreenToPortraitode();

        MainPageObject = new MainPageObject(driver);
        SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI = new NavigationUI(driver);
        MyListPageObject = new MyListPageObject(driver);
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

}
