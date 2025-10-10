package com.flipkart.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.flipkart.basePage.TestBase;
import com.flipkart.pageObjects.CloseLogin;
import com.flipkart.pageObjects.SearchResultsPage;
import com.flipkart.utilities.ConfigReader;
import com.flipkart.utilities.DataProviderUtil;
import com.flipkart.utilities.Log;
import com.flipkart.utilities.TestDataReader;

public class SearchProductAndValidateTest extends TestBase 
{	
	
	@Test(dataProvider="searchItemData", dataProviderClass=DataProviderUtil.class, groups = {"regression"})
	public void searchProductAndValidateTop3Titles(String itemName) throws InterruptedException
	{
		createTest("Validate Search Product");	
	    
		testInfo(Status.INFO ,"Searched product name : "+ itemName);			
		
		 try 
		 {			
			 // Close login popup if displayed
			 CloseLogin cl = new CloseLogin(driver); 
	         cl.closeLoginFormOnHomePage();
	     }
		 catch (Exception ignored) {}
		
		SearchResultsPage srp = new SearchResultsPage(driver);
		
		searchProduct(itemName);		
		
		List<WebElement> top3Products = srp.getFirstThreeProductElements(); 
		testInfo(Status.INFO, "Top 3 products fetched");
		
		boolean result = srp.verifyTitlesContainSearchedProduct(top3Products, itemName);
		
		if(!result)
		{
			testFail(Status.FAIL, "One or more products did not match the searched term.");
			Log.fatal("One or more products did not match the searched term.");
			
			//attachFailedTestScreenshot("Product titles not as per the search.");
			
			Assert.fail("One or more product title(s) don't have searched product term.");			
		}
		else
		{
			testPass(Status.PASS, "All top 3 product titles contain the searched term.");
		}	
		
		Thread.sleep(2000);
	}
}
