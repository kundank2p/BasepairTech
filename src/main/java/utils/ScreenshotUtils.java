package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/" + screenshotName + ".png";

        try {
            // Capture screenshot
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Save screenshot to the specified path
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));

            Log.info("Screenshot captured: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            Log.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}