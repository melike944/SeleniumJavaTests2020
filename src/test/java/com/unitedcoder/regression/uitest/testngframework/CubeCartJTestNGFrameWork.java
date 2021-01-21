package com.unitedcoder.regression.uitest.testngframework;

import com.unitedcoder.classconcepts.ProductContent;
import com.unitedcoder.configutility.ApplicationConfig;
import com.unitedcoder.configutility.UiUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class CubeCartJTestNGFrameWork {
    String configFile="config.properties";
    WebDriver driver;
    String url=ApplicationConfig.readConfigProperties(configFile,"qaurl");
    UiUtility utility;
    @BeforeMethod
    public  void setup(){
        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
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
    @Test(enabled = false)
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
    //Use case 2:admin user should be able to add product

    @Test()
    public void addProductTest(){
        utility.sleep(3);
        WebElement productsLink=driver.findElement(By.id("nav_products"));
        utility.waitForElementPresent(productsLink);
        productsLink.click();
        WebElement addProductLink=driver.findElement(By.xpath("//*[text()='Add Product']"));
        utility.waitForElementPresent(addProductLink);
        addProductLink.click();
        //aynigar10 20 30
        ProductContent content=new ProductContent("melike"+System.currentTimeMillis(),
                "pz7865");
        driver.findElement(By.id("name")).sendKeys(content.getProductName());
        driver.findElement(By.id("product_code")).sendKeys(content.getProductCode());
        WebElement dropDown=driver.findElement(By.id("condition"));
        utility.waitForElementPresent(dropDown);
        Select selectDropDown=new Select(dropDown);
        selectDropDown.selectByValue("used");
        WebElement saveButton=driver.findElement(By.xpath("//input[@value='Save']"));
        utility.waitForElementPresent(saveButton);
        saveButton.click();
        WebElement successfulMessage=driver.findElement(By.xpath("//div[@id=\"gui_message\"]/div[2]"));
        Assert.assertTrue(successfulMessage.isDisplayed());
    }
    //Use case 3:admin user should be able to add a product category

    @Test(enabled = false)
    public void addProductCategory(){
        WebElement categoryLink=driver.findElement(By.id("nav_categories"));
        utility.waitForElementPresent(categoryLink);
        categoryLink.click();
        WebElement addCatogoryLink=driver.findElement(By.linkText("Add Category"));
        utility.waitForElementPresent(addCatogoryLink);
        addCatogoryLink.click();
        WebElement nameTextField=driver.findElement(By.id("name"));
        utility.waitForElementPresent(nameTextField);
        nameTextField.sendKeys("Java-Selenium"+System.currentTimeMillis());
        WebElement saveButton=driver.findElement(By.id("cat_save"));
        utility.waitForElementPresent(saveButton);
        saveButton.click();
        WebElement successfulMessage=driver.findElement(By.cssSelector("div.success"));
        Assert.assertTrue(successfulMessage.isDisplayed());
    }
    @AfterMethod
    public void teardown(){
        utility.sleep(3);
        WebElement logOutButton=driver.findElement(By.cssSelector("i.fa.fa-sign-out"));
        utility.waitForElementPresent(logOutButton);
        logOutButton.click();
        driver.close();
        driver.quit();




}}
