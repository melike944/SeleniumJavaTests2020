package com.unitedcoder.regression.uitest.testngframework;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGDemo3 {
    @Test(invocationCount = 3)
    public void test1(){
        System.out.println("Test1....");
        Assert.assertEquals(10,10);
    }
    @Test
    public void test2(){
        System.out.println("Test2....");
        Assert.assertEquals(20,20);
    }


}
