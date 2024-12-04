import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v129.network.model.ClientSecurityState;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DownloadFileTest {
    WebDriver webDriver;
    File folder;
    WebDriverWait wait;
    ChromeOptions chromeOptions;

    @BeforeTest
    public  void setup(){
        folder = new File(UUID.randomUUID().toString());
        folder.mkdir();
        String folderPath = folder.getAbsolutePath();
        chromeOptions = new ChromeOptions();
        Map<String,Object> pref = new HashMap<String, Object>();
        pref.put("download.default_directory", folderPath);
        chromeOptions.setExperimentalOption("prefs", pref);
         webDriver = new ChromeDriver(chromeOptions);

    }

    @AfterTest
    public  void tearDown(){
        if(webDriver != null)
            webDriver.quit();
        for (File file: folder.listFiles()){
            file.delete();
        }
        folder.delete();
    }

    @Test()
    @Description("This test is to validate the download of a file")
    public void downloadFile(){
        webDriver.get("https://the-internet.herokuapp.com/download");
        webDriver.findElement(By.cssSelector(".example a")).click();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(50));
        webDriverWait.until((webDriver1 -> folder.listFiles().length>0 && folder.listFiles()[0].length()>0));
        File[] files = folder.listFiles();

        Assert.assertTrue(files.length > 0);
        for(File file : files){
            Assert.assertTrue(files.length> 0);
        }






    }
}
