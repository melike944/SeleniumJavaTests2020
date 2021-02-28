package com.unitedcoder.regression.databasetest;


import com.unitedcoder.configutility.ApplicationConfig;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

public class DataBaseInsertTest {
    static String configFile="config.properties";
    static String StandAloneUrl= ApplicationConfig.readConfigProperties(configFile,"standalone.dbUrl");
    static String port=ApplicationConfig.readConfigProperties(configFile,"standalone.port");
    static String username=ApplicationConfig.readConfigProperties(configFile,"standalone.username");
    static String password=ApplicationConfig.readConfigProperties(configFile,"standalone.password");
    static String defaultSchema=ApplicationConfig.readConfigProperties(configFile,"standalone.defaultSchema");
    Connection connection=null;
    @BeforeClass
    public void setUp(){
        connection=ConnectionManager.connectToDataBaseServer(StandAloneUrl,port,
                username,password,defaultSchema,ConnectionType.MYSQLServer);
    }
    @Test(description = "Admin user should be able to insert record into cubecart category table",
            groups ={"insert record"} )
    public void verifyCategoryInsertTest(){
        CategoryObject categoryObject=new CategoryObject("melike_MySql1",
                "My_SQL_Demo1",1,0,1,"demo","ttr",
                "Key",1,1);
        DataAccess access=new DataAccess();
        boolean isRecordInserted=access.insertNewCategory(categoryObject,connection);
        Assert.assertTrue(isRecordInserted);
    }
    @Test(description = "Admin user should be able to delelte category",dependsOnGroups = {"insert record"})
    public void deleteCategoryTest(){
        DataAccess access=new DataAccess();
        boolean isDeleted=access.deleteCategory("melike_MySql1",connection);
        Assert.assertTrue(isDeleted);
    }
    @AfterClass
    public void tearDown(){
        ConnectionManager.closeDataBaseConnection(connection);
    }
}

