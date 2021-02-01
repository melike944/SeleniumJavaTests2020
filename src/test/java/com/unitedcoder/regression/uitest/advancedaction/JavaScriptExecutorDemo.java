package com.unitedcoder.regression.uitest.advancedaction;
import com.unitedcoder.configutility.ApplicationConfig;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class JavaScriptExecutorDemo {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.twoplugs.com/");
    }

    @Test
    public void highLightElement() {
        sleep(2);
        WebElement join = driver.findElement(By.xpath("//span[@class=\"help\"]"));
        JavaScriptMethods.highLightElement(join, driver);
    }

    @Test
    public void drowElement() {
        sleep(2);
        WebElement join = driver.findElement(By.xpath("(//span[@class='help'])[2]"));
        waitForElementPresent(join);
        JavaScriptMethods.setElementBorder(join, driver);
        sleep(3);
        TakesScreenshot screenshot=(TakesScreenshot)driver;
        File screenShotFile=screenshot.getScreenshotAs(OutputType.FILE);
        String folder= ApplicationConfig.readConfigProperties("config.properties",
                "imagefolder");
        File finalFile=new File(folder+File.separator+"joionElent"+".png");
        try {
            FileUtils.copyFile(screenShotFile,finalFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void clickWithJS(){
        WebElement contactLink=driver.findElement(By.xpath("//a[@href='/contactus']"));
        waitForElementPresent(contactLink);
        JavaScriptMethods.clickWithJS(contactLink,driver);
    }
    @Test
    public void getTitle(){
        JavaScriptMethods.getTitle(driver);
        String title=JavaScriptMethods.getTitle(driver);
        System.out.println(title);
        JavaScriptMethods.generateAlert(driver,"You clicked button");
        sleep(3);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForElementPresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}