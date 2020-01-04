package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
