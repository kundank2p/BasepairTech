package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Page Elements
    By usernameField = By.id("Login_username");
    By passwordField = By.id("Login_password");
    By loginButton = By.xpath("//button[@type='button' and .//span[text()='Log in']]");

    // Actions
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isLoginSuccessful() {
        try {
            By dashboardHeader = By.xpath("//h1[contains(text(),\"Interview's Project Dashboard\")]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


}
