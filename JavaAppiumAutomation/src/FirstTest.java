/*
 * Created by Oxana Lobysheva on 12/11/2019.
 */

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.nio.file.FileSystems;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    private String searchToolbarByXpath = "//*[contains(@text, 'Wikipedia')]";
    private String searchInputFieldById = "org.wikipedia:id/search_src_text";
    private String searchResultListById = "org.wikipedia:id/search_results_list";
    private String searchResultElementsById = "org.wikipedia:id/page_list_item_container";
    private String searchCloseBtnById = "org.wikipedia:id/search_close_btn";
    private String searchJavaArticleByXpath = "//*[contains(@text,'oriented programming language')]";
    private String searchAppiumArticleByXpath = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']";
    private String searchArticleEndingByXpath = "//*[@text='View page in browser']";
    private String searchOptionsBtnByXpath = "//*[@content-desc='More options']";
    private String searchAddArticleToListOption = "//*[@text='Add to reading list']";
    private String searchOnboardingBtnById = "org.wikipedia:id/onboarding_button";
    private String searchInputTitleForSavingById = "org.wikipedia:id/text_input";
    private String searchOkBtneByXpath = "//*[@text = 'OK']";
    private String searchNavigateUpBtnByXpath = "//android.widget.ImageButton[@content-desc='Navigate up']";
    private String searchMyListsOptionByXpath = "//android.widget.FrameLayout[@content-desc='My lists']";
    private String searchNoResultsFoundLabel = "//*[@text='No results found']";
    private String searchArticleTitleById = "org.wikipedia:id/view_page_title_text";
    private int defaultTimeout = 35;

    @Before
    public void setUp() throws Exception{

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app",FileSystems.getDefault().getPath(".").toAbsolutePath().normalize().toString() + "/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    //@Test
    public void searchPromptTest(){

        waitForElementAndClick(By.xpath(searchToolbarByXpath), "Cannot find Search toolbar");
        WebElement searchInput = waitForElementPresenceBy(By.id(searchInputFieldById), "Cannot find Input field for search");
        String searchPrompt = getTextFrom(searchInput);
        Assert.assertEquals("Search…", searchPrompt);

    }

    //@Test
    public void cancelSearchTest() {

        waitForElementAndClick(By.xpath(searchToolbarByXpath), "Cannot find Search toolbar");
        WebElement searchInput = waitForElementPresenceBy(By.id(searchInputFieldById), "Cannot find Search input field");
        searchInput.sendKeys("Appium");
        int searchResult = getAmountOfFoundedElements(By.id(searchResultElementsById));
        Assert.assertTrue(searchResultElementsById + " is empty", searchResult > 0);
        waitForElementAndClick(By.id(searchCloseBtnById), "Cannot find Close button");
        Assert.assertTrue(waitForElementInvisibilityBy(By.id(searchResultElementsById),"Check of invisibility of element failed"));

    }

    //@Test
    public void swipeUpTest() {

        waitForElementAndClick(By.xpath(searchToolbarByXpath), "Cannot find Search toolbar");
        WebElement searchInput = waitForElementPresenceBy(By.id(searchInputFieldById), "Cannot find Input field for search");
        searchInput.sendKeys("Appium");
        List<WebElement> result = waitForElementsPresenceBy(By.id(searchResultElementsById), "Cannot find search result");
        Assert.assertTrue(searchResultElementsById + " is empty", result.size() > 0);
        waitForElementAndClick(By.xpath(searchAppiumArticleByXpath), "Cannot find Appium article");
        swipeUpToFindElement(By.xpath(searchArticleEndingByXpath), "Cannot find the end of the article", 5);
    }

    //@Test
    public void saveFirstArticleToMyList(){
        waitForElementAndClick(By.xpath(searchToolbarByXpath), "Error. Step - find Search toolbar");
        WebElement searchInput = waitForElementPresenceBy(By.id(searchInputFieldById), "Error. Step - find Input field for search");
        searchInput.sendKeys("Java");
        List<WebElement> result = waitForElementsPresenceBy(By.id(searchResultElementsById), "Error. Step - find Search result");
        Assert.assertTrue(searchResultElementsById + " is empty", result.size() > 0);
        waitForElementAndClick(By.xpath(searchJavaArticleByXpath), "Error. Step - find an article");
        waitForElementAndClick(By.xpath(searchOptionsBtnByXpath), "Error. Step - find Options button for article");
        waitForElementAndClick(By.xpath(searchAddArticleToListOption), "Error. Step - find Option - Add article to reading list");
        waitForElementAndClick(By.id(searchOnboardingBtnById), "Error. Step - click 'Got it' tip overlay");
        WebElement inputTitleForArticleSaving = waitForElementPresenceBy(By.id(searchInputTitleForSavingById), "Error. Step - find and clear Title for my list folder");
        String title = "Test Folder " + getCurrentDate();
        inputTitleForArticleSaving.sendKeys(title);
        waitForElementAndClick(By.xpath(searchOkBtneByXpath), "Error. Step - click OK button for folder creating");
        waitForElementAndClick(By.xpath(searchNavigateUpBtnByXpath), "Error. Step - click X button");
        waitForElementAndClick(By.xpath(searchMyListsOptionByXpath), "Error. Step - find My lists in navigation bar");
        waitForElementAndClick(By.xpath("//android.widget.TextView[contains(@text,'" + title + "')]"), "Error. Step - find created folder");
        waitForElementPresenceBy(By.xpath(searchJavaArticleByXpath), "Error. Step - find saved article in the list");
        swipeLeftforElement(By.xpath(searchJavaArticleByXpath), "Error. Step - delete article from my list");
        waitForElementInvisibilityBy(By.xpath(searchJavaArticleByXpath), "Error. Step - check invisibility deleted article");
    }

    //@Test
    public void emptyResultSearchTest() {

        waitForElementAndClick(By.xpath(searchToolbarByXpath), "Cannot find Search toolbar");
        WebElement searchInput = waitForElementPresenceBy(By.id(searchInputFieldById), "Cannot find Search input field");
        searchInput.sendKeys("Appiummmmmmmmm");
        waitForElementPresenceBy(By.xpath(searchNoResultsFoundLabel), "Cannot find No results found label");
        int searchResult = getAmountOfFoundedElements(By.id(searchResultElementsById));
        Assert.assertTrue(searchResultElementsById + " is empty", searchResult == 0);

    }

    //@Test
    public void changeScreenOrientationForSearchTest(){
        waitForElementAndClick(By.xpath(searchToolbarByXpath), "Cannot find Search toolbar");
        waitForElementAndSetText(By.id(searchInputFieldById), "Java", "Cannot find Search input field");
        waitForElementAndClick(By.xpath(searchJavaArticleByXpath), "Cannot find an article");
        String titleBeforeRotation = waitForElementAndGetAttribute(By.id(searchArticleTitleById), "text", "Cannot get Article Title before rotation");
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterRotation = waitForElementAndGetAttribute(By.id(searchArticleTitleById), "text", "Cannot get Article Title after rotation");
        Assert.assertEquals("Article title changed after toration", titleBeforeRotation, titleAfterRotation);
    }

    //@Test
    public void sendAppInBackgroundModeTest(){
        waitForElementAndClick(By.xpath(searchToolbarByXpath), "Cannot find Search toolbar");
        waitForElementAndSetText(By.id(searchInputFieldById), "Java", "Cannot find Search input field");
        waitForElementAndClick(By.xpath(searchJavaArticleByXpath), "Cannot find an article");
        driver.runAppInBackground(5);
        waitForElementAndClick(By.xpath(searchJavaArticleByXpath), "Cannot find an article after returning from background");

    }

    @Test
    public void saveTwoArticlesAndDeleteOneTest(){

        String title = "Test Folder " + getCurrentDate();

        String firstSearch= "Java";
        waitForElementAndClick(By.xpath(searchToolbarByXpath), "Error. Step (first article) - find Search toolbar");
        waitForElementAndSetText(By.id(searchInputFieldById), firstSearch, "Error. Step (first article) - set a search parameter " + firstSearch);
        waitForElementAndClick(By.xpath(searchJavaArticleByXpath), "Error. Step (first article) - find an article by " + searchJavaArticleByXpath);
        waitForElementAndClick(By.xpath(searchOptionsBtnByXpath), "Error. Step (first article) - find Options button for article");
        waitForElementAndClick(By.xpath(searchAddArticleToListOption), "Error. Step (first article) - find Option - Add article to reading list");
        waitForElementAndClick(By.id(searchOnboardingBtnById), "Error. Step (first article) - click 'Got it' tip overlay");
        waitForElementAndSetText(By.id(searchInputTitleForSavingById), title, "Error. Step (first article) - find and set a Title for my list folder with title " + title);
        waitForElementAndClick(By.xpath(searchOkBtneByXpath), "Error. Step (first article) - click OK button for the folder creating");
        waitForElementAndClick(By.xpath(searchNavigateUpBtnByXpath), "Error. Step (first article) - click X button");

        String secondSearch= "Appium";
        waitForElementAndClick(By.xpath(searchToolbarByXpath), "Error. Step (second article) - find Search toolbar");
        waitForElementAndSetText(By.id(searchInputFieldById), secondSearch, "Error. Step (second article) - set a search parameter " + secondSearch);
        waitForElementAndClick(By.xpath(searchAppiumArticleByXpath), "Error. Step (second article) - find an article by " + searchAppiumArticleByXpath);
        waitForElementAndClick(By.xpath(searchOptionsBtnByXpath), "Error. Step (second article) - find Options button for article");
        waitForElementAndClick(By.xpath(searchAddArticleToListOption), "Error. Step (second article) - find Option - Add article to reading list");
        waitForElementAndClick(By.xpath("//android.widget.TextView[contains(@text,'" + title + "')]"), "Error. Step (second article) - find created folder with title " + title);
        waitForElementAndClick(By.xpath(searchNavigateUpBtnByXpath), "Error. Step (second article) - click X button");

        waitForElementAndClick(By.xpath(searchMyListsOptionByXpath), "Error. Step - find My lists in navigation bar");
        waitForElementAndClick(By.xpath("//android.widget.TextView[contains(@text,'" + title + "')]"), "Error. Step - find created folder in my lists");
        waitForElementPresenceBy(By.xpath(searchJavaArticleByXpath), "Error. Step - find saved article in the list by " + searchJavaArticleByXpath);
        swipeLeftforElement(By.xpath(searchJavaArticleByXpath), "Error. Step - delete article from my list");
        waitForElementInvisibilityBy(By.xpath(searchJavaArticleByXpath), "Error. Step - check invisibility deleted article");
        waitForElementPresenceBy(By.xpath(searchAppiumArticleByXpath), "Error. Step - find saved article in the list by " + searchAppiumArticleByXpath);

        waitForElementAndClick(By.xpath(searchAppiumArticleByXpath), "Error. Step - find an article by " + searchAppiumArticleByXpath);
        String titleSecondArticle = waitForElementAndGetAttribute(By.id(searchArticleTitleById), "text", "Error. Step - get the title last article in the list");
        Assert.assertEquals("Title of the last article in the list differs from expected", secondSearch, titleSecondArticle);

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

    private List<WebElement> waitForElementsPresenceBy(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    private Boolean waitForElementInvisibilityBy(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + '\n');
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresenceBy(By by, String errorMessage) {
        return waitForElementPresenceBy(by, errorMessage + "\nCannot find " + by, defaultTimeout);
    }

    private List<WebElement> waitForElementsPresenceBy(By by, String errorMessage) {
        return waitForElementsPresenceBy(by, errorMessage + "\nCannot find " + by, defaultTimeout);
    }

    private Boolean waitForElementInvisibilityBy(By by, String errorMessage){
        return waitForElementInvisibilityBy(by, errorMessage + "\nFounded elements " + by, defaultTimeout);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage) {
        WebElement element = waitForElementPresenceBy(by, errorMessage);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSetText(By by, String text, String errorMessage){
        WebElement element = waitForElementPresenceBy(by, errorMessage);
        element.sendKeys(text);
        return element;
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage){
        WebElement element = waitForElementPresenceBy(by, errorMessage);
        return element.getAttribute(attribute);
    }

    private WebElement waitForElementAndClear(By by, String errorMessage) {
        WebElement element = waitForElementPresenceBy(by, errorMessage);
        element.sendKeys("");
        return element;
    }

    private String getTextFrom(WebElement element){
        return element.getAttribute("text");
    }

    protected void swipUp(int timeOfSwipe){
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

    protected void swipeUpQick(){
        swipUp(200);
    }

    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipes){
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

    protected void swipeLeftforElement(By by, String errorMessage){
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

    protected String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    protected int getAmountOfFoundedElements(By by){
        List result = driver.findElements(by);
       return result.size();
    }

}
