package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        FOOTER_ELEMENT = "xpath://*[contains(@name,'View article in browser')]";
    }

    public IOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
