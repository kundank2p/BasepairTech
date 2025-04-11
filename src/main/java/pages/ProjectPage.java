package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.io.File;
import java.time.Duration;

public class ProjectPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ProjectPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isOnProjectPage() {
        try {
            return wait.until(ExpectedConditions.urlContains("/project"));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickUploadSampleSection(String label) {
        By uploadSampleBtn = By.xpath("//div[@class='sample-floating-upload']");
        wait.until(ExpectedConditions.elementToBeClickable(uploadSampleBtn)).click();
    }
    private String getAbsolutePath(String fileName) {
        return new File("src/test/resources/testdata/" + fileName).getAbsolutePath();
    }
    public void uploadFile(String fileName) {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));
        input.sendKeys(getAbsolutePath(fileName));

        try {
            By previewLocator = By.xpath("//div[contains(@class, 'ant-upload-list')]//div[contains(@class,'ant-upload-list-item')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(previewLocator));
        } catch (TimeoutException e) {
            System.out.println("File preview not visible after upload.");
        }
    }

    public void clickUploadButton() {
        By button = By.xpath("//button[@class='ant-btn ant-btn-primary']");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(button));
        try {
            By previewLocator = By.xpath("//div[contains(@class, 'ant-upload-list')]//div[contains(@class,'ant-upload-list-item')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(previewLocator));
        } catch (TimeoutException e) {
            System.out.println("Upload preview not visible before clicking submit.");
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public boolean isUploadSuccessful() {
        try {
            By successMsg = By.xpath("//*[contains(text(),'Sample added successfully')]");

            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement successElement = longWait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));

            return successElement.isDisplayed();

        } catch (TimeoutException e) {
            System.out.println("Success message not visible after waiting 20 seconds.");
            return false;
        }
    }
    public void uploadMultipleFiles(String[] fileNames) {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));

        StringBuilder filePaths = new StringBuilder();
        for (String file : fileNames) {
            filePaths.append(getAbsolutePath(file.trim())).append("\n"); // newline-separated for multi-upload
        }
        input.sendKeys(filePaths.toString().trim());

        try {
            By previewLocator = By.xpath("//div[contains(@class, 'ant-upload-list')]//div[contains(@class,'ant-upload-list-item')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(previewLocator));
        } catch (TimeoutException e) {
            System.out.println("File preview not visible after multiple file upload.");
        }
    }

    public boolean isValidationErrorVisible() {
        try {
            By errorLocator = By.xpath("//*[contains(text(),'Please select a file') or contains(text(),'required')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isUnsupportedFileTypeErrorVisible() {
        try {
            By errorLocator = By.xpath("//*[contains(text(),'unsupported') or contains(text(),'not allowed')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

}
