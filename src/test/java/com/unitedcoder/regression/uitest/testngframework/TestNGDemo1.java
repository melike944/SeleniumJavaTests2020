package com.unitedcoder.regression.uitest.testngframework;


import org.junit.Assert;
import org.testng.annotations.*;
import static java.lang.Math.*;

public class TestNGDemo1 {

    @BeforeSuite
    public void beforeSuitTest(){
        System.out.println("Before Suit Test will Run First");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("Before Test Will run After Suite");
    }
    @BeforeClass
    public void setup(){
        System.out.println("Before class will run once");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Before method will run before every test method");
    }
    @Test(priority = 2,description = "Admin user shoulde be able to add product",
            groups = {"smoke test,regression test"})
    public void addProductTest(){
        System.out.println("this is for adding a new product");
        Assert.assertTrue("add product".contains("product"));
    }
    @Test(priority = 1,enabled = false,groups = {"Regression test"})
    public void deleteProduct(){
        System.out.println("this test is for deleting product");
        Assert.assertEquals(10,10);
    }
    @Test(priority = 3,groups = {"Regression test"})
    public void viewCustomer(){
        System.out.println("this test is for view customer");
        Assert.assertEquals(max(10,20),20);
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("Before method will run after every test method");
    }
    @AfterClass
    public void tearDown(){
        System.out.println("After Class will run once");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("After test will run after class");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("After Suite will run After all test");
    }
}
