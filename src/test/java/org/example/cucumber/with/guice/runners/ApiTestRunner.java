package org.example.cucumber.with.guice.runners;

import io.cucumber.junit.CucumberOptions;
import org.example.cucumber.with.guice.framework.config.CustomObjectFactory;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("src/test/resources/features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty," +
        "junit:target/metadata-cucumber-results.xml," +
        "json:target/metadata-cucumber-reports.json")
@CucumberOptions(objectFactory= CustomObjectFactory.class)
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "not @Manual and not @Defects")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.example.cucumber.with.guice.framework.steps")
@ConfigurationParameter(key = OBJECT_FACTORY_PROPERTY_NAME, value = "org.example.cucumber.with.guice.framework.config.CustomObjectFactory")
public class ApiTestRunner {

}