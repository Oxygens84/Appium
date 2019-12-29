package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

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

    public AndroidMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
