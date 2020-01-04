package lib.ui.factories;

import lib.ui.mobile_web.MWAuthPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthPageObjectFactory {

    public static MWAuthPageObject get(RemoteWebDriver driver){
        return new MWAuthPageObject(driver);
    }
}
