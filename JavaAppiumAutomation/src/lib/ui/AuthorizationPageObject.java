package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    protected static String
            LOGIN_BUTTON,
            LOGIN_INPUT,
            PASSWORD_INPUT,
            SUBMIT_BUTTON;

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementAndClick(LOGIN_BUTTON, "Auth Page: cannot find and click Auth button");
        this.waitForElementPresence(LOGIN_INPUT, "Auth Page: cannot find Login field");
        this.waitForElementPresence(PASSWORD_INPUT, "Auth Page: cannot find Password field");
    }

    public void enterLoginAndPassword(String login, String password) {
        this.waitForElementAndSetText(LOGIN_INPUT, login,"Auth Page: cannot find and set text for Login");
        this.waitForElementAndSetText(PASSWORD_INPUT, password,"Auth Page: cannot find and set text for Password");
    }

    public void clickSubmitButton(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Auth Page: cannot find and click Submit auth button");
    }
}
