package com.unitedcoder.regression.project.project4;

import com.unitedcoder.configutility.ApplicationConfig;
import com.unitedcoder.regression.uitest.pageobjectmodule.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class AddProductsPage {
    WebDriver driver;
    TestUtility utility;
    String configFile = "config.properties";
    @FindBy(linkText = "Products")
    WebElement productsLink;
    @FindBy(xpath = "//div[@class=\"tab\"]/a")
    WebElement addProductsLik;
    @FindBy(id = "name")
    WebElement productNameField;
    @FindBy(id = "product_code")
    WebElement productCodeField;
    @FindBy(xpath = "//input[@value=\"Save\"]")
    WebElement saveButton;
    @FindBy(xpath = "//div[@class=\"success\"]")
    WebElement verifyProductsAdded;

    public AddProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utility = new TestUtility(driver);
    }

    public  boolean addProducts() {
        utility.waitForElementPresent(productsLink);
        productsLink.click();
        String p1 = ApplicationConfig.readConfigProperties(configFile, "pN1");
        String p2 = ApplicationConfig.readConfigProperties(configFile, "pN2");
        String p3 = ApplicationConfig.readConfigProperties(configFile, "pN3");
        List<String> productsName = Arrays.asList(p1, p2, p3);
        for (String s : productsName) {
            utility.waitForElementPresent(addProductsLik);
            addProductsLik.click();
            utility.waitForElementPresent(productNameField);
            utility.waitForElementPresent(productCodeField);
            productNameField.sendKeys(s);
            saveButton.click();
            utility.waitForElementPresent(saveButton);
        }
        return verifyProductsAdded.isDisplayed();
    }

    public  boolean verify() {
        utility.waitForElementPresent(verifyProductsAdded);
        return verifyProductsAdded.isDisplayed();
    }
}
