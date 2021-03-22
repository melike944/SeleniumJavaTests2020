package com.unitedcoder.regression.uitest.cucumberframework;

import com.unitedcoder.configutility.ApplicationConfig;
import com.unitedcoder.cubecartautomation.TestBase;
import com.unitedcoder.regression.browserutils.JenkinsBrowserMode;
import com.unitedcoder.regression.uitest.pageobjectmodule.DashboardPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class Hook extends TestBase {

    final static String configFile = "config.properties";
    final static String url = ApplicationConfig.readConfigProperties(configFile, "qaurl");

    @Before("@regressionTest")
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        JenkinsBrowserMode browserMode = new JenkinsBrowserMode();
        boolean useHeadless = browserMode.setHeadlessModeIfLinux(chromeOptions);
        if (!useHeadless) {
            System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
        }
        //define a webdriver
        driver = new ChromeDriver(chromeOptions);
        //maximize browser window
        if (!useHeadless) {
            driver.manage().window().maximize();
        }
        driver.get(url);

    }

    @After("@regressionTest")
    public void teardown() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.logout();
        driver.close();

    }
}