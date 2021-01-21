package com.unitedcoder.regression.uitest.testngframework;


import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import java.util.ArrayList;
import java.util.List;

public class TestResultListener implements ITestListener   {
    static List<ITestNGMethod> passedTest=new ArrayList<>();
    static List<ITestNGMethod> failedTest=new ArrayList<>();
    static List<ITestNGMethod> skippedTest=new ArrayList<>();
    ScreenShotUtility screenShotUtility=new ScreenShotUtility();

    public void onTestSuccess(ITestResult result) {
        passedTest.add(result.getMethod());
        System.out.println("Total Passed Test: "+passedTest.size());
    }

    public void onTestFailure(ITestResult result) {
        failedTest.add(result.getMethod());
        System.out.println("Total Failed Test: "+failedTest.size());
        screenShotUtility.takeScreenShot(result.getMethod().getMethodName(),
                (WebDriver)result.getTestContext().getAttribute("driver"));
    }

    public void onTestSkipped(ITestResult result) {
        skippedTest.add(result.getMethod());
        System.out.println("Total Skipped test: "+skippedTest.size());
    }

}



