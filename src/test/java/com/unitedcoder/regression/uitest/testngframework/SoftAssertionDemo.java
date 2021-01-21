package com.unitedcoder.regression.uitest.testngframework;


import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertionDemo {
    SoftAssert softAssert=new SoftAssert();
    @Test
    public void dummyTest(){
        System.out.println("Open Browser");
        Assert.assertEquals(10,10);
        System.out.println("Login");
        Assert.assertEquals(10,10);
        System.out.println("Add Product");
        softAssert.assertEquals(10,20);
        System.out.println("Add Customer");
        softAssert.assertEquals(10,10);
        System.out.println("Add review");
        softAssert.assertAll();
    }
}



