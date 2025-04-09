package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.DashboardPage;
import utils.DriverManager;

public class NavigateToProjectSteps {

    WebDriver driver;
    DashboardPage dashboardPage;

    @Given("the user is logged in and on the dashboard page")
    public void userOnDashboard() {
        driver = DriverManager.getDriver();
        dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isDashboardVisible(), "Dashboard is not visible after login");
    }

    @When("the user clicks on the project titled {string}")
    public void userClicksOnProject(String projectTitle) {
        dashboardPage.clickOnProjectTitle(projectTitle);
    }

    @Then("the user should be navigated to the project page")
    public void userIsOnProjectPage() {
        Assert.assertTrue(dashboardPage.isOnProjectPage(), "Not navigated to project page");
    }
}
