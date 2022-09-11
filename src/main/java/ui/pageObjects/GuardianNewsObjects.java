package ui.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.config.Base;

import java.util.ArrayList;
import java.util.List;

public class GuardianNewsObjects extends Base{
    WebDriver driver;
    private final String NewsButtonXpath = "(//ul[@class='pillars'][1]/li)[1]";
    private final String FirstNewsArticleXpath = "(((//div[@class='fc-slice-wrapper']/ul/li)[4]/ul/li)[1]//a)[1]";
    private final String CookiesButtonXpath = "//button[@title='Yes, I’m happy']";
    private final String NewsResourcesListXpath = "//div[@id='search']/div/div/div//a/h3//parent::a";
    private final String GoogleSearchTextBoxXpath = "//input[@name='q']";

    public GuardianNewsObjects(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    @FindBy(xpath = CookiesButtonXpath)
    private List<WebElement> CookiesButton;

    @FindBy(xpath = NewsButtonXpath)
    private WebElement NewsButton;

    @FindBy(xpath = FirstNewsArticleXpath)
    private WebElement FirstNewsArticle;

    @FindBy(xpath = NewsResourcesListXpath)
    private List<WebElement> NewsResourcesList;

    @FindBy(xpath = GoogleSearchTextBoxXpath)
    public WebElement GoogleSearchTextBox;

    public String readFirstArticle(){
        FindElementInIframeAndClick(NewsButtonXpath);
        return FindElementInIframeAndGetText(FirstNewsArticleXpath);
    }

    public void acceptCookies(){
        FindElementInIframeAndClick(CookiesButtonXpath);
    }

    public ArrayList<String> listNewsResources(){
        List<WebElement> resourceElement = FindElements(NewsResourcesListXpath);
        ArrayList<String> list = new ArrayList<>();
        for(int i=1;i<resourceElement.size();i++){
            list.add(resourceElement.get(i).getAttribute("href"));
        }
        return list;
    }

}
