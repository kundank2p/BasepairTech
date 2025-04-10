package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.ProjectPage;
import utils.DriverManager;

import java.time.Duration;

public class UploadSampleSteps {

    WebDriver driver;
    ProjectPage projectPage;

    @Given("the user is logged in and on the project page")
    public void userIsOnProjectPage() {
        driver = DriverManager.getDriver();
        projectPage = new ProjectPage(driver);
        Assert.assertTrue(projectPage.isOnProjectPage(), "User is not on the project page");
    }

    @When("the user clicks the {string} section")
    public void userClicksUploadSection(String sectionName) {
        projectPage.clickUploadSampleSection(sectionName);
    }


    @When("the user selects a file named {string} from testdata")
    public void userSelectsFile(String fileName) {
        projectPage.uploadFile(fileName);
    }

    @When("the user selects multiple files from testdata {string}")
    public void userSelectsMultipleFiles(String fileList) {
        String[] files = fileList.split(",");
        projectPage.uploadMultipleFiles(files);
    }

    @When("the user clicks the Upload samples button")
    public void userClicksButtonSubmit() {
        projectPage.clickButton();
    }

    @Then("the file should be uploaded successfully")
    public void verifySingleFileUploadSuccess() {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            By successMsg = By.xpath("//span[@class='ant-alert-message' and contains(text(),'Sample added successfully.')]");
            WebElement successElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));

            Assert.assertTrue(successElement.isDisplayed(), "File was not uploaded successfully");

        } catch (TimeoutException e) {
            Assert.fail("Success message not visible after upload");
        }
    }

    @Then("all selected files should be uploaded successfully")
    public void verifyMultipleFileUploadSuccess() {
        Assert.assertTrue(projectPage.areMultipleFilesUploaded(), "Not all files were uploaded successfully");
    }
}
