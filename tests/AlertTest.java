package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertsPage;
import base.BaseTest;

@Epic("Alerts Demo")
@Feature("Prompt Alert")
public class AlertTest extends BaseTest {

    @Test
    @Story("User enters text into prompt alert")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that prompt alert accepts text input and displays correct result")
    public void alertElementsTest() {

        String name = "David Barsegyan";

        AlertsPage alertsPage = new AlertsPage(driver);

        alertsPage
                .navigate()
                .openTextboxSection()
                .handlePrompt(name);

        String actualResult = alertsPage.readResultMessage();

        Assert.assertEquals(
                actualResult,
                "Hello " + name + " How are you today"
        );
    }
}
