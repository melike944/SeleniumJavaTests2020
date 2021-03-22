package com.unitedcoder.regression.project.project4;

import com.unitedcoder.cubecartautomation.TestBase;
import com.unitedcoder.regression.uitest.pageobjectmodule.DashboardPage;
import com.unitedcoder.regression.uitest.pageobjectmodule.LoginPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestRunner extends TestBase {
    //invoke the class
    static DashboardPage dashboardPage;
    static LoginPage loginPage;
     AddProductsPage addProducts;
    DeleteProductsPage deleteProducts;
    @BeforeClass
    public static void setup() {
        initialzation();
        loginPage = new LoginPage(driver);
        loginPage.login();
    }
        @Test
        public void addProducts() {
            addProducts= new AddProductsPage(driver);
            Assert.assertTrue(addProducts.addProducts());
            Assert.assertTrue(addProducts.verify());
        }
        @Test
        public void deleteProducts() throws InterruptedException {
            deleteProducts = new DeleteProductsPage(driver);
            Assert.assertTrue(deleteProducts.deleteProducts());
            Assert.assertTrue(deleteProducts.verify());
        }
            @AfterClass
            public static void tearDown() throws InterruptedException {
                dashboardPage = new DashboardPage(driver);
                dashboardPage.logout();
                Thread.sleep(3000);
                driver.close();
                driver.quit();
            }

}
