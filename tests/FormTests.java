package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PracticeFormPage;

@Epic("DemoQA")
@Feature("Practice Form")
public class FormTest extends BaseTest {

    @Test
    @Story("User submits practice form")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that practice form can be submitted and data is displayed correctly")
    public void practiceFormSubmissionTest() {

        PracticeFormPage formPage = new PracticeFormPage(driver);

        formPage
                .navigate()
                .populateForm(
                        "Johm",
                        "Doe",
                        "test@mail.com",
                        "1234567890"
                )
                .confirmSubmission();

        String modalTitle = formPage.fetchModalTitle();
        String studentName = formPage.fetchStudentName();

        Assert.assertEquals(modalTitle, "Thanks for submitting the form");
        Assert.assertEquals(studentName, "Johm Doe");
    }
}
