package tutorial;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("tutorial") // Points to your feature files location
@ConfigurationParameter(key = "cucumber.glue", value = "tutorial") // Package where your step definitions are located
@ConfigurationParameter(key = "cucumber.plugin", value = "html:build/reports/cucumber.html")
public class RunCucumberTests {
}
