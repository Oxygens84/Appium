package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    public RemoteWebDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()){
            return new AndroidDriver(new URL(APPIUM_URL), this.getAndroidCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(new URL(APPIUM_URL), this.getIosCapabilities());
        } else if (this.isMW()) {
            return new ChromeDriver(this.getChromeOptions());
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

    public boolean isMW(){
        return isPlatform(PLATFORM_WEB);
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

    public String getPlatformValue(){
        return System.getenv("platform");
    }

    private Boolean isPlatform(String testPlatform){
        String platform = this.getPlatformValue();
        return testPlatform.equals(platform);
    }

    private ChromeOptions getChromeOptions(){
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 1.0);
        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceName", "Galaxy S5");
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Galaxy S5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
        ChromeOptions chromeoptions = new ChromeOptions();
        chromeoptions.addArguments("window-size=360,640");
        return chromeoptions;
    }
}
