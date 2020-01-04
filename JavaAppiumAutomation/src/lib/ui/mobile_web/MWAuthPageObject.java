package lib.ui.mobile_web;

import lib.ui.AuthorizationPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWAuthPageObject extends AuthorizationPageObject {

    static {
        LOGIN_BUTTON = "xpath://body/div/a[text()='Log in']";
        LOGIN_INPUT = "css:input[name='wpName']";
        PASSWORD_INPUT = "css:input[name='wpPassword']";
        SUBMIT_BUTTON = "css:button#wpLoginAttempt";
    }

    public MWAuthPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
