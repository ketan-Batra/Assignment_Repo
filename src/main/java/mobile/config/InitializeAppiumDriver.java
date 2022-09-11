package mobile.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileCommand;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.cucumber.java.After;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class InitializeAppiumDriver {

    public static AppiumDriver driver;
    public static Properties properties = new Properties();

    static {
        try {
        properties.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\mobile\\config\\application.properties"));
    }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setMobileAppiumDriver(){
        if(System.getProperty("platform").equalsIgnoreCase("ios")){
            driver = iOSDriver();
        }else {
            driver = androidDriver();
        }
        if (driver == null){
            throw new SessionNotCreatedException("Driver is NUll");
        }
    }

    public AppiumDriver getMobileDriver(){
        return driver;
    }

    public AppiumDriver iOSDriver(){
        try {
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability("TestName","Quick Start iOS Tests");
            dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            dc.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
            dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,1000);

            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new IOSDriver(url,dc);
            return driver;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public AppiumDriver androidDriver(){
        try {
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
            dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
            dc.setCapability(MobileCapabilityType.DEVICE_NAME, "ab85abee");
            dc.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
            dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,1000);
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AndroidDriver(url,dc);
            return driver;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
