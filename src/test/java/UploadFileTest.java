import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class UploadFileTest {
    By fileUploadBtn = By.id("file-upload");
    By fileSubmitBtn = By.id("file-submit");
    By uploadedFilesBox = By.id("uploaded-files");
    WebDriver webDriver;

    @BeforeTest
    public  void setup(){
         webDriver = new ChromeDriver();

    }

    @AfterTest
    public  void tearDown(){
        if(webDriver != null)
            webDriver.quit();
    }

    @Test()
    @Description("This test is to validate the upload function")
    public void uploadFile(){
        String filePath = "C:\\Users\\t.alhusin\\IdeaProjects\\ElementalSelenium\\src\\main\\resources\\testFile.txt";
        String fileName = "testFile.txt";
        webDriver.get("https://the-internet.herokuapp.com/upload");
        webDriver.findElement(fileUploadBtn).sendKeys(filePath);
        webDriver.findElement(fileSubmitBtn).click();
        String uploadedFileName = webDriver.findElement(uploadedFilesBox).getText();
        Assert.assertEquals(uploadedFileName,fileName, "The file is not uploaded successfully");



    }
}
