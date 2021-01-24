package com.unitedcoder.regression.uitest.advancedaction;


import com.unitedcoder.regression.uitest.testngframework.ScreenShotUtility;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SeleniumAdvancedActions {
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
    public void dragAndDrop(){
        driver.get("https://jqueryui.com/droppable/");
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");
        sleep(3);
        WebElement iframe=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        WebElement draggable=driver.findElement(By.id("draggable"));
        WebElement droppable=driver.findElement(By.id("droppable"));
        Actions actions=new Actions(driver);
        //actions.dragAndDrop(draggable,droppable).build().perform();
        //actions.clickAndHold(draggable).moveToElement(droppable).release().build().perform();
        actions.dragAndDropBy(draggable,10,0).dragAndDrop(draggable,droppable)
                .build().perform();
        sleep(3);
        Assert.assertTrue(droppable.getText().contains("Dropped"));
    }
    @Test
    public void classicMusicTest(){
        driver.get("https://jqueryui.com/menu/");
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");
        WebElement frame=driver.findElement(By.cssSelector(".demo-frame"));
        driver.switchTo().frame(frame);
        WebElement musicMenu=driver.findElement(By.id("ui-id-9"));
        sleep(2);
        waitForElementPresent(musicMenu);
        Actions actions=new Actions(driver);
        actions.moveToElement(musicMenu).build().perform();
        WebElement rockMenu=driver.findElement(By.id("ui-id-10"));
        waitForElementPresent(rockMenu);
        sleep(2);
        //Actions actions1=new Actions(driver);
        actions.moveToElement(musicMenu).moveToElement(rockMenu).build().perform();
        WebElement classMenu=driver.findElement(By.id("ui-id-12"));
        waitForElementPresent(classMenu);
        Assert.assertTrue(classMenu.isDisplayed());

    }
    @Test
    public void multipleWindowTest() {
        driver.get("http://forum.seleniummaster.com/testfiles/windowtest.html");
        WebElement openWindowLink = driver.findElement(By.linkText("Open Window"));
        String currentWindow = driver.getWindowHandle();
        waitForElementPresent(openWindowLink);
        openWindowLink.click();
        for (String childWindow : driver.getWindowHandles()) {
            System.out.println("window name is :" + childWindow);
            if (!childWindow.equalsIgnoreCase(currentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement confirmBUtton = driver.findElement(By.name("Abutton1"));
                waitForElementPresent(confirmBUtton);
                Assert.assertTrue(confirmBUtton.isDisplayed());
            }
        }
    }
    @Test
    public void iterateMultipleWindows(){
        driver.get("http://forum.seleniummaster.com/testfiles/windowtest.html");
        WebElement openWindowLink=driver.findElement(By.linkText("Open Window"));
        waitForElementPresent(openWindowLink);
        openWindowLink.click();
        Set<String> windows=driver.getWindowHandles();
        Iterator<String> iterator=windows.iterator();
        String current=iterator.next();//current window
        String newWindow=iterator.next();
        driver.switchTo().window(newWindow);
        WebElement confirmBUtton = driver.findElement(By.name("Abutton1"));
        waitForElementPresent(confirmBUtton);
        Assert.assertTrue(confirmBUtton.isDisplayed());
    }
    @Test(description = "This test is for select multiple elements")
    public void selectableItems(){
        driver.get("https://jqueryui.com/selectable/");
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");
        sleep(3);
        WebElement iframe=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        List<WebElement> items=driver.findElements(By.xpath("//ol[@id=\"selectable\"]/li"));
        int itemCount=items.size();
        int counter=0;
        for(WebElement eachItem:items){
            waitForElementPresent(eachItem);
            eachItem.click();
            counter++;
            sleep(2);
        }
        Assert.assertTrue(itemCount==counter);

    }
    @Test(description = "This tesi is for select multiple elements")
    public void MultipleLinkTest() {
        driver.get("https://jqueryui.com/");
        sleep(3);
        List<WebElement> links = driver.findElements(By.xpath("//div[@id=\"sidebar\"]//a"));
        List<String> urls=new ArrayList<>();
        for (WebElement eachLink : links) {
            String url=eachLink.getAttribute("href");
            urls.add(url);
        }
        int count=0;
        for(String url:urls){
            driver.navigate().to(url);
            sleep(2);
            ScreenShotUtility screenShotUtility=new ScreenShotUtility();
            screenShotUtility.takeScreenShot(url.replace("https://jqueryui.com/","")
                    .replaceAll("/",""),driver);
            count++;
            if(count>=10)
                break;
        }
        Assert.assertTrue(urls.size()>1);

    }
    @Test
    public void verifyStatusCode(){
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        List<WebElement> links=driver.findElements(By.cssSelector("li[class=\"gf-li\"] a"));
        for(WebElement eachLink:links){
            String url=eachLink.getAttribute("href");
            HttpURLConnection connection=null;
            try {
                connection=(HttpURLConnection)new URL(url).openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                connection.setRequestMethod("HEAD");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            try {
                connection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int responseCode=0;
            try {
                responseCode=connection.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(responseCode);
            if(responseCode>400){
                System.out.println("The link with Text "+eachLink.getText()+" is a broken link"+
                        "With response code : "+responseCode
                );
            }

        }

    }

    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();

    }
    public void waitForElementPresent(WebElement element){
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void sleep(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



