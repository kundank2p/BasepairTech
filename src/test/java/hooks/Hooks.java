package hooks;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverManager;

public class Hooks {
    @BeforeClass
    public void setup() {

        DriverManager.getDriver(); // Initialize driver
    }

//    @AfterClass
//    public void tearDown() {
//
//        DriverManager.quitDriver(); // Close driver
//    }
}