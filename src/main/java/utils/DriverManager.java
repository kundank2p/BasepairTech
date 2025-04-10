package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

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
        System.out.println("Initialized WebDriver instance: " + chromeDriver); 
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            System.out.println("Quitting WebDriver instance: " + driver.get()); 
            driver.get().quit();
            driver.remove(); 
        }
    }
}
