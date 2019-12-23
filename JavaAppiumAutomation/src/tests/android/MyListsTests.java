package tests.android;

import lib.CoreTestCase;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String
            SEARCH_TEXT_JAVA = "Java",
            SEARCH_TEXT_JAVA_RESULT = "oriented programming language",
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

        ArticlePageObject.waitForArticleTitle();
        MyListPageObject.addArticleToNewList(folderTitle);

        NavigationUI.returnToSearch();
        NavigationUI.goToMyLists();

        MyListPageObject.clickFolderWithSubstring(folderTitle);
        MyListPageObject.removeArticleFromMyList(articleTitle);
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
        ArticlePageObject.waitForArticleTitle();
        MyListPageObject.addArticleToNewList(folderTitle);
        NavigationUI.returnToSearch();

        String searchText2 = SEARCH_TEXT_APPIUM;
        String articleTitle2 = SEARCH_TEXT_APPIUM_RESULT;

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText2);
        SearchPageObject.waitForSearchResult(articleTitle2);
        SearchPageObject.clickArticleWithSubstring(articleTitle2);
        ArticlePageObject.waitForArticleTitle();
        MyListPageObject.addArticleToExistingList(folderTitle);
        NavigationUI.returnToSearch();

        NavigationUI.goToMyLists();
        MyListPageObject.clickFolderWithSubstring(folderTitle);
        MyListPageObject.removeArticleFromMyList(articleTitle1);
        MyListPageObject.waitArticleAppearInMyList(articleTitle2);
    }

}


