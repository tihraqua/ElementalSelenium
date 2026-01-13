import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowsTabs {
    WebDriver driver;


    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();

    }
    @Test
    public void openWindow(){
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.switchTo().newWindow(WindowType.WINDOW);
        Assert.assertEquals(driver.getWindowHandles().toArray().length,2);
    }
    @Test
    public void openTab(){
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.switchTo().newWindow(WindowType.TAB);
        Assert.assertEquals(2,driver.getWindowHandles().toArray().length);
    }
}
