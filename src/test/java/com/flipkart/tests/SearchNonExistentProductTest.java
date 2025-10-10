package com.flipkart.tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.flipkart.basePage.TestBase;
import com.flipkart.pageObjects.NoResultsPage;
import com.flipkart.utilities.DataProviderUtil;
import com.flipkart.utilities.Log;
import com.flipkart.utilities.TestDataReader;

public class SearchNonExistentProductTest extends TestBase
{
	private static final Logger logger = Log.getLogger(); // No need to pass the class
	
	@Test(priority=0, dataProvider = "excelTestData", dataProviderClass = DataProviderUtil.class, groups = {"regression"})
	public void searchNonExistentProduct(String invalidItem)
	{
		createTest("Searching non existsent product.");
		
//		String item = TestDataReader.getTestData("nonExistentProduct");		
//		searchProduct(item);
		
		searchProduct(invalidItem);
		testInfo(Status.INFO, "Searched non-existent product: " + invalidItem);
	}
	
	@Test(priority=1, groups = {"regression"})
	public void validateSearchResultMessage()
	{
		createTest("Validating No Results Message");
		
		NoResultsPage nrp = new NoResultsPage(driver);
		
		String message = nrp.verifyNoResults();
		
		if(!message.equals(TestDataReader.getTestData("noResultsMessage")))
		{
			testFail(Status.FAIL, "Non-Existent Product search message not fetched - "+message);
			logger.fatal("Non-Existent Product search message not fetched - "+message);
			
			Assert.assertEquals(message, TestDataReader.getTestData("noResultsMessage"), "Non-Existent Product search message doesnt 'match.");
		}
		else
		{
			testPass(Status.PASS, "Non-Existent Product search made");
			logger.info("Non-Existent Product search made. Result Message : "+ message);
		
		}
		
	}
}
