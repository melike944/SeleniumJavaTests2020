package com.unitedcoder.regression.project.project5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
    WebDriver driver;
    CubeCartTestUtility utility;
    String configFile="config.properties";
    @FindBy(linkText = "Products")
    WebElement productsLink;
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
    WebElement successMessage;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new CubeCartTestUtility(driver);
    }
    // write the method for each action
    public void clickOnProductsLink(){
        utility.waitForElementPresent(productsLink);
        productsLink.click();
    }
    public void clickOnAddProductLink(){
        utility.waitForElementPresent(addProductLink);
        addProductLink.click();
    }
    public void enterProductName(String productName){
        utility.waitForElementPresent(productNameField);
        productNameField.sendKeys(productName);
        productNameField.getText();
    }
    public void enterProductCode(String productCode){
        utility.waitForElementPresent(productCodeField);
        productCodeField.sendKeys(productCode);
        productCodeField.getText();
    }
    public void clickOnSaveButton(){
        utility.waitForElementPresent(saveButton);
        saveButton.click();
    }
    public boolean productAddedSuccessfully() {
        utility.waitForElementPresent(successMessage);
        System.out.println(successMessage);
        return successMessage.isDisplayed();
    }
    //  add product method
    public boolean addProducts(String productName,String productCode){
        clickOnProductsLink();
        clickOnAddProductLink();
        enterProductName(productName);
        enterProductCode(productCode);
        clickOnSaveButton();
        productAddedSuccessfully();
        return productAddedSuccessfully();
    }
}

