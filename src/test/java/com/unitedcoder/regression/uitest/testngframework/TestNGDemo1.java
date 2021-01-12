package com.unitedcoder.regression.uitest.testngframework;

import org.junit.Assert;
import org.testng.annotations.*;
import static java.lang.Math.*;

public class TestNGDemo1 {

    @BeforeClass
    public void setup(){
        System.out.println("before Class will run once");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("After method will run before every test method");
    }
    @Test(priority = 2,description = "Admin user should be able to add product",
            groups = {"smoke test","regression test"})
    public void addProductTest(){
        System.out.println("this is for adding a new product");
        Assert.assertTrue("add product".contains("product"));
    }
    @Test(priority = 1,enabled = false,groups = {"regression test"})
    public void deleteProduct(){
        System.out.println("This test is for deleting product");
        Assert.assertEquals(10,10);
    }
    @Test(priority = 3,groups = {"regression test"})
    public void viewCustomer(){
        System.out.println("This test is for view customer");
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



}
