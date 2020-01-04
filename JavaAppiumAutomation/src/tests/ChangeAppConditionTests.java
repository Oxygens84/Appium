package tests;

import lib.CoreTestCase;
import lib.Platform;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    private static final String
            SEARCH_TEXT_JAVA = "Java (programming language)",
            SEARCH_TEXT_JAVA_RESULT = "oriented programming language";

    @Test
    public void testChangeScreenOrientationForSearch(){
        if (Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(SEARCH_TEXT_JAVA);
        SearchPageObject.waitForSearchResult(SEARCH_TEXT_JAVA_RESULT);
        SearchPageObject.clickArticleWithSubstring(SEARCH_TEXT_JAVA_RESULT);

        String titleBeforeRotation = ArticlePageObject.getArticleTitle(SEARCH_TEXT_JAVA);
        this.rotateScreenToLandscapeMode();
        String titleAfterRotation = ArticlePageObject.getArticleTitle(SEARCH_TEXT_JAVA);
        assertEquals("Article title changed after rotation to Landscape", titleBeforeRotation, titleAfterRotation);

        titleBeforeRotation = ArticlePageObject.getArticleTitle(SEARCH_TEXT_JAVA);
        this.rotateScreenToPortraitMode();
        titleAfterRotation = ArticlePageObject.getArticleTitle(SEARCH_TEXT_JAVA);
        assertEquals("Article title changed after rotation to Portrait", titleBeforeRotation, titleAfterRotation);
    }

    @Test
    public void testSendAppInBackgroundMode(){
        if (Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(SEARCH_TEXT_JAVA);
        SearchPageObject.waitForSearchResult(SEARCH_TEXT_JAVA_RESULT);
        SearchPageObject.clickArticleWithSubstring(SEARCH_TEXT_JAVA_RESULT);

        String titleBefore = ArticlePageObject.getArticleTitle(SEARCH_TEXT_JAVA);
        this.runAppInBackground();
        String titleAfter = ArticlePageObject.getArticleTitle(SEARCH_TEXT_JAVA);
        assertEquals("Article title changed after rotation to Portrait", titleBefore, titleAfter);
    }

}
