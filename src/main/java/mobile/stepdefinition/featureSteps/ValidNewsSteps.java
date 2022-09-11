package mobile.stepdefinition.featureSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import logger.Log;
import mobile.config.InitializeAppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.*;
import ui.pageObjects.GuardianNewsObjects;

import java.util.ArrayList;

public class ValidNewsSteps {
    InitializeAppiumDriver driver = new InitializeAppiumDriver();
    GuardianNewsObjects newsObjects = new GuardianNewsObjects(driver.getMobileDriver());
    String FirstArticleText;

    @Given("user visits {string} on mobile")
    public void user_visits_on_mobile(String url){
        Log.info("Launching URL : "+url);
        newsObjects.launchURL(url);
    }

    @Then("user reads 1st article present in the page on mobile browser")
    public void user_reads_article_on_mobile(){
        newsObjects.acceptCookies();
        FirstArticleText = newsObjects.readFirstArticle();
        Log.info("FirstArticleText is : "+FirstArticleText);
    }

    @And("validates the same article in other sources in {string} on mobile browser")
    public void validate_article_other_sources_on_mobile(String url){
        newsObjects.launchURL(url);
        newsObjects.GoogleSearchTextBox.sendKeys(FirstArticleText);
        newsObjects.GoogleSearchTextBox.sendKeys(Keys.ENTER);
        newsObjects.WebDriverWait(driver.getMobileDriver().switchTo().activeElement());
        newsObjects.WebDriverStaticWait();
        String[] list = driver.getMobileDriver().switchTo().activeElement().getText().split("\n");
        ArrayList<String> arrayList = new ArrayList<>();
        for (String s : list) {
            if (s.contains("www")) {
                arrayList.add(s);
            }
        }
        arrayList.forEach(System.out::println);
        Assert.assertTrue("Matching Resources not found, News is not valid",arrayList.size()>0);
    }
}
