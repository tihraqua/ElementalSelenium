import io.qameta.allure.Description;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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
import org.apache.http.client.methods.HttpHead;

import java.io.File;
import java.io.IOException;
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

    @Test
    public void downloadFileTestRevised() throws IOException {
        webDriver.get("https://the-internet.herokuapp.com/download");
        String downloadLink = webDriver.findElement(By.xpath("//div[@class='example']/following::a[1]")).getAttribute("href");

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpHead request = new HttpHead(downloadLink);
        HttpResponse response = httpClient.execute(request);
        String contentType = response.getFirstHeader("Content-Type").getValue();
        int contentLength = Integer.parseInt(response.getFirstHeader(("Content-Length")).getValue());
        Assert.assertEquals(contentType,"text/html; charset=utf-8");
        Assert.assertNotEquals(contentLength,0);

    }
}
