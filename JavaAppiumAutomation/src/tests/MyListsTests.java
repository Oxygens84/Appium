package tests;

import lib.CoreTestCase;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String
            SEARCH_TEXT_JAVA = "Java",
            SEARCH_TEXT_JAVA_RESULT = "programming language",
            SEARCH_TEXT_APPIUM = "Appium",
            SEARCH_TEXT_APPIUM_RESULT = "Appium",
            LOGIN = "wikiappium",
            PASSWORD = "qwe12345";

    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        String folderTitle = "Test Folder " + MainPageObject.getCurrentDate();
        String searchText = SEARCH_TEXT_JAVA;
        String searchResultText = SEARCH_TEXT_JAVA_RESULT;

        //TODO: clear/check SavedList before to start

        NavigationUI.openMainMenu();
        NavigationUI.goToAuth();
        //Thread.sleep(2000);
        AuthorizationPageObject.enterLoginAndPassword(LOGIN, PASSWORD);
        AuthorizationPageObject.clickSubmitButton();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText);
        SearchPageObject.waitForSearchResult(searchResultText);
        SearchPageObject.clickArticleWithSubstring(searchResultText);
        ArticlePageObject.waitForArticleTitle();
        MyListPageObject.saveArticle(folderTitle);

        NavigationUI.returnToSearch();
        NavigationUI.openMainMenu();
        NavigationUI.goToMyLists();
        MyListPageObject.clickFolderWithSubstring(folderTitle);

        MyListPageObject.checkAmountOfElements(1);
        MyListPageObject.removeArticleFromMyList(searchResultText);
        MyListPageObject.checkAmountOfElements(0);

    }

    @Test
    public void testSaveTwoArticlesAndDeleteOne() throws InterruptedException {
        String folderTitle = "Test Folder " + MainPageObject.getCurrentDate();
        String searchText1 = SEARCH_TEXT_JAVA;
        String searchResultText1 = SEARCH_TEXT_JAVA_RESULT;

        NavigationUI.openMainMenu();
        NavigationUI.goToAuth();
        AuthorizationPageObject.enterLoginAndPassword(LOGIN, PASSWORD);
        AuthorizationPageObject.clickSubmitButton();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText1);
        SearchPageObject.waitForSearchResult(searchResultText1);
        SearchPageObject.clickArticleWithSubstring(searchResultText1);
        ArticlePageObject.waitForArticleTitle();
        MyListPageObject.saveArticle(folderTitle);

        NavigationUI.returnToSearch();

        String searchText2 = SEARCH_TEXT_APPIUM;
        String searchResultText2 = SEARCH_TEXT_APPIUM_RESULT;

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchText2);
        SearchPageObject.waitForSearchResult(searchResultText2);
        SearchPageObject.clickArticleWithSubstring(searchResultText2);
        ArticlePageObject.waitForArticleTitle();
        MyListPageObject.saveArticle(folderTitle);

        NavigationUI.returnToSearch();

        NavigationUI.openMainMenu();
        NavigationUI.goToMyLists();
        MyListPageObject.clickFolderWithSubstring(folderTitle);

        MyListPageObject.checkAmountOfElements(2);
        MyListPageObject.removeArticleFromMyList(searchResultText1);
        MyListPageObject.checkAmountOfElements(1);
        MyListPageObject.waitArticleAppearInMyList(searchResultText2);
    }

}


