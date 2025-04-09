package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    // ThreadLocal allows safe parallel test execution, one driver per thread
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }

    private static void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver(getChromeOptions());
        chromeDriver.manage().window().maximize();
        driver.set(chromeDriver);
        System.out.println("Initialized WebDriver instance: " + chromeDriver);  //  debug log
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // Add more Chrome options if needed
        return options;
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            System.out.println("Quitting WebDriver instance: " + driver.get());  //  debug log
            driver.get().quit();
            driver.remove(); // Clean up for this thread
        }
    }
}
