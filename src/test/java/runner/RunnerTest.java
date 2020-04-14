package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import com.cucumber.listener.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.Log;

@Test
@CucumberOptions(
        features = { "./src/test/java/features/" },
        glue = "stepDefs",
        tags = "~@wip",
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
)
public class RunnerTest extends AbstractTestNGCucumberTests {

    @BeforeClass
    public static void setUp(){
        Log.startLog("Test is starting ...");
    }

    @AfterClass
    public static void writeExtentReport() {
        Log.endLog("Test is ending ...");
        //Reporter.loadXMLConfig("extent-config.xml");
    }

}

