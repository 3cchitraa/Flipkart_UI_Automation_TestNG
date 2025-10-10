package com.flipkart.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport
{
	private static ExtentReports extentReport;
	private static ExtentSparkReporter spark;
	private static String reportPath;
	
	public static ExtentReports createInstance()
	{
		if(extentReport == null)
		{
			ExtentSparkReporter  sparkReporter = new ExtentSparkReporter("./test-output/ExtentReport.html");			
			
			//sparkReporter.config().setDocumentTitle("Extent Automation Report");
			//sparkReporter.config().setReportName("Test Execution Summary");
			sparkReporter.config().setTheme(Theme.STANDARD);
			
			extentReport = new ExtentReports();
			
			extentReport.attachReporter(sparkReporter);						
		}
		return extentReport;
	}
}
