package ui.stepdefinition.common;

import mobile.config.InitializeAppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.config.ThreadLocalWebUiDriver;

import java.time.Duration;
import java.util.List;

public class GenericFunctions {
    ThreadLocalWebUiDriver localWebUiDriver = new ThreadLocalWebUiDriver();
    InitializeAppiumDriver initializeAppiumDriver = new InitializeAppiumDriver();

    WebDriver driver = localWebUiDriver.getDriver()==null?initializeAppiumDriver.getMobileDriver(): localWebUiDriver.getDriver();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public void FindElementInIframeAndClick(String xpath){
        List<WebElement> iframeElement = driver.findElements(By.tagName("iframe"));
        for(WebElement ele : iframeElement){
            driver.switchTo().frame(ele);
            List<WebElement> elementList = driver.findElements(By.xpath(xpath));
            if(elementList.size()>0){
                WebDriverWait(elementList.get(0));
                elementList.get(0).click();
                break;
            }
            driver.switchTo().defaultContent();
        }
        driver.switchTo().defaultContent();
    }

    public String FindElementInIframeAndGetText(String xpath){
        List<WebElement> iframeElement = driver.findElements(By.tagName("iframe"));
        List<WebElement> elementList = driver.findElements(By.xpath(xpath));
        String text="";
        if(elementList.size()>0){
            WebDriverWait(elementList.get(0));
            text = elementList.get(0).getText();
        }else {
        for(WebElement ele : iframeElement){
            System.out.println(ele.getText());
            driver.switchTo().frame(ele);
            elementList = driver.findElements(By.xpath(xpath));
            if(elementList.size()>0){
                WebDriverWait(elementList.get(0));
                text = elementList.get(0).getText();
                break;
            }
            driver.switchTo().defaultContent();
        }
            driver.switchTo().defaultContent();
        }
        return text;
    }

    public void launchURL(String url){
        driver.get(url);
    }

    public void WebDriverWait(WebElement e){
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void WebDriverStaticWait(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<WebElement> FindElements(String xpath){
        List<WebElement> iframeElement = driver.findElements(By.tagName("iframe"));
        List<WebElement> elementList = driver.findElements(By.xpath(xpath));
        List<WebElement> elements = null;
        if(elementList.size()>0){
            WebDriverWait(elementList.get(0));
            elements =elementList;
        }else {
            for(WebElement ele : iframeElement){
                System.out.println(ele.getText());
                driver.switchTo().frame(ele);
                elementList = driver.findElements(By.xpath(xpath));
                if(elementList.size()>0){
                    WebDriverWait(elementList.get(0));
                    elements= elementList;
                    break;
                }
                driver.switchTo().defaultContent();
            }
            driver.switchTo().defaultContent();
        }
        return elements;
    }

}
