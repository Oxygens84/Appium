package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE_BY_SUBSTRING_TP = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        FOOTER_ELEMENT = "xpath://*[contains(@name,'View article in browser')]";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
