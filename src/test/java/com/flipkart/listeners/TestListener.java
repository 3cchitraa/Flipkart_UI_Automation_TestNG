package com.flipkart.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.flipkart.basePage.TestBase;
import com.flipkart.utilities.DriverReader;
import com.flipkart.utilities.ScreenshotUtility;


public class TestListener implements ITestListener
{

	    @Override
	    public void onTestStart(ITestResult result) 
	    {
	        System.out.println("Test Started: " + result.getName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) 
	    {
	        System.out.println("Test Passed: " + result.getName());
	    }
	    
	    
	    @Override
	    public void onTestFailure(ITestResult result) 
	    {
	        System.out.println("Test Failed: " + result.getName());
	        String testName = result.getName();
            String errorMsg = result.getThrowable().getMessage();
	      
	        Object testClass = result.getInstance();
	        WebDriver driver = ((TestBase) testClass).getDriver(); 
	        
	        // Take screenshot
	        
	        ScreenshotUtility.takeScreenshot(driver, testName + "_" + clean(errorMsg));
//	        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//	        String fileName = result.getName() + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png";
//	        File destFile = new File("screenshots/" + fileName);
//
//	        try {
//	            FileUtils.copyFile(srcFile, destFile);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
	        
	    }
	    
	    private String clean(String input) {
	        return input.replaceAll("[^a-zA-Z0-9]", "_").substring(0, Math.min(30, input.length()));
	    }
		

	    @Override
	    public void onTestSkipped(ITestResult result) 
	    {
	        System.out.println("Test Skipped: " + result.getName());
	    }

	    @Override
	    public void onStart(ITestContext context) 
	    {
	        System.out.println("Suite started: " + context.getName());
	    }

	    @Override
	    public void onFinish(ITestContext context) 
	    {
	        System.out.println("Suite finished: " + context.getName());
	    }
}
