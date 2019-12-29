package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE_BY_SUBSTRING_TP = "xpath://*[contains(@text,{SUBSTRING})]";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
