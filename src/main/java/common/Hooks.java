package common;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Before;
import mobile.config.InitializeAppiumDriver;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import ui.config.ThreadLocalWebUiDriver;

public class Hooks {

    ThreadLocalWebUiDriver uiDriver = new ThreadLocalWebUiDriver();
    InitializeAppiumDriver mobileDriver = new InitializeAppiumDriver();

    public void startUiTestExecution(){
        uiDriver.setDriver();
    }

    public void startMobileTestExecution(){
        mobileDriver.setMobileAppiumDriver();
    }

    @Before()
    public void startExecution(){
        String browser = System.getProperty("browser");
        String platform = System.getProperty("platform");
        PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\main\\java\\logger\\log4j.properties");
        if(browser!=null){
            startUiTestExecution();
        }else if(platform!=null){
            startMobileTestExecution();
        }
    }

    public AppiumDriver getAppiumDriver(){
        return mobileDriver.getMobileDriver();
    }

    public WebDriver getWebDriver(){
        return uiDriver.getDriver();
    }

}
