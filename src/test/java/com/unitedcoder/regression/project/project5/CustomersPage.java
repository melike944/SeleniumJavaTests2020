package com.unitedcoder.regression.project.project5;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomersPage {
    WebDriver driver;
    CubeCartTestUtility utility;
    @FindBy(xpath = "//ul[@id=\"menu_Customers\"]/li[1]/a")
    WebElement customerListLink;
    @FindBy(xpath = "//div[@id=\"tab_control\"]/div[3]/a")
    WebElement addCustomerLink;
    @FindBy(css = "input[id='cust-firstname']")
    WebElement firstNameField;
    @FindBy(css = "input[id='cust-lastname']")
    WebElement lastNameField;
    @FindBy(css = "input[id='cust-email']")
    WebElement emailField;
    @FindBy(id = "cust-phone")
    WebElement phoneNumberField;
    @FindBy(css = "input[name='save']")
    WebElement saveButton;
    @FindBy(css = "div.success")
    WebElement successMessage;

    //1.default constructor
    public CustomersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);//unrecognized elements without this method
        utility = new CubeCartTestUtility(driver);//the reason is like the line above;
    }

    //2.write method for each actions
    public void clickCustomerListLink() {
        utility.waitForElementPresent(customerListLink);
        customerListLink.click();
    }

    public void clickAddCustomerLink() {
        utility.waitForElementPresent(addCustomerLink);
        addCustomerLink.click();
    }

    public void enterFirstName(String firstName) {
        utility.waitForElementPresent(firstNameField);
        firstNameField.sendKeys(firstName);
        firstNameField.getText();
    }

    public void enterLastname(String lastName) {
        utility.waitForElementPresent(lastNameField);
        lastNameField.sendKeys(lastName);
        lastNameField.getText();
    }

    public void enterEmail(String email) {
        utility.waitForElementPresent(emailField);
        emailField.sendKeys(email);
        emailField.getText();
    }

    public void clickSaveButton() {
        utility.waitForElementPresent(saveButton);
        saveButton.click();
    }

    public boolean customerAddedSuccessfully() {
        utility.waitForElementPresent(successMessage);
        System.out.println(successMessage);
        return successMessage.isDisplayed();
    }

    //3.add customers
    public boolean addCustomers(String firstName, String lastName, String email) {
        clickCustomerListLink();
        clickAddCustomerLink();
        enterFirstName(firstName);
        enterLastname(lastName);
        enterEmail(email);
        clickSaveButton();
        return customerAddedSuccessfully(); }

}


