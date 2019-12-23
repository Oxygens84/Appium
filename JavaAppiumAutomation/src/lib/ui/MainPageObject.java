package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;
    private int defaultTimeout = 35;
    private int defaultTimeOfSwipe = 200;


    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }

    public WebElement waitForElementPresenceBy(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public List<WebElement> waitForElementsPresenceBy(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    public Boolean waitForElementInvisibilityBy(String locator, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresenceBy(String locator, String errorMessage) {
        return waitForElementPresenceBy(locator, errorMessage + "\nCannot find " + locator, defaultTimeout);
    }

    public List<WebElement> waitForElementsPresenceBy(String locator, String errorMessage) {
        return waitForElementsPresenceBy(locator, errorMessage + "\nCannot find " + locator, defaultTimeout);
    }

    public Boolean waitForElementInvisibilityBy(String locator, String errorMessage){
        return waitForElementInvisibilityBy(locator, errorMessage + "\nFounded elements " + locator, defaultTimeout);
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage) {
        WebElement element = waitForElementPresenceBy(locator, errorMessage);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSetText(String locator, String text, String errorMessage){
        WebElement element = waitForElementPresenceBy(locator, errorMessage);
        element.sendKeys(text);
        return element;
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage){
        WebElement element = waitForElementPresenceBy(locator, errorMessage);
        return element.getAttribute(attribute);
    }

    public WebElement waitForElementAndClear(String locator, String errorMessage) {
        WebElement element = waitForElementPresenceBy(locator, errorMessage);
        element.clear();
        return element;
    }

    public String getTextFrom(WebElement element){
        return element.getAttribute("text");
    }

    public void swipeUp(int timeOfSwipe){
        TouchAction actions = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int)(size.height*0.8);
        int end_y = (int)(size.height*0.2);
        actions
                .press(x, start_y)
                .waitAction()
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    public void swipeUpQick(){
        swipeUp(defaultTimeOfSwipe);
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes){
        By by = this.getLocatorByString(locator);
        int currentLoop = 0;
        while ( driver.findElements(by).size() == 0){
            if (currentLoop == maxSwipes) {
                waitForElementPresenceBy(locator, errorMessage + "\nCannot find " + by, defaultTimeout);
                return;
            }
            swipeUpQick();
            ++currentLoop;
        }
    }

    public void swipeLeftforElement(String locator, String errorMessage){
        WebElement element = waitForElementPresenceBy(locator, errorMessage);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    public String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public int getAmountOfFoundedElements(String locator){
        By by = this.getLocatorByString(locator);
        List result = driver.findElements(by);
        return result.size();
    }

    public void assertElementPresent(By by, String attribute, String errorMessage){
        try {
            Assert.assertTrue(errorMessage + "\n Cannot find an attribute value for " + attribute, !driver.findElement(by).getAttribute(attribute).isEmpty());
        } catch (Throwable t) {
            System.out.println(errorMessage + "\n" + t);
            Assert.fail();
        }
    }

    private By getLocatorByString(String locator_with_type){
        String[] given_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String type = given_locator[0];
        String locator = given_locator[1];
        if (type.equals("xpath")) {
            return By.xpath(locator);
        } else if (type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }

    }
}
