package listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverManager;
import utils.ExtentReportManager;
import utils.Log;
import utils.ScreenshotUtils;

public class ExtentTestNGListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        // Create a test in the Extent Report
        ExtentReportManager.createTest(result.getMethod().getMethodName());
        Log.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log test success
        Log.info("Test passed: " + result.getName());

        // Capture screenshot on success
        String screenshotPath = ScreenshotUtils.captureScreenshot(DriverManager.getDriver(), result.getName() + "_success");

        // Attach screenshot to Extent Report
        if (screenshotPath != null) {
            ExtentReportManager.getTest().pass(
                    "Test Passed",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()
            );
        } else {
            ExtentReportManager.getTest().pass("Test Passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log test failure
        Log.error("Test failed: " + result.getName());

        // Capture screenshot on failure
        String screenshotPath = ScreenshotUtils.captureScreenshot(DriverManager.getDriver(), result.getName() + "_failure");

        // Attach screenshot to Extent Report
        if (screenshotPath != null) {
            ExtentReportManager.getTest().fail(
                    "Test Failed",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()
            );
        } else {
            ExtentReportManager.getTest().fail("Test Failed");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log test skipped
        Log.warn("Test skipped: " + result.getName());
        ExtentReportManager.getTest().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the Extent Report
        ExtentReportManager.flush();
    }
}