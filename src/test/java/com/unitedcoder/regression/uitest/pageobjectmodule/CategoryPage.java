package com.unitedcoder.regression.uitest.pageobjectmodule;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryPage {
    WebDriver driver;
    TestUtility utility;
    @FindBy(css="i.fa.fa-trash")
    WebElement deleteIcon;
    @FindBy(xpath = "//div[@class='success']")
    WebElement successfullyDeletedMessage;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
    }
    public boolean deleteCategory(){
        utility.waitForElementPresent(deleteIcon);
        deleteIcon.click();
        utility.waitForAlertPresent();
        Alert alert=driver.switchTo().alert();
        alert.accept();
        return successfullyDeletedMessage.isDisplayed();
    }
}
