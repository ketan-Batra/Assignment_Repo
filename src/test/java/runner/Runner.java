package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/features",
        glue = {"mobile","ui","common"},
        stepNotifications = true,
        tags = "@mobileNews11",
        publish = true,
        plugin = {"pretty", "html:target/cucumber-reports/report.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class Runner {
}
