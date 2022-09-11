package common;

import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Report {
    Scenario scenario;
    Hooks hooks = new Hooks();

    @BeforeAll
    public static void deleteScreenshotDirectory(){
        try {
            FileUtils.cleanDirectory(new File(System.getProperty("user.dir")+"\\target\\screenshots\\"));
            FileUtils.delete(new File(System.getProperty("user.dir")+"\\LogFile"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterStep
    public void gather_Screenshot(Scenario scenario){
        this.scenario=scenario;
        String bytes = ScreenshotByteArray();
        scenario.attach(bytes,"image/png", "Step");
    }

    public void takeScreenshot(Scenario scenario){
        File File;
        if(System.getProperty("browser")!=null){
         File = ((TakesScreenshot) hooks.getWebDriver()).getScreenshotAs(OutputType.FILE);
        }else {
            File = ((TakesScreenshot) hooks.getAppiumDriver()).getScreenshotAs(OutputType.FILE);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy_hhmmss");
        String CurrentDate = simpleDateFormat.format(date.getTime());
        try {
            FileUtils.copyFile(File,new File(System.getProperty("user.dir")+"\\target\\screenshots\\"+scenario.getName()+"\\"+CurrentDate+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ScreenshotByteArray(){
        File File;
        if(System.getProperty("browser")!=null){
            File = ((TakesScreenshot) hooks.getWebDriver()).getScreenshotAs(OutputType.FILE);
        }else {
            File = ((TakesScreenshot) hooks.getAppiumDriver()).getScreenshotAs(OutputType.FILE);
        }
        byte[] byteArr;
        String encodedString = null;
        try {
            byteArr = FileUtils.readFileToByteArray(File);
            encodedString = Base64.getEncoder().encodeToString(byteArr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

}
