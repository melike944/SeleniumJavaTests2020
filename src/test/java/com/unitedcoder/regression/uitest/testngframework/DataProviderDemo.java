package com.unitedcoder.regression.uitest.testngframework;


import com.unitedcoder.cubecartautomation.TestBase;
import com.unitedcoder.regression.uitest.pageobjectmodel.DashboardPage;
import com.unitedcoder.regression.uitest.pageobjectmodel.LoginPage;
import com.unitedcoder.regression.uitest.pageobjectmodel.TestUtility;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderDemo extends TestBase {
    TestUtility utility;
    @BeforeClass
    public void setup(){
        initialzation();
    }
    @Test(dataProvider = "loginInfo")
    public void roleBasedSecurityTest(String username,String password){
        utility=new TestUtility(driver);
        utility.sleep(3);
        LoginPage loginPage=new LoginPage(driver);
        loginPage.loginUser(username,password);
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.logout();
    }
    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }
    @DataProvider
    public Object[][] loginInfo(){
        Object[][] testData=new Object[][]{
                {"testautomation","automation123!"},
                {"testautomation1","automation123!"},
                {"testautomation2","automation123!"}
        };
        return testData;
    }



}
