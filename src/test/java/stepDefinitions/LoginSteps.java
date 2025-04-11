package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import org.testng.Assert;
import utils.DriverManager;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Given("the user is on the login page")
    public void userOnLoginPage() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get("https://test.basepairtech.com/login?next=/");

        loginPage = new LoginPage(driver);
    }

    @When("the user enters a valid username and password")
    public void userEntersCredentials() {
        loginPage.enterUsername("test+ic01@basepairtech.com");
        loginPage.enterPassword("HNdK*@VV$ahec8");
    }

    @And("clicks the login button")
    public void userClicksLogin() {
        loginPage.clickLogin();
    }

    @Then("the user should be navigated to the dashboard")
    public void verifyLoginSuccess() {
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed");
    }
}
