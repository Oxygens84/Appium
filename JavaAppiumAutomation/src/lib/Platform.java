package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.nio.file.FileSystems;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    public AppiumDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()){
            return new AndroidDriver(new URL(APPIUM_URL), this.getAndroidCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(new URL(APPIUM_URL), this.getIosCapabilities());
        } else {
            throw new Exception("Cannot find type of the driver");
        }
    }

    private static Platform instance;
    private Platform() {}
    public static Platform getInstance(){
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS(){
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidCapabilities() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app", FileSystems.getDefault().getPath(".").toAbsolutePath().normalize().toString() + "/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIosCapabilities() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","ios");
        capabilities.setCapability("deviceName","iPhone SE");
        capabilities.setCapability("platformVersion","12.4");
        capabilities.setCapability("automationName","XCUITest");
        capabilities.setCapability("app", FileSystems.getDefault().getPath(".").toAbsolutePath().normalize().toString() + "/apks/Wikipedia.app");
        return capabilities;
    }

    private String getPlatformValue(){
        return System.getenv("platform");
    }

    private Boolean isPlatform(String testPlatform){
        String platform = this.getPlatformValue();
        return testPlatform.equals(platform);
    }

}
