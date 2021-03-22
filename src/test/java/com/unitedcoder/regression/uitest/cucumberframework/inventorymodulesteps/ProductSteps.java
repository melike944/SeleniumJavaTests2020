package com.unitedcoder.regression.uitest.cucumberframework.inventorymodulesteps;

import com.unitedcoder.cubecartautomation.TestBase;
import com.unitedcoder.regression.uitest.pageobjectmodule.DashboardPage;
import com.unitedcoder.regression.uitest.pageobjectmodule.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;



public class ProductSteps extends TestBase {

    DashboardPage dashboardPage;
    ProductsPage productsPage;

    @Given("User already on the dashboard page")
    public void userAlreadyOnTheDashboardPage() {
        dashboardPage=new DashboardPage(driver);
        productsPage=new ProductsPage(driver);
    }

    @When("User Click on Products link")
    public void userClickOnProductsLink() {
        dashboardPage.clickOnProductssLink();
    }

    @And("user click on add product link to fill out product information")
    public void userClickOnAddProductLinkToFillOutProductInformation() {

        productsPage.addProducts("melike"+System.currentTimeMillis(),
                "100"+System.currentTimeMillis());
    }

    @Then("Product should be added")
    public void productShouldBeAdded() {
        Assert.assertTrue(productsPage.verifyNewProductAdded());
    }

    @And("user click on the delete icon")
    public void userClickOnTheDeleteIcon() {
        productsPage.deleteProduct();
    }

    @Then("Product should be deleted successfully")
    public void productShouldBeDeletedSuccessfully() {
        Assert.assertTrue(productsPage.verifyDeleteProductSuccessfully());
    }
}
