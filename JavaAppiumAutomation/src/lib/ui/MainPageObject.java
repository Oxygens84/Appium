package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected RemoteWebDriver driver;
    private int defaultTimeout = 35;
    private int defaultTimeOfSwipe = 200;


    public MainPageObject(RemoteWebDriver driver){
        this.driver = driver;
    }

    public WebElement waitForElementPresence(String locator, String errorMessage, long timeoutInSeconds){
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

    public Boolean isTextPresentedInElement(String locator, String text, String errorMessage, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(by, text)
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

    public WebElement waitForElementPresence(String locator, String errorMessage) {
        return waitForElementPresence(locator, errorMessage + "\nCannot find " + locator, defaultTimeout);
    }

    public List<WebElement> waitForElementsPresenceBy(String locator, String errorMessage) {
        return waitForElementsPresenceBy(locator, errorMessage + "\nCannot find " + locator, defaultTimeout);
    }

    public Boolean waitForElementInvisibilityBy(String locator, String errorMessage){
        return waitForElementInvisibilityBy(locator, errorMessage + "\nFounded elements " + locator, defaultTimeout);
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage) {
        WebElement element = waitForElementPresence(locator, errorMessage);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSetText(String locator, String text, String errorMessage){
        WebElement element = waitForElementPresence(locator, errorMessage);
        element.sendKeys(text);
        return element;
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage){
        WebElement element = waitForElementPresence(locator, errorMessage);
        return element.getAttribute(attribute);
    }

    public WebElement waitForElementAndClear(String locator, String errorMessage) {
        WebElement element = waitForElementPresence(locator, errorMessage);
        element.clear();
        return element;
    }

    public String getTextFrom(WebElement element){
        return element.getAttribute("text");
    }

    public void swipeUp(int timeOfSwipe){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            TouchAction actions = new TouchAction(driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);
            actions
                    .press(x, start_y)
                    .waitAction()
                    .moveTo(x, end_y)
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeUp is not acceptable for platform " + Platform.getInstance().getPlatformValue());
        }
    }

    public void swipeUpQick(){
        swipeUp(defaultTimeOfSwipe);
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes){
        By by = this.getLocatorByString(locator);
        int currentLoop = 0;
        while ( driver.findElements(by).size() == 0){
            if (currentLoop == maxSwipes) {
                waitForElementPresence(locator, errorMessage + "\nCannot find " + by, defaultTimeout);
                return;
            }
            swipeUpQick();
            ++currentLoop;
        }
    }

    public void swipeUpTillElementAppear(String locator, String errorMessage, int maxSwipes){
        int already_swiped = 0;
        while (!this.isElementLocatedOnScreen(locator, errorMessage)){
            if (already_swiped > maxSwipes){
                Assert.assertTrue(errorMessage, this.isElementLocatedOnScreen(locator, errorMessage));
            }
            swipeUpQick();
            ++already_swiped;
        }
    }

    public void clickElementToRightUpperConner(String locator, String errorMessage){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WebElement element = waitForElementPresence(locator + "/..", errorMessage);
            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            int width = element.getSize().getWidth();

            int point_to_click_x = (right_x + width) - 3;
            int point_to_click_y = middle_y;

            TouchAction action = new TouchAction(driver);
            action.tap(point_to_click_x, point_to_click_y).perform();
        } else {
                System.out.println("Method clickElementToRightUpperConner is not acceptable for platform " + Platform.getInstance().getPlatformValue());
        }
    }

    public void swipeLeftforElement(String locator, String errorMessage){
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WebElement element = waitForElementPresence(locator, errorMessage);
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            TouchAction action = new TouchAction(driver);

            action.press(right_x, middle_y);
            action.waitAction(300);

            if (Platform.getInstance().isAndroid()) {
                action.moveTo(left_x, middle_y);
            } else {
                int offset_x = (-1 * element.getSize().getWidth());
                action.moveTo(offset_x, 0);
            }

            action.release();
            action.perform();
        } else {
            System.out.println("Method swipeLeftforElement is not acceptable for platform " + Platform.getInstance().getPlatformValue());
        }
    }

    public String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public int getAmountOfFoundedElements(String locator){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
        wait.withMessage("Cannot find element for " + locator + " \n");
        try {
            wait.until(
                    ExpectedConditions.presenceOfElementLocated(by)
            );
            return driver.findElements(by).size();
        } catch (Exception e) {
            return 0;
        }
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
        } else if (type.equals("css")) {
            return By.cssSelector(locator);
        } else {
                throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }

    public Boolean isElementLocatedOnScreen(String locator, String errorMessage){
        int element_locator_by_y = this.waitForElementPresence(locator, errorMessage).getLocation().getY();
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_locator_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_locator_by_y < screen_size_by_y;
    }

    public void scrollWebPageUp(){
        if (Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0,250)");
        } else {
            System.out.println("Method scrollWebPageUp is not acceptable for platform " + Platform.getInstance().getPlatformValue());
        }
    }

    public void scrollWebPageTillElementVisible(String locator, String errorMessage, int maxSwipes){
        int already_swiped = 0;
        WebElement element = this.waitForElementPresence(locator, errorMessage);
        while (!this.isElementLocatedOnScreen(locator, errorMessage)){
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > maxSwipes){
                Assert.assertTrue(errorMessage, element.isDisplayed());
            }
        }
    }

    public Boolean isElementPresence(String locator){
        return this.getAmountOfFoundedElements(locator) > 0;
    }

    public void tryClickElementWithFewAttempts(String locator, String errorMessage, int attepmts){
        int current_attempt = 0;
        boolean need_more_attempts = true;
        while (need_more_attempts){
            try{
                this.waitForElementAndClick(locator, errorMessage);
                need_more_attempts = false;
            } catch (Exception e) {
                if (current_attempt > attepmts) {
                    this.waitForElementAndClick(locator, errorMessage);
                }
            }
            ++current_attempt;
        }
    }
}
