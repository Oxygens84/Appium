package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    private static final String
            ACTIONS_OPTION ="//*[@content-desc='More options']",
            ADD_ARTICLE = "//*[@text='Add to reading list']",
            ONBOARDING = "org.wikipedia:id/onboarding_button",
            MY_LIST_FOLDER_TITLE = "org.wikipedia:id/text_input",
            OK_BUTTON = "//*[@text = 'OK']",
            SEARCH_ELEMENT_BY_SUBSTRING_TPL = "//*[contains(@text,'{SUBSTRING}')]";

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHOD */
    private static String getResultSearchElement(String substring){
        return SEARCH_ELEMENT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHOD */

    public void addArticleToMyList(String title){
        this.waitForElementAndClick(By.xpath(ACTIONS_OPTION), "My list Page: cannot find Options button for article");
        this.waitForElementAndClick(By.xpath(ADD_ARTICLE), "My list Page: cannot find Option 'Add to reading list'");
        this.waitForElementAndClick(By.id(ONBOARDING), "My list Page: cannot find and click 'Got it' tip overlay");
        setTitleForMyListFolder(title);
        this.waitForElementAndClick(By.xpath(OK_BUTTON), "My list page: cannot find and click OK button for folder creating");
    }

    public void setTitleForMyListFolder(String title){
        this.waitForElementAndClear(By.id(MY_LIST_FOLDER_TITLE), "My list Page: cannot find and clear Title for my list folder");
        this.waitForElementAndSetText(By.id(MY_LIST_FOLDER_TITLE), title, "My list Page: cannot set new title for My list folder");
    }

    public void clickFolderWithSubstring(String title){
        String searchResultXpath = getResultSearchElement(title);
        this.waitForElementAndClick(By.xpath(searchResultXpath), "My list Page: cannot find and click Folder for substring " + title);
    }

    public void removeArticleFromMyList(String title){
        waitArticleAppearInMyList(title);
        String searchResultXpath = getResultSearchElement(title);
        this.swipeLeftforElement(By.xpath(searchResultXpath), "My list Page: cannot delete article from my list");
        waitArticleDisappearFromMyList(title);
    }

    public void waitArticleAppearInMyList(String title){
        String searchResultXpath = getResultSearchElement(title);
        this.waitForElementPresenceBy(By.xpath(searchResultXpath), "My list Page: didn't appear article for substring " + title);
    }

    public void waitArticleDisappearFromMyList(String title){
        String searchResultXpath = getResultSearchElement(title);
        this.waitForElementInvisibilityBy(By.xpath(searchResultXpath), "My list Page: article is still present in My list");
    }

}
