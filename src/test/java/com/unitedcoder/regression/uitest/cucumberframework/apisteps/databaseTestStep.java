package com.unitedcoder.regression.uitest.cucumberframework.apisteps;

import com.unitedcoder.configutility.ApplicationConfig;
import com.unitedcoder.regression.databasetest.ConnectionManager;
import com.unitedcoder.regression.databasetest.ConnectionType;
import com.unitedcoder.regression.databasetest.DataAccess;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.Connection;

public class databaseTestStep {
    static String configFile="config.properties";
    static String dbUrl= ApplicationConfig.readConfigProperties(configFile,"cubeCartProd.dbUrl");
    static String port=ApplicationConfig.readConfigProperties(configFile,"cubeCartProd.port");
    static String username=ApplicationConfig.readConfigProperties(configFile,"cubeCartProd.username");
    static String password=ApplicationConfig.readConfigProperties(configFile,"cubeCartProd.password");
    static String defaultSchema=ApplicationConfig.readConfigProperties(configFile,"cubeCartProd.defaultSchema");
    Connection connection=null;
    @Before("@DatabaseTest")
    public void setup() {
        connection= ConnectionManager.connectToDataBaseServer(dbUrl,port,
                username,password,defaultSchema, ConnectionType.MYSQLServer);
    }

    @After("@DatabaseTest")
    public void tearDown(){
        ConnectionManager.closeDataBaseConnection(connection);
    }
    boolean isProductAccess=false;
    @Given("a user has access to the cc_CubeCart_inventory table")
    public void aUserHasAccessToTheCc_CubeCart_inventoryTable() {
        System.out.println("Already accessed to the cubecart data base");
    }

    @When("user query the query script in the cc_CubeCart_inventory table")
    public void userQueryTheQueryScriptInTheCc_CubeCart_inventoryTable() {
        DataAccess access=new DataAccess();
        isProductAccess=access.getProductName("Toyota",connection);
    }

    @Then("user should be see product info")
    public void userShouldBeSeeProductInfo() {
        Assert.assertTrue(isProductAccess);
    }

}
