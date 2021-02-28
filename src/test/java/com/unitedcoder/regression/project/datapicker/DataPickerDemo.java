package com.unitedcoder.regression.project.datapicker;


import com.unitedcoder.configutility.ApplicationConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DataPickerDemo {
    /*
Open Chrome browser and navigate to the site "https://jqueryui.com/demos/".
Click on the Datepicker link under the Widgets in the left navigation section.
Click on the link "Select a Date Range"  under the Examples section.
Set From field to "02/20/2018", and to field "03/20/2018".
Verify that From field has the text "02/20/2018" and to field has the text "03/20/2018".
 */
    String configFile = "config.properties";
    WebDriver driver;
    String url = ApplicationConfig.readConfigProperties(configFile, "jquery");

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test
    public void datePickerTest() {
        WebElement datePickerLink = driver.findElement(By.linkText("Datepicker"));
        waitForElementPresent(datePickerLink);
        datePickerLink.click();
        WebElement dateRangeLink=driver.findElement(By.linkText("Select a Date Range"));
        waitForElementPresent(dateRangeLink);
        dateRangeLink.click();
        WebElement frameElement=driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frameElement);
        sleep(3);
        WebElement from=driver.findElement(By.id("from"));
        waitForElementPresent(from);
        from.click();
        sleep(3);
        String targetYear="2018";
        while (true){
            WebElement prevIcon=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/a/span"));
            waitForElementPresent(prevIcon);
            prevIcon.click();
            sleep(1);
            String selectedYear=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span")).getText();
            if (selectedYear.contains(targetYear)){
                System.out.println("Selected year is "+selectedYear);
                break;
            }
        }
        Select selectMonth=new Select(driver.findElement(By.cssSelector(".ui-datepicker-month")));
        selectMonth.selectByVisibleText("Feb");
        String selectDay20=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[4]/td[4]/a")).getText();
        if (selectDay20.contains("20")){
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[4]/td[3]")).click();
            sleep(2);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();

    }

    public void sleep(int second) {
        try {
            Thread.sleep(second * 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForElementPresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}


