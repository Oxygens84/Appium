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

public class MainPageObject {

    protected AppiumDriver driver;
    private int defaultTimeout = 35;
    private int defaultTimeOfSwipe = 200;


    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }

    public WebElement waitForElementPresenceBy(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public List<WebElement> waitForElementsPresenceBy(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    public Boolean waitForElementInvisibilityBy(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresenceBy(By by, String errorMessage) {
        return waitForElementPresenceBy(by, errorMessage + "\nCannot find " + by, defaultTimeout);
    }

    public List<WebElement> waitForElementsPresenceBy(By by, String errorMessage) {
        return waitForElementsPresenceBy(by, errorMessage + "\nCannot find " + by, defaultTimeout);
    }

    public Boolean waitForElementInvisibilityBy(By by, String errorMessage){
        return waitForElementInvisibilityBy(by, errorMessage + "\nFounded elements " + by, defaultTimeout);
    }

    public WebElement waitForElementAndClick(By by, String errorMessage) {
        WebElement element = waitForElementPresenceBy(by, errorMessage);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSetText(By by, String text, String errorMessage){
        WebElement element = waitForElementPresenceBy(by, errorMessage);
        element.sendKeys(text);
        return element;
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String errorMessage){
        WebElement element = waitForElementPresenceBy(by, errorMessage);
        return element.getAttribute(attribute);
    }

    public WebElement waitForElementAndClear(By by, String errorMessage) {
        WebElement element = waitForElementPresenceBy(by, errorMessage);
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

    public void swipeUpToFindElement(By by, String errorMessage, int maxSwipes){
        int currentLoop = 0;
        while ( driver.findElements(by).size() == 0){
            if (currentLoop == maxSwipes) {
                waitForElementPresenceBy(by, errorMessage + "\nCannot find " + by, defaultTimeout);
                return;
            }
            swipeUpQick();
            ++currentLoop;
        }
    }

    public void swipeLeftforElement(By by, String errorMessage){
        WebElement element = waitForElementPresenceBy(by, errorMessage);
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

    public int getAmountOfFoundedElements(By by){
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

}
