import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CommandsTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCommands() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        By enableBtn = By.xpath("//form[@id='input-example']/button");
        By inputField = By.xpath("//form[@id='input-example']//input");
        By message = By.id("message");

        driver.findElement(enableBtn).click();

        wait.until(ExpectedConditions.elementToBeClickable(inputField));
        wait.until(ExpectedConditions.textToBe(message, "It's enabled!"));

        WebElement input = driver.findElement(inputField);
        if (input.isEnabled() && driver.findElement(message).isDisplayed()) {
            System.out.println("Input field enabled");
        }

        wait.until(ExpectedConditions.textToBe(enableBtn, "Disable"));
        String btnText = driver.findElement(enableBtn).getText();
        if (btnText.equals("Disable")) {
            System.out.println("Button text changed");
        }

        input.sendKeys("Bootcamp");
        input.clear();

        driver.get("http://the-internet.herokuapp.com/drag_and_drop");
        WebElement colA = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-a")));
        WebElement colB = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-b")));

        int YA = colA.getRect().y;
        int YB = colB.getRect().y;

        if (YA == YB) {
            System.out.println("Columns A and B aligned successfully");
        } else {
            System.out.println("Columns A and B are NOT aligned");
        }
    }
}
