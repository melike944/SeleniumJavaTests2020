package com.unitedcoder.regression.uitest.advancedaction;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AdvancedActionDemo1 {
    WebDriver driver;
    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");
    }
    @Test
    public void amazonTest() throws InterruptedException {
        WebElement accountList=driver.findElement(By.cssSelector("a[id='nav-link-accountList']"));
        WebElement searchBox=driver.findElement(By.id("twotabsearchtextbox"));
        Actions actions=new Actions(driver);
        Thread.sleep(2000);
        actions.moveToElement(searchBox).click().keyDown(Keys.SHIFT).sendKeys("iphone").doubleClick().
                build().perform();
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        actions.moveToElement(accountList).build().perform();

    }
    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();

    }
}


