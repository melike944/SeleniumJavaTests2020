package com.unitedcoder.regression.uitest.pageobjectmodule;

import com.unitedcoder.cubecartautomation.TestBase;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestRunner extends TestBase {
    static TestUtility utility;
    static LoginPage loginPage;
    static DashboardPage dashboardPage;
    CustomerPage customerPage;
    ProductsPage productsPage;
    CategoryPage categoryPage;
    @BeforeClass
    public static void setup(){
        initialzation();
        loginPage=new LoginPage(driver);
        utility=new TestUtility(driver);
        dashboardPage=new DashboardPage(driver);
        loginPage.login();
    }
    @Test()
    public void addProduct(){
        dashboardPage.clickOnProductssLink();
        productsPage=new ProductsPage(driver);
        Assert.assertTrue(productsPage.addProduct());
    }
    @Test
    public void deleteCategory(){
        categoryPage=new CategoryPage(driver);
        dashboardPage.clickOnCategorylink();
        Assert.assertTrue(categoryPage.deleteCategory());
    }
    @Test
    public void viewCustomers(){
        dashboardPage.clickOncustomersLink();
        customerPage=new CustomerPage(driver);
        Assert.assertTrue(customerPage.viewCustomers());
    }
    @AfterClass
    public static void tearDown(){
        dashboardPage.logout();
        driver.close();
        driver.quit();
    }
}