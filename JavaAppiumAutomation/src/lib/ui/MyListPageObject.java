package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    private static final String
            ACTIONS_OPTION ="xpath://*[@content-desc='More options']",
            ADD_ARTICLE = "xpath://*[@text='Add to reading list']",
            ONBOARDING = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_FOLDER_TITLE = "id:org.wikipedia:id/text_input",
            OK_BUTTON = "xpath://*[@text = 'OK']",
            SEARCH_ELEMENT_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]";

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHOD */
    private static String getResultSearchElement(String substring){
        return "xpath:" + SEARCH_ELEMENT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHOD */

    public void addArticleToNewList(String title){
        this.waitForElementAndClick(ACTIONS_OPTION, "My list Page: cannot find Options button for article");
        this.waitForElementAndClick(ADD_ARTICLE, "My list Page: cannot find Option 'Add to reading list'");
        this.waitForElementAndClick(ONBOARDING, "My list Page: cannot find and click 'Got it' tip overlay");
        setTitleForMyListFolder(title);
        this.waitForElementAndClick(OK_BUTTON, "My list page: cannot find and click OK button for folder creating");
    }

    public void addArticleToExistingList(String title){
        this.waitForElementAndClick(ACTIONS_OPTION, "My list Page: cannot find Options button for article");
        this.waitForElementAndClick(ADD_ARTICLE, "My list Page: cannot find Option 'Add to reading list'");
        this.clickFolderWithSubstring(title);
    }

    public void setTitleForMyListFolder(String title){
        this.waitForElementAndClear(MY_LIST_FOLDER_TITLE, "My list Page: cannot find and clear Title for my list folder");
        this.waitForElementAndSetText(MY_LIST_FOLDER_TITLE, title, "My list Page: cannot set new title for My list folder");
    }

    public void clickFolderWithSubstring(String title){
        String searchResultXpath = getResultSearchElement(title);
        this.waitForElementAndClick(searchResultXpath, "My list Page: cannot find and click Folder for substring " + title);
    }

    public void removeArticleFromMyList(String title){
        waitArticleAppearInMyList(title);
        String searchResultXpath = getResultSearchElement(title);
        this.swipeLeftforElement(searchResultXpath, "My list Page: cannot delete article from my list");
        waitArticleDisappearFromMyList(title);
    }

    public void waitArticleAppearInMyList(String title){
        String searchResultXpath = getResultSearchElement(title);
        this.waitForElementPresenceBy(searchResultXpath, "My list Page: didn't appear article for substring " + title);
    }

    public void waitArticleDisappearFromMyList(String title){
        String searchResultXpath = getResultSearchElement(title);
        this.waitForElementInvisibilityBy(searchResultXpath, "My list Page: article is still present in My list");
    }

}
