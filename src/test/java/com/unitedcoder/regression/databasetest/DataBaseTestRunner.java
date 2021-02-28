package com.unitedcoder.regression.databasetest;


import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

public class DataBaseTestRunner {
    static String dbUrl="148.72.106.125";
    static String port="3306";
    static String username="testautomation";
    static String password="automation123!";
    static String defaultSchema="i4296639_cc1";
    Connection connection=null;
    @BeforeClass
    public void setUp(){
        connection=ConnectionManager.connectToDataBaseServer(dbUrl,port,
                username,password,defaultSchema,ConnectionType.MYSQLServer);
    }
    @Test(description = "CubeCart inventory table should have expected products")
    public void verifyProducts(){
        DataAccess access=new DataAccess();
        boolean isProductFound=access.getProductName("Toyota",connection);
        Assert.assertTrue(isProductFound);
    }
    @Test(description = "CubeCart inventory table should have expected products")
    public void verifyProduct2(){
        DataAccess access=new DataAccess();
        boolean isProductFound=access.getProductName("Electronics",connection);
        Assert.assertTrue(isProductFound);
    }
    @Test(description = "CubeCart customer table should have expected customers")
    public void verifyCustomer(){
        DataAccess access=new DataAccess();
        boolean isCustomerFound=access.getCustomer("123@gmail.com",connection);
        Assert.assertTrue(isCustomerFound);
    }
    @AfterClass
    public void tearDown(){
        ConnectionManager.closeDataBaseConnection(connection);
    }
}
