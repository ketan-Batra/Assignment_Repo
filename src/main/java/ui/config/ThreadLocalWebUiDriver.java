package ui.config;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import ui.stepdefinition.common.GenericFunctions;

import java.util.Collections;

public class ThreadLocalWebUiDriver {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String Browser = System.getProperty("browser");

    public void setDriver(){
        if(Browser.equalsIgnoreCase("chrome")){
        WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("disable-popup-blocking"));
        driver.set(new ChromeDriver(options));
        }else if(Browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        }else{
            System.out.println("Provided Browser :"+Browser+" driver doesn't exist");
        }
    }

    public WebDriver getDriver()
    {
        return driver.get();
    }

    @After
    public void closeBrowser()
    {
        if(driver.get()!=null){
        driver.get().quit();
        driver.remove();
        }
    }

}
