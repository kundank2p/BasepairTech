package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.Objects;

public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isDashboardVisible() {
        try {
            By dashboardHeader = By.xpath("//h1[contains(text(),\"Interview's Project Dashboard\")]");
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
            System.out.println("Dashboard header found: " + header.getText());
            return true;
        } catch (TimeoutException e) {
            System.out.println("Dashboard header not found within timeout.");
            return false;
        }
    }

    public void clickOnProjectTitle(String projectName) {
        By projectTitleElement = By.xpath("//h1[text()='" + projectName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(projectTitleElement)).click();
    }

    public boolean isOnProjectPage() {
        try {
            wait.until(ExpectedConditions.urlContains("/project"));
            return Objects.requireNonNull(driver.getCurrentUrl()).contains("/project");
        } catch (TimeoutException e) {
            return false;
        }
    }
}
