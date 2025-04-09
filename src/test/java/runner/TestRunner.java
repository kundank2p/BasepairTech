package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        tags = "@positive or @negative or @upload or @multipleUpload",
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}

