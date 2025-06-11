package com.magento.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = {
	        "com.magento.automation.stepdefinitions",
	        "com.magento.automation.hooks"
	    },
	    plugin = {
	        "pretty",
	        "html:target/cucumber-reports/Automation-Testcase-report.html"
	    },
	    monochrome = true
	    //tags = "@signup_weak_password"
	)
	public class TestRunner extends AbstractTestNGCucumberTests {
	   
	}

