package com.unitedcoder.regression.uitest.cucumberframework;

import com.unitedcoder.cubecartautomation.TestBase;
import com.unitedcoder.regression.uitest.pageobjectmodule.DashboardPage;
import com.unitedcoder.regression.uitest.pageobjectmodule.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps extends TestBase {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    @Given("user on login page")
    public void userOnLoginPage() {
        loginPage=new LoginPage(driver);
    }
    @When("user enter username and password click on login button")
    public void userEnterUsernameAndPasswordClickOnLoginButton() {
        loginPage.login();
    }
    @Then("user successfully login to the system")
    public void userSuccessfullyLoginToTheSystem() {
        dashboardPage=new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.verifyLogin());
    }
}