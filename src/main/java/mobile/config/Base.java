package mobile.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class Base {

    public Base(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)),this);
    }
}
