import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.applicationcache.model.ApplicationCacheStatusUpdated;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WindowHandle {

    WebDriver driver;
    WebDriverWait driverWait;


    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();

    }

    @Test
    public void multipleWindows() {
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.findElement(By.cssSelector(".example a")).click();
        driverWait.until(driver -> !driver.getTitle().isEmpty());
        List<String> windowsHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowsHandles.get(0));
        Assert.assertEquals("The Internet", driver.getTitle());
        driver.switchTo().window(windowsHandles.get(1));
        Assert.assertEquals("New Window", driver.getTitle());
    }

    @Test
    public void multipleWindowsSecond() {
        driver.get("https://the-internet.herokuapp.com/windows");
        String firstWindow = driver.getWindowHandle();
        driver.findElement(By.cssSelector(".example a")).click();
        String newWindow = "";
        List<String> windowsHandlers = new ArrayList<>(driver.getWindowHandles());

        for (String window : windowsHandlers) {
            if (!window.equals(firstWindow)) {
                newWindow = window;
            }
        }
        driver.switchTo().window(firstWindow);
        driverWait.until(driver -> !driver.getTitle().isEmpty());
        Assert.assertEquals("The Internet", driver.getTitle());
        driver.switchTo().window(newWindow);
        Assert.assertEquals("New Window", driver.getTitle());

    }
}

