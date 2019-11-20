/*
 * Created by Oxana Lobysheva on 12/11/2019.
 */

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FirstTest {

    private AppiumDriver driver;

    private String searchToolbarByXpath = "//*[contains(@text, 'Wikipedia')]";
    private String searchInputFieldById = "org.wikipedia:id/search_src_text";
    private String searchResultListById = "org.wikipedia:id/search_results_list";
    private String searchCloseBtnById = "org.wikipedia:id/search_close_btn";
    private int defaultTimeout = 5;

    @Before
    public void setUp() throws Exception{

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app",FileSystems.getDefault().getPath(".").toAbsolutePath().normalize().toString() + "/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void searchPromptTest(){

        waitForElementAndClick(By.xpath(searchToolbarByXpath));
        WebElement element = waitForElementPresenceBy(By.id(searchInputFieldById));
        String searchPrompt = getTextFrom(element);
        Assert.assertEquals("Search…", searchPrompt);

    }

    @After
    public void tearDown(){
        driver.quit();
    }

    private WebElement waitForElementPresenceBy(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresenceBy(By by) {
        return waitForElementPresenceBy(by, "Cannot find " + by, defaultTimeout);
    }

    private WebElement waitForElementAndClick(By by) {
        WebElement element = waitForElementPresenceBy(by);
        element.click();
        return element;
    }

    private String getTextFrom(WebElement element){
        return element.getAttribute("text");
    }

}
