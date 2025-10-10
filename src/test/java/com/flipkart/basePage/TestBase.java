package com.flipkart.basePage;


import java.io.File;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.flipkart.pageObjects.SearchOnHomePage;
import com.flipkart.utilities.ConfigReader;
import com.flipkart.utilities.DriverReader;
import com.flipkart.utilities.ExtentReport;
import com.flipkart.utilities.Log;
import com.flipkart.utilities.ScreenshotUtility;
import com.flipkart.utilities.TestDataReader;
import org.openqa.selenium.JavascriptExecutor;


public class TestBase 
{
	protected WebDriver driver;	
	public static ExtentReports report;
	public static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
	protected DriverReader dr = new DriverReader();
	private static final Logger logger = Log.getLogger(); // No need to pass the class
	
	   
	
	@BeforeSuite
	public void setUpReport()
	{
		report = ExtentReport.createInstance();
	}
	
	@AfterSuite
	public void tearDownReport()
	{
		report.flush();
	}
	
	@BeforeClass
	public void setUpDriver()
	{
		ConfigReader.initProperties();
		TestDataReader.initTestData();
		
		String browser = ConfigReader.getConfig("browser");
		//driver = DriverReader.initializeDriver(browser);
		driver = dr.initializeDriver(browser);
		logger.info("Initialized driver with browser driver : "+driver);
		
		driver.get(ConfigReader.getConfig("url"));		
		logger.info("Launching the url : " + ConfigReader.getConfig("url"));
		
		long waitTime = Long.parseLong(ConfigReader.getConfig("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
	}

//	@AfterMethod
//	public void takeSnapOnFailure(ITestResult result)
//	{
//		if(ITestResult.FAILURE == result.getStatus()) 
//		 {
//	            String testName = result.getName();
//	            String errorMsg = result.getThrowable().getMessage();
//	            ScreenshotUtility.takeScreenshot(driver, testName + "_" + clean(errorMsg));
//	     }	
//	}
	
	@AfterClass
	public void tearDownDriver()
	{		 
		//DriverReader.quitDriver();
		dr.quitDriver();
		logger.info("Execution done! Closed all Driver instances.");
	}
	
	private String clean(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "_").substring(0, Math.min(30, input.length()));
    }
	
	public void createTest(String testName)
	{
		ExtentTest test = report.createTest(testName);
		testThread.set(test);
	}
	
	public void testInfo(Status status, String message)
	{
		testThread.get().info(message);
	}
	
	public void testPass(Status status, String message)
	{
		testThread.get().pass(message);
	}
	
	public void testFail(Status status, String message)
	{
		testThread.get().fail(message);
	}
	
//	public void attachFailedTestScreenshot(String fileName)
//	{
//		try
//		{
//			TakesScreenshot screenShot = (TakesScreenshot) driver;
//			
//			File src = screenShot.getScreenshotAs(OutputType.FILE);
//			
//			String path = "test-output/Execution_Screenshots/" + fileName + ".png";
//			
//			FileUtils.copyFile(src, new File(path));
//			
//			testThread.get().addScreenCaptureFromPath(path);			
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
	
	protected void searchProduct(String product)
	{
		SearchOnHomePage search = new SearchOnHomePage(driver);
		search.searchProduct(product);
		
		logger.info("Searching product - " +product);
		testInfo(Status.INFO, "Searching product - "+product);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}
