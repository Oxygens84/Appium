package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE = "css:#section_0";
        ARTICLE_TITLE_BY_SUBSTRING_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{SUBSTRING}')]";
        FOOTER_ELEMENT = "css:footer";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
