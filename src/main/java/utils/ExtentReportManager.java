package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            sparkReporter.config().setDocumentTitle("Test Automation Report");
            sparkReporter.config().setReportName("TestNG Extent Report");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void createTest(String testName) {
        test.set(getInstance().createTest(testName));
    }

    public static void flush() {
        getInstance().flush();
    }
}