package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AlertsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By textboxSection =
            By.xpath("//a[contains(text(),'Alert with Textbox')]");
    private final By openPromptBtn =
            By.xpath("//button[contains(text(),'click the button to demonstrate the prompt box')]");
    private final By outputMessage =
            By.id("demo1");

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Navigate to alerts demo page")
    public AlertsPage navigate() {
        driver.get("https://demo.automationtesting.in/Alerts.html");
        return this;
    }

    @Step("Switch to textbox alert section")
    public AlertsPage openTextboxSection() {
        closeUnexpectedAlertIfExists();
        wait.until(ExpectedConditions.elementToBeClickable(textboxSection)).click();
        return this;
    }

    @Step("Handle prompt alert with input: {text}")
    public AlertsPage handlePrompt(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(openPromptBtn)).click();

        Alert promptAlert = wait.until(ExpectedConditions.alertIsPresent());
        promptAlert.sendKeys(text);
        promptAlert.accept();

        return this;
    }

    @Step("Read result text from page")
    public String readResultMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(outputMessage))
                   .getText();
    }

    private void closeUnexpectedAlertIfExists() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException ignored) {
        }
    }
}
