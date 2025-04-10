package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.time.Duration;
import java.util.Map;

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

//    public void clickUploadIcon() {
//        By icon = By.xpath("//i[@class='anticon anticon-inbox']");
//        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(icon));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
//    }

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


    public void uploadMultipleFiles(String[] fileNames) {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));
        StringBuilder allPaths = new StringBuilder();
        for (int i = 0; i < fileNames.length; i++) {
            allPaths.append(getAbsolutePath(fileNames[i].trim()));
            if (i < fileNames.length - 1) {
                allPaths.append("\n");
            }
        }
        input.sendKeys(allPaths.toString());

        try {
            By previewLocator = By.xpath("//div[contains(@class, 'ant-upload-list')]//div[contains(@class,'ant-upload-list-item')]");
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(previewLocator, 1));
        } catch (TimeoutException e) {
            System.out.println("Multiple file preview not visible.");
        }
    }




    public void clickButton() {
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



    public boolean isUploadSuccess() {
        try {
            By toast = By.xpath("//*[contains(text(),'Upload successful') or contains(text(),'successfully uploaded')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(toast));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean areMultipleFilesUploaded() {
        try {
            By toast = By.xpath("//*[contains(text(),'All files uploaded successfully') or contains(text(),'successfully uploaded')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(toast));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private String getAbsolutePath(String fileName) {
        return new File("src/test/resources/testdata/" + fileName).getAbsolutePath();
    }
}
