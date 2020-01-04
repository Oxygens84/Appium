package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject {

    static {
        ADD_ARTICLE = "css:a[data-event-name='menu.watch']";
        SEARCH_ELEMENT_BY_SUBSTRING_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{SUBSTRING}')]";
        SEARCH_ELEMENTS = "xpath://li[contains(@class,'page-summary with-watchstar')]";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watch button";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watched watched button";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]/../../a[contains(@class,'watched')]";
    }

    public MWMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}