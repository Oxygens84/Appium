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

        String titleBeforeRotation1 = ArticlePageObject.getArticleTitle(SEARCH_TEXT_JAVA);
        this.rotateScreenToLandscapeMode();
        String titleAfterRotation1 = ArticlePageObject.getArticleTitle(SEARCH_TEXT_JAVA);
        assertEquals("Article title changed after rotation to Landscape", titleBeforeRotation1, titleAfterRotation1);

        String titleBeforeRotation2 = ArticlePageObject.getArticleTitle(SEARCH_TEXT_JAVA);
        this.rotateScreenToPortraitMode();
        String titleAfterRotation2 = ArticlePageObject.getArticleTitle(SEARCH_TEXT_JAVA);
        assertEquals("Article title changed after rotation to Portrait", titleBeforeRotation2, titleAfterRotation2);
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
        //Android: bug? after background it opens start page (not previous article)
        assertEquals("Article title changed after send into background", titleBefore, titleAfter);
    }

}
