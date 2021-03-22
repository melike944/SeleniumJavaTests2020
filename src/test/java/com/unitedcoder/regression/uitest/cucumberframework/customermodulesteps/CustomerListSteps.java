package com.unitedcoder.regression.uitest.cucumberframework.customermodulesteps;

import com.unitedcoder.cubecartautomation.TestBase;
import com.unitedcoder.regression.uitest.pageobjectmodule.CustomersPage;
import com.unitedcoder.regression.uitest.pageobjectmodule.DashboardPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CustomerListSteps extends TestBase {
    DashboardPage dashboardPage;
    CustomersPage customersPage;
    //Add customer
    @Given("user already on the dashboard page")
    public void userAlreadyOnTheDashboardPage() {
        dashboardPage=new DashboardPage(driver);
        customersPage=new CustomersPage(driver);
    }

    @When("user click on Customer List link")
    public void userClickOnCustomerListLink() {
        dashboardPage.clickOncustomersLink();
    }

    @And("user click on add customer link to fill out all customer information")
    public void userClickOnAddCustomerLinkToFillOutAllCustomerInformation() {
        customersPage.addCustomer("melike","abdukhar",
                "099"+System.currentTimeMillis()+"@gmail.com");
    }

    @Then("new Customer should be added")
    public void newCustomerShouldBeAdded() {
        Assert.assertTrue(customersPage.customerAddedSuccessfully());
    }

    @And("user click on delete icon")
    public void userClickOnDeleteIcon() {
        customersPage.deleteCustomer();
    }

    @Then("user should be deleted successfully")
    public void userShouldBeDeletedSuccessfully() {
        Assert.assertTrue(customersPage.verifyDeletedSuccessfully());
    }
}
