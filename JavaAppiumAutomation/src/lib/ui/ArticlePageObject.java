package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            ARTICLE_TITLE = "id:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";

    private static final Integer MAX_SWIPE = 5;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForArticleTitle(){
        return this.waitForElementPresenceBy(ARTICLE_TITLE, "Article Page: cannot find article's title");
    }

    public String getArticleTitle(){
        WebElement titleElement = waitForArticleTitle();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Article Page: cannot find article's footer" , MAX_SWIPE);
    }


}
