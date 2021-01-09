package com.unitedcoder.regression.uitest.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    WebDriver driver;
    TestUtility utility;
    @FindBy(linkText = "Customer List")
    WebElement customerLisLink;
    @FindBy(linkText = "Products")
    WebElement productsLink;
    @FindBy(linkText = "Categories")
    WebElement categoriesLink;
    @FindBy(css="i.fa.fa-sign-out")
    WebElement logoutButton;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
    }
    public boolean verifyLogin(){
        utility.waitForElementPresent(logoutButton);
        return logoutButton.isDisplayed();
    }
    public void clickOncustomersLink(){
        utility.waitForElementPresent(customerLisLink);
        customerLisLink.click();
    }
    public void clickOnProductssLink(){
        utility.waitForElementPresent(productsLink);
        productsLink.click();
    }
    public void clickOnCategorylink(){
        utility.waitForElementPresent(categoriesLink);
        categoriesLink.click();
    }
    public void logout(){
        utility.waitForElementPresent(logoutButton);
        logoutButton.click();
    }
}
