package lib.ui.android;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListPageObject {

    static {
        ACTIONS_OPTION = "xpath://*[@content-desc='More options']";
        ADD_ARTICLE = "xpath://*[@text='Add to reading list']";
        ONBOARDING = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_FOLDER_TITLE = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "xpath://*[@text = 'OK']";
        SEARCH_ELEMENT_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]";
        SEARCH_ELEMENTS = "id:org.wikipedia:id/page_list_item_container";
    }

    public AndroidMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
