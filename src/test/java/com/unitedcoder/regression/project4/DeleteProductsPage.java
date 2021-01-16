package com.unitedcoder.regression.project4;

import com.unitedcoder.configutility.ApplicationConfig;
import com.unitedcoder.regression.uitest.pageobjectmodel.TestUtility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DeleteProductsPage {

        WebDriver driver;
        TestUtility utility;
    String configFile = "config.properties";
        @FindBy(linkText = "Products")
        WebElement productsLink;
        @FindBy(css = "i.fa.fa-trash")
        WebElement deleteIcon;
        @FindBy(xpath = "//div[@class='success']")
        WebElement successfullyDeletedProduct;
    String p1 = ApplicationConfig.readConfigProperties(configFile, "pN1");
    String p2 = ApplicationConfig.readConfigProperties(configFile, "pN2");
    String p3 = ApplicationConfig.readConfigProperties(configFile, "pN3");

        public DeleteProductsPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
            utility = new TestUtility(driver);

        }

        public boolean deleteProducts() throws InterruptedException {
            utility.waitForElementPresent(productsLink);
            productsLink.click();
            List<WebElement> ProductsNameList = driver.findElements(By.xpath("//div[@id='general']/table[3]/tbody/tr"));

            for (int i = 1; i <=ProductsNameList.size(); i++) {
                WebElement ProductsName=driver.findElement(By.xpath("//div[@id='general']/table[3]/tbody/tr["+i+"]/td[2]"));
                if(ProductsName.getText().equals(p1)||ProductsName.getText().equals(p2)|| ProductsName.getText().equals(p3)) {
                    WebElement CheckBox = driver.findElement(By.xpath("//div[@id='general']/table[3]/tbody/tr[" + i + "]/td/div/input"));
                    CheckBox.click();
                    WebElement deleteIcon = driver.findElement(By.xpath("//div[@id=\"general\"]/table[3]/tbody/tr[" + i + "]/td[11]/a[4]"));
                    deleteIcon.click();

                    Alert alert = driver.switchTo().alert();
                    alert.accept();
                    WebElement verifyDeleting = driver.findElement(By.xpath("//div[@class='success']"));


//            for (int i = 0; i <= 2; i++) {
//                utility.waitForElementPresent(deleteIcon);
//                deleteIcon.click();
//                Thread.sleep(4000);
//                utility.waitForAlertPresent();
//                Alert alert = driver.switchTo().alert();
//                alert.accept();
//            }
//            return successfullyDeletedProduct.isDisplayed();
//        }
                }}
            return true;
        }
        public boolean verify() {
            utility.waitForElementPresent(successfullyDeletedProduct);
            return successfullyDeletedProduct.isDisplayed();
        }

    }
