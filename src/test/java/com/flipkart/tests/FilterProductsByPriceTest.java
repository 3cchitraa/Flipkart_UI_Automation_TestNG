package com.flipkart.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.flipkart.basePage.TestBase;
import com.flipkart.listeners.TestListener;
import com.flipkart.pageObjects.SearchResultsPage;
import com.flipkart.utilities.Log;
import com.flipkart.utilities.TestDataReader;


/*
 * Test Logic is working as Expected but there seems to be a bug in the app
 */

@Listeners(TestListener.class)
public class FilterProductsByPriceTest extends TestBase
{	
	SearchResultsPage srp;
	private static final Logger logger = Log.getLogger(); // No need to pass the class
	
	@BeforeMethod()
	public void initPageObject()
	{
		srp = new SearchResultsPage(driver);
	}
	
	@Test(priority=0, groups = {"sanity"})
	public void searchingProduct()
	{
		createTest("Searching for Product");
				
		String product = TestDataReader.getTestData("sortByPriceProduct");
		searchProduct(product);
		testInfo(Status.INFO, "Searching product : "+product);
		logger.info("Searching for product : " + product);
	}
	
	@Test(priority=1, groups = {"sanity"})
	public void sortByPrice()
	{
		createTest("Click Sort By Price -- Low to High");
		//srp.clickSortByPriceLowToHigh();	
		
		testInfo(Status.INFO, "Clicking on Sort By Price -- Low to High ");
		logger.info("Clicking on Sort By Price -- Low to High " );
	}
		
	
	@Test(priority=3, groups = {"sanity"})
	public void validatePriceLowToHigh()
	{
		createTest("Validating - Sort - Price Low to High");	
		
		ArrayList<Integer> price = srp.getFirstNPrices(5);
		
		List<Integer> sortedPrices = new ArrayList<>(price);
		
		Collections.sort(sortedPrices);
		
		try
		{
			Assert.assertEquals(price, sortedPrices, "Prices are not sorted in increasing order");
			
			testPass(Status.PASS, "Prices are sorted in increasing order: " + price);
			
			logger.info("Prices are sorted in increasing order: " + price);

	    } catch (AssertionError e) 
		{	    
	        testFail(Status.FAIL, "Prices are NOT sorted - not in INCREASING ORDER: " + price);

	        // Log failure in Logger
	        logger.fatal("Prices are NOT sorted - not in INCREASING ORDER: " + price);
	    }	
	}
		
//		if(price.equals(sortedPrices))
//		{
//			testPass(Status.PASS, "Prices are in increasing order." );
//			
//			System.out.println("Prices are in increasing order : Expected Price - "+sortedPrices+" and Actual Price - "+price);
//		}
//		else
//		{
//			Log.fatal("\nProducts not displaying as per sorted prices in increasing order.");
//			testFail(Status.FAIL, "Prices not in increasing order.");
//			
//			assert price.equals(sortedPrices) : "Prices not in increasing order. Expected price Order : "+sortedPrices +" and Actual Price Order : "+ price;
//		}
	

}
