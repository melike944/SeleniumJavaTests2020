package com.unitedcoder.regression.project.project5;


import com.unitedcoder.configutility.ApplicationConfig;
import com.unitedcoder.regression.databasetest.ConnectionManager;
import com.unitedcoder.regression.databasetest.ConnectionType;
import com.unitedcoder.regression.databasetest.DataAccess;

import com.unitedcoder.regression.uitest.pageobjectmodule.DashboardPage;
import com.unitedcoder.regression.uitest.pageobjectmodule.LoginPage;

import org.testng.Assert;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.Random;

public class CubeCartDBTest {

    WebDriver driver;
    Connection connection=null;
    static String configFile="config.properties";
    String url= ApplicationConfig.readConfigProperties(configFile,"qaurl");
    static String dbUrl= ApplicationConfig.readConfigProperties(configFile,"cubeCartProd.dbUrl");
    static String port=ApplicationConfig.readConfigProperties(configFile,"cubeCartProd.port");
    static String username=ApplicationConfig.readConfigProperties(configFile,"cubeCartProd.username");
    static String password=ApplicationConfig.readConfigProperties(configFile,"cubeCartProd.password");
    static String defaultSchema=ApplicationConfig.readConfigProperties(configFile,"cubeCartProd.defaultSchema");
    CustomersPage customersPage;
    ProductsPage productsPage;
    @BeforeClass
    public void setup(){
        connection= ConnectionManager.connectToDataBaseServer(dbUrl,port,username,password,
                defaultSchema, ConnectionType.MYSQLServer);
        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
        LoginPage loginPage=new LoginPage(driver);
        loginPage.login();
    }
    @Test(description = "Admen user should be able to add multiple customers ",dataProvider = "customers")
    public void addCustomers(String firstName, String lastName, String email){
        customersPage =new CustomersPage(driver);
        customersPage.clickCustomerListLink();
        boolean customersSuccessfullyAdded=customersPage.addCustomers(firstName,lastName,email);
        Assert.assertTrue(customersSuccessfullyAdded);
        //verify customers in database
        DataAccess dataAccess=new DataAccess();
        boolean isCustomerExist=dataAccess.getCustomer(email,connection);
        Assert.assertTrue(isCustomerExist);
    }

    @Test(description = "Admin user should be able to add multiple products",dataProvider = "products")
    public void addProducts(String productName,String productCode){
        productsPage=new ProductsPage(driver);
        boolean productsAddedSuccessfully=productsPage.addProducts(productName,productCode);
        Assert.assertTrue(productsAddedSuccessfully);
        //verify data base
        DataAccess dataAccess=new DataAccess();
        boolean isProductsExist=dataAccess.getProductName(productName,connection);
        Assert.assertTrue(isProductsExist);
    }

    @AfterClass
    public void tearDown(){
        ConnectionManager.closeDataBaseConnection(connection);
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.logout();
        driver.close();driver.quit();

    }
    public void sleep(int second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @DataProvider(name = "customers")
    public Object[] customers(){
        Object[][] customers=new Object[][]

                {
                        {"001melike","ipar","zubeyir"+System.currentTimeMillis()+"@gmail.com"},
                        {"002melike","ipar","abdukahar"+System.currentTimeMillis()+"@gmail.com"},
                        {"003melike","ipar","sebire"+System.currentTimeMillis()+"@gmail.com"}
                };
        return customers;
    }
    @DataProvider(name = "products")
    public Object[] products(){
        Random ran=new Random(5000);
        Object[][] products=new Object[][]

                {
                        {"Iphone"+System.currentTimeMillis(),"ABS"+ran.nextInt()},
                        {"Macbook"+System.currentTimeMillis(),"ABS"+ran.nextInt()},
                        {"Laptop"+System.currentTimeMillis(),"ABS"+ran.nextInt()}
                };
        return products;}
}


