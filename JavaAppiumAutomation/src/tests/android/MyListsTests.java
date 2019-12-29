package tests.android;

import lib.CoreTestCase;
import lib.Platform;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String
            SEARCH_TEXT_JAVA = "Java",
            SEARCH_TEXT_JAVA_RESULT = "programming language",
            SEARCH_TEXT_APPIUM = "Appium",
            SEARCH_TEXT_APPIUM_RESULT = "Appium";

    @Test
    public void testSaveFirstArticleToMyList(){
        String folderTitle = "Test Folder " + MainPageObject.getCurrentDate();
        String searchText = SEARCH_TEXT_JAVA;
        String articleTitle = SEARCH_TEXT_JAVA_RESULT;

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText);
        SearchPageObject.waitForSearchResult(articleTitle);
        SearchPageObject.clickArticleWithSubstring(articleTitle);

        if (Platform.getInstance().isAndroid()){
            MyListPageObject.addArticleToNewList(folderTitle);
        } else {
            MyListPageObject.addArticleToMySavedArticles();
        }
        NavigationUI.returnToSearch();
        NavigationUI.goToMyLists();

        if (Platform.getInstance().isAndroid()){
            MyListPageObject.clickFolderWithSubstring(folderTitle);
        }

        MyListPageObject.checkAmountOfElements(1);
        MyListPageObject.removeArticleFromMyList(articleTitle);
        MyListPageObject.checkAmountOfElements(0);

    }

    @Test
    public void testSaveTwoArticlesAndDeleteOne(){
        String folderTitle = "Test Folder " + MainPageObject.getCurrentDate();

        String searchText1 = SEARCH_TEXT_JAVA;
        String articleTitle1 = SEARCH_TEXT_JAVA_RESULT;

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText1);
        SearchPageObject.waitForSearchResult(articleTitle1);
        SearchPageObject.clickArticleWithSubstring(articleTitle1);

        if (Platform.getInstance().isAndroid()){
            MyListPageObject.addArticleToNewList(folderTitle);
        } else {
            MyListPageObject.addArticleToMySavedArticles();
        }
        NavigationUI.returnToSearch();

        String searchText2 = SEARCH_TEXT_APPIUM;
        String articleTitle2 = SEARCH_TEXT_APPIUM_RESULT;

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText2);
        SearchPageObject.waitForSearchResult(articleTitle2);
        SearchPageObject.clickArticleWithSubstring(articleTitle2);

        if (Platform.getInstance().isAndroid()){
            MyListPageObject.addArticleToExistingList(folderTitle);
        } else {
            MyListPageObject.addArticleToMySavedArticles();
        }
        NavigationUI.returnToSearch();

        NavigationUI.goToMyLists();

        if (Platform.getInstance().isAndroid()){
            MyListPageObject.clickFolderWithSubstring(folderTitle);
        }

        MyListPageObject.checkAmountOfElements(2);
        MyListPageObject.removeArticleFromMyList(articleTitle1);
        MyListPageObject.checkAmountOfElements(1);
        MyListPageObject.waitArticleAppearInMyList(articleTitle2);
    }

}


