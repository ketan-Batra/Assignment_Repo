package ui.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.stepdefinition.common.GenericFunctions;

public class Base extends GenericFunctions {

    public Base(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
