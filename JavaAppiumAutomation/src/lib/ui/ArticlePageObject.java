package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            ARTICLE_TITLE,
            ARTICLE_TITLE_BY_SUBSTRING_TPL,
            FOOTER_ELEMENT;

    private static final Integer MAX_SWIPE = 5;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHOD */
    private static String getResultSearchElement(String substring){
        return ARTICLE_TITLE_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHOD */

    public WebElement waitForArticleTitle(String text){
        String title = getResultSearchElement(text);
        return this.waitForElementPresence(title, "Article Page: cannot find article's title");
    }

    public WebElement waitForArticleTitle(){
        return this.waitForElementPresence(ARTICLE_TITLE, "Article Page: cannot find article's title");
    }

    public String getArticleTitle(String withText){
        WebElement titleElement;
        if (Platform.getInstance().isAndroid()){
            titleElement = this.waitForArticleTitle(withText);
            return titleElement.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            titleElement = this.waitForArticleTitle(withText);
            return titleElement.getAttribute("name");
        } else {
            titleElement = waitForArticleTitle();
            return titleElement.getText();
        }
    }

    public void swipeToFooter(){
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Article Page: cannot find article's footer" , MAX_SWIPE);
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Article Page: cannot swipe till the article footer", 5);
        } else {
            this.scrollWebPageTillElementVisible(FOOTER_ELEMENT, "Article Page: cannot swipe till the article footer", 5);
        }
    }

}
