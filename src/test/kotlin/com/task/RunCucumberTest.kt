package com.task

import io.cucumber.core.options.Constants.*
import io.cucumber.java.BeforeAll
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")

@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.task")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "classpath:features")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@Challenge")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:target/cucumber.json")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "junit:target/reports/cucumber-results.xml")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm")

class RunCucumberTest {
}

object Hooks {
    @BeforeAll
    @JvmStatic
    fun before() {
        init()
    }

    private fun init() {
        //do smth before all test even start
    }
}