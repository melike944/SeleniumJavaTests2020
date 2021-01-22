package com.unitedcoder.regression.project.jquer;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JQueryTest {
    WebDriver driver;
    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();


    }
        @Test
        public void resizableTest(){
            driver.get("https://jqueryui.com/resizable/");
            // location iframe
            WebElement frameElement=driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(frameElement);
            //location resizable
            WebElement resizable=driver.findElement(By.xpath("//*[@id=\"resizable\"]/h3"));
            int width=resizable.getSize().width;
            System.out.println("width 1= "+width);
            WebElement point=driver.findElement(By.xpath("//*[@id=\"resizable\"]/div[3]"));
            Actions actions=new Actions(driver);
            sleep(3);
            actions.dragAndDropBy(point,20,30).perform();
            int width2=resizable.getSize().width;
            System.out.println("Width2 ="+width2);
            Assert.assertTrue(width!=width2);
        }

        @Test
        public void menuTest(){
            driver.get("https://jqueryui.com/menu/");
            WebElement frameElement=driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(frameElement);
            WebElement musicMenu = driver.findElement(By.id("ui-id-9"));
            WebElement jazz=driver.findElement(By.id("ui-id-13"));
            WebElement bigBand=driver.findElement(By.id("ui-id-15"));
            sleep(2);
            Actions actions1=new Actions(driver);
            actions1.moveToElement(musicMenu).build().perform();
            sleep(2);
            actions1.moveToElement(jazz).build().perform();
            sleep(3);
            actions1.moveToElement(bigBand).build().perform();
            sleep(3);
            Assert.assertTrue(bigBand.isDisplayed());

        }
        @Test
            public void sliderTest(){
                driver.get("https://jqueryui.com/slider/");
                WebElement frameElement=driver.findElement(By.tagName("iframe"));
                driver.switchTo().frame(frameElement);
                WebElement slider1=driver.findElement(By.xpath("//*[@class=\"ui-slider-handle ui-corner-all ui-state-default\"]"));
                WebElement slider2=driver.findElement(By.xpath("//*[@class=\"ui-slider-handle ui-corner-all ui-state-default\"]"));
                int widthSlid1=slider1.getSize().width;
                System.out.println("Width slide 1 = "+widthSlid1);
                int widthSlid2=slider2.getSize().width;
                sleep(3);
                Actions actions=new Actions(driver);
                actions.clickAndHold(slider1).moveToElement(slider2,50,0).release().perform();
                sleep(3);
                System.out.println("width slid 2 ="+widthSlid2);
                Assert.assertTrue(slider2.isDisplayed());
            }






        @AfterClass
        public void tearDown(){
            driver.close();
            driver.quit();

        }
        public void sleep(int seconds){
            try {
                Thread.sleep(seconds*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

