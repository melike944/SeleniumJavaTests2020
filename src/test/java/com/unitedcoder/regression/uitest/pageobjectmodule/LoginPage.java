package com.unitedcoder.regression.uitest.pageobjectmodule;


import com.unitedcoder.configutility.ApplicationConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    String configFile="config.properties";
    TestUtility utility;
    @FindBy(id="username")
    WebElement usernameField;
    @FindBy(id="password")
    WebElement passwordField;
    @FindBy(id="login")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
    }
    public void enterUserName(String userName){
        utility.waitForElementPresent(usernameField);
        usernameField.sendKeys(userName);
    }
    public void enterPassword(String password){
        utility.waitForElementPresent(passwordField);
        passwordField.sendKeys(password);
    }
    public void clickLoginButton(){
        utility.waitForElementPresent(loginButton);
        loginButton.click();
    }
    public void login(){
        enterUserName(ApplicationConfig.readConfigProperties(configFile,"username"));
        enterPassword(ApplicationConfig.readConfigProperties(configFile,"password"));
        clickLoginButton();
    }
    public void loginUser(String username,String password){
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
    }
}
