package com.unitedcoder.regression.uitest.cucumberframework;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(//customize cucumber test
        plugin = {"pretty","html:target/cubecart-cucumber",
                "json:target/cubecart.json"},
        features = {"src/test/resources"},
        tags = "@AddCustomerTest",
        //@regressionTest,@weatherapitest,@DatabaseTest
        monochrome = true,
        glue={"com/unitedcoder/regression/uitest/cucumberframework"},
        dryRun = false
)


public class CucumberTestRunner {

}
