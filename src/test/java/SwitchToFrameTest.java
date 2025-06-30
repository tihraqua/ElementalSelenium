import io.qameta.allure.Description;
import jdk.jfr.Enabled;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SwitchToFrameTest {
    WebDriver webDriver;
    By editorBox = By.id("tinymce");

    @BeforeMethod
    public  void setup(){

         webDriver = new ChromeDriver();

    }

    @AfterMethod
    public  void tearDown(){
        if(webDriver != null)
            webDriver.quit();

    }

    @Test()
    @Description("This test is to validate switching between frames")
    public void switchToFrame(){
        webDriver.get("https://the-internet.herokuapp.com/nested_frames");
        webDriver.switchTo().frame("frame-top");
        webDriver.switchTo().frame("frame-middle");
        String middleText = webDriver.findElement(By.id("content")).getText();
        Assert.assertEquals(middleText,"MIDDLE", "The switching to frame is failed");
        }

    @Test(enabled = false)
    @Description("This test is to validate switching between frames")
    public void switchToFrameExample2(){
        webDriver.get("https://the-internet.herokuapp.com/tinymce");
        webDriver.switchTo().frame("mce_0_ifr");
        WebElement editor = webDriver.findElement(editorBox);
        String textBefore = editor.getText();
       editor.clear();
        editor.sendKeys("Hello World!!!");
            String textAfter = editor.getText();
        Assert.assertNotEquals(textAfter,textBefore);
        webDriver.switchTo().defaultContent();
        Assert.assertEquals("An iFrame containing the TinyMCE WYSIWYG Editor",webDriver.findElement(By.cssSelector("h3")).getText());


    }






    }

