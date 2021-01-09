package com.unitedcoder.regression.uitest.junittest;

import org.apache.commons.io.FileUtils;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.File;
import java.io.IOException;

public class JUnitConsoleRunner {
    //run Junit test using Junit core package
    public static void main(String[] args) {
        StringBuilder testLog=new StringBuilder();
        testLog.append("<html><head><title>CubeCart Test Report</title></head><body>");
        testLog.append("<p>").append("Test Executed By:")
                .append(System.getProperty("user.name")).append("</p>");
        testLog.append("<p>").append("Test Executed on: ").append(System.getProperty("os.name")).append("</p>");
        testLog.append("<table border=2 style='width:700px; height:250px'>");
        testLog.append("<tr><th>Total Test</th><th>Total Passed</th><th>Total Failed</th></tr>");
        Result testReult= JUnitCore.runClasses(JunitTetDemo.class,CubeCartJUnitFrameWork.class);
        int totalFailures=testReult.getFailureCount();
        int ignoredTest=testReult.getIgnoreCount();
        int totalPassed=testReult.getRunCount()-totalFailures;
        if(totalFailures==0) {
            testLog.append("<tr bgcolor='#006600'>");
            testLog.append("<td>").append(testReult.getRunCount()).append("</td>");
            testLog.append("<td>").append(totalPassed).append("</td>");
            testLog.append("<td>").append(totalFailures).append("</td>");
        }
        else {
            testLog.append("<tr>");
            testLog.append("<td bgcolor='#006600'>").append(testReult.getRunCount()).append("</td>");
            testLog.append("<td bgcolor='#006600'>").append(totalPassed).append("</td>");
            testLog.append("<td bgcolor='#ff3300'>").append(totalFailures).append("</td>");
        }
        if(totalFailures==0)
            System.out.println("All test passed");
        else{
            System.out.println(String.format("%d tests failed ",totalFailures));
            for(Failure failuedTest:testReult.getFailures()){
                testLog.append("<p> Failed Test: ").append(failuedTest.getMessage()).append("</p>");
                testLog.append("<p>").append(failuedTest.getDescription().getMethodName()).append("</p>");

            }
        }
        testLog.append("</tr></table></body></html>");
        try {
            FileUtils.writeStringToFile(new File("testdata\\unitTestReport.html"),testLog.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

