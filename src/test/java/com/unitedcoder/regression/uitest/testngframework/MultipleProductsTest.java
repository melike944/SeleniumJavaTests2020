package com.unitedcoder.regression.uitest.testngframework;

import com.unitedcoder.cubecartautomation.TestBase;
import com.unitedcoder.regression.uitest.pageobjectmodel.DashboardPage;
import com.unitedcoder.regression.uitest.pageobjectmodel.LoginPage;
import com.unitedcoder.regression.uitest.pageobjectmodel.ProductsPage;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MultipleProductsTest extends TestBase {
    @BeforeClass
    public void setup(){
        initialzation();
        LoginPage loginPage=new LoginPage(driver);
        loginPage.login();
    }
    @Test(dataProvider = "productInfo")
    public void addMultipleProducts(String productName,String productCode){
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.clickOnProductssLink();
        ProductsPage productsPage=new ProductsPage(driver);
        productsPage.addProducts(productName,productCode);
        Assert.assertTrue(productsPage.verifyNewProductAdded());
    }
    @AfterClass
    public  void tearDown(){
        driver.close();
        driver.quit();
    }
    @DataProvider
    public Object[][] productInfo(){
        Object[][] productData=new Object[][]{
                {"Apple","Tz89"},
                {"IMac","Ab786"},
                {"Shoes","Cd987"}
        };
        return productData;
    }


}
