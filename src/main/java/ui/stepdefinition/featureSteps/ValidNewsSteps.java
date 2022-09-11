package ui.stepdefinition.featureSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import ui.config.ThreadLocalWebUiDriver;
import ui.pageObjects.GuardianNewsObjects;

import java.util.ArrayList;

public class ValidNewsSteps {
    ThreadLocalWebUiDriver localWebUiDriver = new ThreadLocalWebUiDriver();
    GuardianNewsObjects newsObjects = new GuardianNewsObjects(localWebUiDriver.getDriver());
    String FirstArticleText;

    @Given("user visits {string}")
    public void user_visits(String url){
        newsObjects.launchURL(url);
    }

    @Then("user reads 1st article present in the page")
    public void user_reads_article(){
        newsObjects.acceptCookies();
        FirstArticleText = newsObjects.readFirstArticle();
    }

    @And("validates the same article in other sources in {string}")
    public void validate_article_other_resources(String url){
        localWebUiDriver.getDriver().switchTo().newWindow(WindowType.TAB);
        newsObjects.launchURL(url);
        newsObjects.GoogleSearchTextBox.sendKeys(FirstArticleText);
        newsObjects.GoogleSearchTextBox.sendKeys(Keys.ENTER);
        ArrayList<String> list = newsObjects.listNewsResources();
        list.forEach(System.out::println);
        Assert.assertTrue("Matching Resources not found, News is not valid",list.size()>0);
    }

}
