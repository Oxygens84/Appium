package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
            ACTIONS_OPTION,
            ADD_ARTICLE,
            ONBOARDING,
            MY_LIST_FOLDER_TITLE,
            OK_BUTTON,
            SEARCH_ELEMENT_BY_SUBSTRING_TPL,
            SEARCH_ELEMENTS;

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHOD */
    private static String getResultSearchElement(String substring){
        return SEARCH_ELEMENT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
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
        this.waitArticleAppearInMyList(title);
        String searchResultXpath = getResultSearchElement(title);
        this.swipeLeftforElement(searchResultXpath, "My list Page: cannot delete article from my list");
        if (Platform.getInstance().isIOS()) {
            this.clickElementToRightUpperConner(searchResultXpath, "My list Page: cannot delete article from my list");
        }
        this.waitArticleDisappearFromMyList(title);
    }

    public void waitArticleAppearInMyList(String title){
        String searchResultXpath = getResultSearchElement(title);
        this.waitForElementPresenceBy(searchResultXpath, "My list Page: didn't appear article for substring " + title);
    }

    public void waitArticleDisappearFromMyList(String title){
        String searchResultXpath = getResultSearchElement(title);
        this.waitForElementInvisibilityBy(searchResultXpath, "My list Page: article is still present in My list");
    }

    public void addArticleToMySavedArticles(){
        this.waitForElementAndClick(ADD_ARTICLE, "Article Page: cannot add article to my saved articles");
    }

    public void checkAmountOfElements(int expectedSize){
        Assert.assertEquals(
                "My list Page: amount of elements invalid",
                expectedSize,
                this.getAmountOfFoundedElements(SEARCH_ELEMENTS));
    }
}
