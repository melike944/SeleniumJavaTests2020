package com.unitedcoder.regression.uitest.pageobjectmodule;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
    WebDriver driver;
    TestUtility utility;
    @FindBy(linkText = "Add Product")
    WebElement addProductLink;
    @FindBy(xpath = "//img[@rel=\"#product_status\"]")
    WebElement productStatusCheckBox;
    @FindBy(id="name")
    WebElement productNameField;
    @FindBy(id="product_code")
    WebElement productCodeField;
    @FindBy(xpath = "//input[@value=\"Save\"]")
    WebElement saveButton;
    @FindBy(xpath = "//div[@class=\"success\"]")
    WebElement saveSuccessfulMessage;
    @FindBy(css = "i.fa.fa-trash")
    WebElement deleteIcon;
    @FindBy(xpath = "//*[contains(text(),'Product(s) successfully deleted.')]")
    WebElement deleteSuccessfulMessage;


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
    }
    public boolean addProduct(){
        utility.waitForElementPresent(addProductLink);
        addProductLink.click();
        utility.waitForElementPresent(productStatusCheckBox);
        productStatusCheckBox.click();
        utility.waitForElementPresent(productNameField);
        productNameField.sendKeys("abdusamad-products"+System.currentTimeMillis());
        utility.waitForElementPresent(saveButton);
        saveButton.click();
        utility.waitForElementPresent(saveSuccessfulMessage);
        return saveSuccessfulMessage.isDisplayed();
    }
    public void addProducts(String productName,String productCode){
        utility.waitForElementPresent(addProductLink);
        addProductLink.click();
        utility.waitForElementPresent(productStatusCheckBox);
        productStatusCheckBox.click();
        utility.waitForElementPresent(productNameField);
        productNameField.sendKeys(productName);
        utility.waitForElementPresent(productCodeField);
        productCodeField.sendKeys(productCode);
        utility.waitForElementPresent(saveButton);
        saveButton.click();
    }
    public boolean verifyNewProductAdded(){
        utility.waitForElementPresent(saveSuccessfulMessage);
        return saveSuccessfulMessage.isDisplayed();
    }
    public void deleteProduct(){
        utility.waitForElementPresent(deleteIcon);
        deleteIcon.click();
        Alert alert=driver.switchTo().alert();
        alert.accept();
    }
    public boolean verifyDeleteProductSuccessfully(){
        utility.waitForElementPresent(deleteSuccessfulMessage);
        if (deleteSuccessfulMessage.isDisplayed())
            return true;
        else
            return false;
    }
}

