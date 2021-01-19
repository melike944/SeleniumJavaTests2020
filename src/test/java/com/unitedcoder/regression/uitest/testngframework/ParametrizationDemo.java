package com.unitedcoder.regression.uitest.testngframework;

import com.unitedcoder.regression.uitest.pageobjectmodel.DashboardPage;
import com.unitedcoder.regression.uitest.pageobjectmodel.LoginPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametrizationDemo {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test(groups = {"smoke test"})
    @Parameters({"url","username","password"})
    public void loginTest(String url,String username,String password){
        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        loginPage=new LoginPage(driver);
        loginPage.loginUser(username,password);
        dashboardPage=new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.verifyLogin());
        dashboardPage.logout();
        driver.close();
        driver.quit();
    }



}
