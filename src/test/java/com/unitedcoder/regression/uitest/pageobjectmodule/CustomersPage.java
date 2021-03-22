package com.unitedcoder.regression.uitest.pageobjectmodule;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class CustomersPage {

    WebDriver driver;
    TestUtility utility;
    @FindBy(xpath = "//div[@id='customer-list']/table")
    WebElement customerTable;
    @FindAll(
            @FindBy(xpath = "//div[@id='customer-list']/table/tbody/tr")
    )
    List<WebElement> customerList;
    @FindBy(xpath = "//div[@id=\"tab_control\"]/div[3]/a")
    WebElement addCustomerLink;
    @FindBy(css = "input[id='cust-firstname']")
    WebElement firstNameField;
    @FindBy(css = "input[id='cust-lastname']")
    WebElement lastNameField;
    @FindBy(css = "input[id='cust-email']")
    WebElement emailField;
    @FindBy(css = "input[name='save']")
    WebElement saveButton;
    @FindBy(css = "div.success")
    WebElement successMessage;
    @FindBy(css = "i.fa.fa-trash")
    WebElement deleteIcon;
    @FindBy(xpath = "//*[contains(text(),'Customer successfully deleted.')]")
    WebElement deleteSuccessfulMessage;

    public CustomersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utility = new TestUtility(driver);
    }

    public boolean viewCustomers() {
        utility.waitForElementPresent(customerTable);
        return customerList.size() >= 1;
    }

    public void addCustomer(String firstName, String lastName, String email) {
        utility.waitForElementPresent(addCustomerLink);
        addCustomerLink.click();
        utility.waitForElementPresent(firstNameField);
        firstNameField.sendKeys(firstName);
        utility.waitForElementPresent(lastNameField);
        lastNameField.sendKeys(lastName);
        utility.waitForElementPresent(emailField);
        emailField.sendKeys(email);
        utility.waitForElementPresent(saveButton);
        saveButton.click();
    }

    public boolean customerAddedSuccessfully() {
        utility.waitForElementPresent(successMessage);
        System.out.println(successMessage);
        return successMessage.isDisplayed();


    }

    public void deleteCustomer() {
        utility.waitForElementPresent(deleteIcon);
        deleteIcon.click();
        utility.waitForAlertPresent();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean verifyDeletedSuccessfully() {
        utility.waitForElementPresent(deleteSuccessfulMessage);
        if (deleteSuccessfulMessage.isDisplayed())
            return true;
        else
            return false;
    }
}
