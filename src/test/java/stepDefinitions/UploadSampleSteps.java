package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ProjectPage;
import utils.DriverManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @When("the user clicks the upload icon")
    public void userClicksUploadIcon() {
        projectPage.clickUploadIcon();
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

    @When("the user fills in the metadata")
    public void userFillsMetadata(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists();

        for (List<String> row : rows) {
            if (row.size() == 2) {
                String field = row.get(0).trim();
                String value = row.get(1) != null ? row.get(1).trim() : "";

                // Skip if value is marked as [empty]
                if (!value.equalsIgnoreCase("[empty]")) {
                    projectPage.selectDropdown(field, value);
                }
            }
        }


    }

    @When("the user clicks the Upload samples button")
    public void userClicksButtonSubmit(String xpath) {
        projectPage.clickButton(xpath);
    }

    @Then("the file should be uploaded successfully")
    public void verifySingleFileUploadSuccess() {
        Assert.assertTrue(projectPage.isUploadSuccess(), "File was not uploaded successfully");
    }

    @Then("all selected files should be uploaded successfully")
    public void verifyMultipleFileUploadSuccess() {
        Assert.assertTrue(projectPage.areMultipleFilesUploaded(), "Not all files were uploaded successfully");
    }
}
