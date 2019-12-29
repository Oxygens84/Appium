package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            ARTICLE_TITLE,
            ARTICLE_TITLE_BY_SUBSTRING_TP,
            FOOTER_ELEMENT;

    private static final Integer MAX_SWIPE = 5;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHOD */
    private static String getResultSearchElement(String substring){
        return ARTICLE_TITLE_BY_SUBSTRING_TP.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHOD */

    public WebElement waitForArticleTitle(String text){
        String title = getResultSearchElement(text);
        return this.waitForElementPresenceBy(title, "Article Page: cannot find article's title");
    }

    public WebElement waitForArticleTitle(){
        return this.waitForElementPresenceBy(ARTICLE_TITLE, "Article Page: cannot find article's title");
    }

    public String getArticleTitle(String withText){
        WebElement titleElement = this.waitForArticleTitle(withText);
        if (Platform.getInstance().isAndroid()){
            return titleElement.getAttribute("text");
        } else {
            return titleElement.getAttribute("name");
        }
    }

    public void swipeToFooter(){
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Article Page: cannot find article's footer" , MAX_SWIPE);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Article Page: cannot swipe till the article footer", 5);
        }
    }

}
