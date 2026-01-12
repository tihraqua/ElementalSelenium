import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SelectElement {
    WebDriver driver;
    org.openqa.selenium.support.ui.Select select;

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
    public void selectOption(){
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement ddl = driver.findElement(By.id("dropdown"));
        select = new Select(ddl);
        select.selectByIndex(1);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 1");

    }
}
