package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import lib.ui.*;

import java.net.URL;
import java.nio.file.FileSystems;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String appiumUrl = "http://127.0.0.1:4723/wd/hub";

    private static final String PLATFORM_IOS = "iOS";
    private static final String PLATFORM_ANDROID = "Android";
    private static String CURRENT_PLATFORM = PLATFORM_IOS;

    protected lib.ui.MainPageObject MainPageObject;
    protected lib.ui.SearchPageObject SearchPageObject;
    protected lib.ui.ArticlePageObject ArticlePageObject;
    protected lib.ui.NavigationUI NavigationUI;
    protected lib.ui.MyListPageObject MyListPageObject;
    protected lib.ui.WelcomePageObject WelcomePageObject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        CURRENT_PLATFORM = System.getenv("platform");
        DesiredCapabilities capabilities = getCapabilitiesByParameter();
        driver = getDriver(capabilities);
        rotateScreenToPortraitode();

        MainPageObject = new MainPageObject(driver);
        SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI = new NavigationUI(driver);
        MyListPageObject = new MyListPageObject(driver);
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

    protected DesiredCapabilities getCapabilitiesByParameter() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (CURRENT_PLATFORM.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","AndroidTestDevice");
            capabilities.setCapability("platformVersion","6.0");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia");
            capabilities.setCapability("appActivity",".main.MainActivity");
            capabilities.setCapability("app", FileSystems.getDefault().getPath(".").toAbsolutePath().normalize().toString() + "/apks/org.wikipedia.apk");
        } else if (CURRENT_PLATFORM.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("platformVersion", "12.4");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("app", FileSystems.getDefault().getPath(".").toAbsolutePath().normalize().toString() + "/apks/Wikipedia.app");
        } else {
            throw  new Exception("Cannot find platform environment. Platform name = " + CURRENT_PLATFORM);
        }
        return capabilities;
    }

    protected AppiumDriver getDriver(DesiredCapabilities capabilities) throws Exception{
        if (CURRENT_PLATFORM.equals(PLATFORM_ANDROID)) {
            return new AndroidDriver(new URL(appiumUrl), capabilities);
        } else if (CURRENT_PLATFORM.equals(PLATFORM_IOS)) {
            return new IOSDriver(new URL(appiumUrl), capabilities);
        } else {
            throw  new Exception("Cannot find platform environment. Platform name = " + CURRENT_PLATFORM);
        }
    }
}
