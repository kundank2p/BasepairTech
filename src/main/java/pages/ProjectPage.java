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

    public void clickUploadIcon() {
        By icon = By.xpath("//i[@class='anticon anticon-inbox']");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(icon));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void uploadFile(String fileName) {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));
        input.sendKeys(getAbsolutePath(fileName));
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
    }

    public void fillMetadata(Map<String, String> metadata) {

        if (metadata.containsKey("Platform")) {
            selectDropdown("Platform", metadata.get("Platform"));
        }
        if (metadata.containsKey("Data type")) {
            selectDropdown("Data type", metadata.get("Data type"));
        }
        if (metadata.containsKey("Stranded") && !metadata.get("Stranded").isBlank()) {
            selectDropdown("Stranded", metadata.get("Stranded"));
        }
        if (metadata.containsKey("Spike in") && !metadata.get("Spike in").isBlank()) {
            selectDropdown("Spike in", metadata.get("Spike in"));
        }
        if (metadata.containsKey("Genome")) {
            selectDropdown("Genome", metadata.get("Genome"));
        }
        if (metadata.containsKey("Pipeline") && !metadata.get("Pipeline").isBlank()) {
            selectDropdown("Pipeline", metadata.get("Pipeline"));
        }
    }

    public void selectDropdown(String labelText, String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Click the dropdown box next to the label
            WebElement dropdownTrigger = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[contains(text(),'" + labelText + "')]/following-sibling::div//div[contains(@class,'ant-select-selector')]")
            ));
            dropdownTrigger.click();

            // Wait for the option panel and select value
            WebElement dropdownOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class,'ant-select-dropdown')]//div[@role='option' and normalize-space(text())='" + valueToSelect + "']")
            ));
            dropdownOption.click();

        } catch (TimeoutException e) {
            System.out.println("Dropdown '" + labelText + "' or option '" + valueToSelect + "' not found.");
            throw e;
        }
    }


    public void clickButton(String xpath) {
        By button = By.xpath("//button[@class='ant-btn ant-btn-primary']");
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
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
