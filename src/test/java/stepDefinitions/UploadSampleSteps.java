package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ProjectPage;
import utils.DriverManager;

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

    @When("the user clicks the Upload samples button")
    public void userClicksButtonSubmit() {
        projectPage.clickUploadButton();
    }

    @Then("the file should be uploaded successfully")
    public void verifySingleFileUploadSuccess() {
        Assert.assertTrue(projectPage.isUploadSuccessful(), "File was not uploaded successfully");
    }

    @When("the user selects files named {string} from testdata")
    public void userSelectsMultipleFiles(String files) {
        String[] fileList = files.split(",");
        projectPage.uploadMultipleFiles(fileList);
    }

    @Then("the files should be uploaded successfully")
    public void verifyMultipleFilesUpload() {
        Assert.assertTrue(projectPage.isUploadSuccessful(), "Files were not uploaded successfully");
    }

    @Then("a validation error should be displayed")
    public void verifyValidationError() {
        Assert.assertTrue(projectPage.isValidationErrorVisible(), "Validation error was not displayed");
    }

    @Then("an error message should be displayed for unsupported format")
    public void verifyUnsupportedFileError() {
        Assert.assertTrue(projectPage.isUnsupportedFileTypeErrorVisible(), "Unsupported file type error was not displayed");
    }

}
