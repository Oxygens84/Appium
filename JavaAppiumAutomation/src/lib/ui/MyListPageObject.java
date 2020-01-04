package lib.ui;

import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
            ACTIONS_OPTION,
            ADD_ARTICLE,
            ONBOARDING,
            MY_LIST_FOLDER_TITLE,
            OK_BUTTON,
            SEARCH_ELEMENT_BY_SUBSTRING_TPL,
            SEARCH_ELEMENTS,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            REMOVE_FROM_SAVED_BUTTON;

    public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring){
        return SEARCH_ELEMENT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public String getRemoveButtonByTitle(String title){
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", title);

    }

    public void addArticleToNewList(String title){
        this.waitForElementAndClick(ACTIONS_OPTION, "My list Page: cannot find Options button for article");
        this.waitForElementAndClick(ADD_ARTICLE, "My list Page: cannot find Option 'Add to reading list'");
        this.waitForElementAndClick(ONBOARDING, "My list Page: cannot find and click 'Got it' tip overlay");
        setTitleForMyListFolder(title);
        this.waitForElementAndClick(OK_BUTTON, "My list page: cannot find and click OK button for folder creating");
    }

    public void addArticleToExistingList(String folderTitle){
        this.waitForElementAndClick(ACTIONS_OPTION, "My list Page: cannot find Options button for article");
        this.waitForElementAndClick(ADD_ARTICLE, "My list Page: cannot find Option 'Add to reading list'");
        this.clickFolderWithSubstring(folderTitle);
    }

    public void setTitleForMyListFolder(String title){
        this.waitForElementAndClear(MY_LIST_FOLDER_TITLE, "My list Page: cannot find and clear Title for my list folder");
        this.waitForElementAndSetText(MY_LIST_FOLDER_TITLE, title, "My list Page: cannot set new title for My list folder");
    }

    public void clickFolderWithSubstring(String title){
        if (Platform.getInstance().isAndroid()) {
            String searchResultXpath = getResultSearchElement(title);
            this.waitForElementAndClick(searchResultXpath, "My list Page: cannot find and click Folder for substring " + title);
        }
    }

    public void removeArticleFromMyList(String title){
        this.waitArticleAppearInMyList(title);
        String searchResultXpath = getResultSearchElement(title);

        if (Platform.getInstance().isAndroid()){
            this.swipeLeftforElement(searchResultXpath, "My list Page: cannot delete article from my list");
        }
        if (Platform.getInstance().isIOS()){
            this.swipeLeftforElement(searchResultXpath, "My list Page: cannot delete article from my list");
            this.clickElementToRightUpperConner(searchResultXpath, "My list Page: cannot delete article from my list");
        }
        if (Platform.getInstance().isMW()){
            String removeLocator = getRemoveButtonByTitle(title);
            this.waitForElementAndClick(removeLocator, "My list Page: cannot find and click button to remove article from saved");
            driver.navigate().refresh();
        }

        this.waitArticleDisappearFromMyList(title);
    }

    public void waitArticleAppearInMyList(String title){
        String searchResultXpath = getResultSearchElement(title);
        this.waitForElementPresence(searchResultXpath, "My list Page: didn't appear article for substring " + title);
    }

    public void waitArticleDisappearFromMyList(String title){
        String searchResultXpath = getResultSearchElement(title);
        this.waitForElementInvisibilityBy(searchResultXpath, "My list Page: article is still present in My list");
    }

    public void addArticleToMySavedArticles(){
        this.waitForElementsPresenceBy(ADD_ARTICLE, "Article Page: cannot find button to save articke");
        this.waitForElementAndClick(ADD_ARTICLE, "Article Page: cannot add article to my saved articles");
    }

    public void checkAmountOfElements(int expectedSize){
        Assert.assertEquals(
                "My list Page: amount of elements invalid",
                expectedSize,
                this.getAmountOfFoundedElements(SEARCH_ELEMENTS));
    }

    public void removeArticleFromSavedIfItAdded(){
        if (this.isElementPresence(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Article Page: cannot find button to remove article from saved");
            this.waitForElementsPresenceBy(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Article Page: cannot find button to save article after removing");
        }
    }

    public void saveArticle(String folderTitle){
        if (Platform.getInstance().isAndroid()){
            addArticleToNewList(folderTitle);
        } else {
            addArticleToMySavedArticles();
        }
    }

    public void saveArticleIntoExistingFolder(String folderTitle){
        if (Platform.getInstance().isAndroid()){
            addArticleToExistingList(folderTitle);
        } else {
            addArticleToMySavedArticles();
        }
    }
}
