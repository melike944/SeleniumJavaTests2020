package com.unitedcoder.regression.uitest.pageobjectmodule;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CustomerPage {
    WebDriver driver;
    TestUtility utility;
    @FindBy(xpath = "//div[@id='customer-list']/table")
    WebElement customerTable;
    @FindAll(
            @FindBy(xpath = "//div[@id='customer-list']/table/tbody/tr")


    )
    List<WebElement> customerList;

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
    }


    public boolean viewCustomers(){
        utility.waitForElementPresent(customerTable);
        return customerList.size()>=1;
    }


}

