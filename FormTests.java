import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FormTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testPracticeFormSubmission() {
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement subjectsInput = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("subjectsInput"))
        );
        WebElement submitButton = driver.findElement(By.id("submit"));

        new Actions(driver)
                .scrollToElement(subjectsInput)
                .perform();

        driver.findElement(By.id("firstName")).sendKeys("Johm");
        driver.findElement(By.id("lastName")).sendKeys("Doe");
        driver.findElement(By.id("userEmail")).sendKeys("test@mail.com");
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        driver.findElement(By.id("userNumber")).sendKeys("1234567890");

        subjectsInput.sendKeys("Math");
        subjectsInput.sendKeys(Keys.TAB);

        subjectsInput.sendKeys("Arts");
        subjectsInput.sendKeys(Keys.TAB);

        new Actions(driver)
                .scrollToElement(submitButton)
                .perform();

        driver.findElement(By.xpath("//label[text()='Music']")).click();
        driver.findElement(By.id("currentAddress")).sendKeys("Test Address");

        WebElement stateInput = driver.findElement(By.id("react-select-3-input"));
        stateInput.sendKeys("NCR", Keys.TAB);

        WebElement cityInput = driver.findElement(By.id("react-select-4-input"));
        cityInput.sendKeys("Delhi", Keys.TAB);

        new Actions(driver)
                .scrollByAmount(0, submitButton.getSize().getHeight())
                .perform();

        submitButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));

        assertCellText("Student Name", "Johm Doe");
        assertCellText("Student Email", "test@mail.com");
        assertCellText("Gender", "Male");
        assertCellText("Mobile", "1234567890");

        WebElement subjectsCell = getResultCell("Subjects");
        Assert.assertTrue(subjectsCell.getText().contains("Math"));
        Assert.assertTrue(subjectsCell.getText().contains("Arts"));

        assertCellText("Hobbies", "Music");
        assertCellText("Address", "Test Address");
        assertCellText("State and City", "NCR Delhi");

        System.out.println("All assertions passed successfully");
    }

    private WebElement getResultCell(String rowName) {
        String xpath = String.format("//td[text()='%s']//following-sibling::td", rowName);
        return driver.findElement(By.xpath(xpath));
    }

    private void assertCellText(String rowName, String expectedText) {
        WebElement cell = getResultCell(rowName);
        Assert.assertEquals(cell.getText(), expectedText);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
