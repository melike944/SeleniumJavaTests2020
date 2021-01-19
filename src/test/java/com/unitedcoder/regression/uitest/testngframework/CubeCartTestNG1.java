package com.unitedcoder.regression.uitest.testngframework;


import com.unitedcoder.configutility.ApplicationConfig;
import com.unitedcoder.configutility.UiUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
@Listeners(TestResultListener.class)
public class CubeCartTestNG1 {
    String configFile="config.properties";
    WebDriver driver;
    String url= ApplicationConfig.readConfigProperties(configFile,"qaurl");
    UiUtility utility;
    @BeforeMethod
    public  void setup(ITestContext context){
        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
        context.setAttribute("driver",driver);
        utility=new UiUtility(driver);
        WebElement userNameField=driver.findElement(By.id("username"));
        String userName=ApplicationConfig.readConfigProperties(configFile,"username");
        utility.waitForElementPresent(userNameField);
        userNameField.sendKeys(userName);
        WebElement passwordField=driver.findElement(By.id("password"));
        String password=ApplicationConfig.readConfigProperties(configFile,"password");
        utility.waitForElementPresent(passwordField);
        passwordField.sendKeys(password);
        WebElement loginButton=driver.findElement(By.id("login"));
        utility.waitForElementPresent(loginButton);
        loginButton.click();

    }
    //Use case 1:admin user should be able to view customers
    @Test
    public void viewCustomersTest(){
        WebElement customerLink=driver.findElement(By.linkText("Customer List"));
        utility.waitForElementPresent(customerLink);
        customerLink.click();
        WebElement customerTable=driver.findElement(By.xpath("//div[@id='customer-list']/table"));
        utility.waitForElementPresent(customerTable);
        List<WebElement> customers=driver.findElements(By.xpath("//div[@id='customer-list']/table/tbody/tr"));
        System.out.println(customers.size());
        Assert.assertTrue(customers.size()>=1);
    }
    @AfterMethod
    public void teardown(){
        utility.sleep(3);
        WebElement logOutBUtton=driver.findElement(By.cssSelector("i.fa.fa-sign-out"));
        utility.waitForElementPresent(logOutBUtton);
        logOutBUtton.click();
        driver.close();
        driver.quit();

    }
}



