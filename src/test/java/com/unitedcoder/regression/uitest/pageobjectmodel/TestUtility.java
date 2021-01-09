package com.unitedcoder.regression.uitest.pageobjectmodel;

import com.unitedcoder.configutility.ApplicationConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtility {
    private int timeout=Integer.parseInt(ApplicationConfig
            .readConfigProperties("config.properties","timeout"));
    private WebDriver driver;
    public TestUtility(WebDriver driver){
        this.driver=driver;
    }
    public void waitForElementPresent(WebElement element){
        WebDriverWait wait=new WebDriverWait(driver,timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void slepp(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void waitForAlertPresent(){
        WebDriverWait wait=new WebDriverWait(driver,timeout);
        wait.until(ExpectedConditions.alertIsPresent());
    }
}
