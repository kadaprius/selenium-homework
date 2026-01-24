package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PracticeFormPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameInput = By.xpath("//input[@id='firstName']");
    private final By lastNameInput = By.xpath("//input[@id='lastName']");
    private final By emailInput = By.xpath("//input[@id='userEmail']");
    private final By genderOption = By.xpath("//label[contains(@for,'gender-radio-1')]");
    private final By phoneInput = By.xpath("//input[@id='userNumber']");
    private final By submitBtn = By.xpath("//button[@id='submit']");
    private final By modalHeader = By.xpath("//div[@id='example-modal-sizes-title-lg']");
    private final By studentNameValue =
            By.xpath("//td[text()='Student Name']/following-sibling::td");

    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Navigate to practice form")
    public PracticeFormPage navigate() {
        driver.get("https://demoqa.com/automation-practice-form");
        return this;
    }

    @Step("Populate form fields")
    public PracticeFormPage populateForm(String firstName, String lastName, String email, String phone) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameInput)).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(emailInput).sendKeys(email);

        WebElement gender = driver.findElement(genderOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", gender);

        driver.findElement(phoneInput).sendKeys(phone);
        return this;
    }

    @Step("Confirm form submission")
    public PracticeFormPage confirmSubmission() {
        WebElement submit = driver.findElement(submitBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
        return this;
    }

    @Step("Fetch modal title")
    public String fetchModalTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(modalHeader)).getText();
    }

    @Step("Fetch submitted student name")
    public String fetchStudentName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(studentNameValue)).getText();
    }
}
