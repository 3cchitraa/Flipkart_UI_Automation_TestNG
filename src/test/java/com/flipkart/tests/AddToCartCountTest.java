package com.flipkart.tests;


import java.util.Set;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.flipkart.basePage.TestBase;
import com.flipkart.pageObjects.AddToCartPage;
import com.flipkart.utilities.Log;
import com.flipkart.utilities.TestDataReader;

public class AddToCartCountTest extends TestBase
{
	private AddToCartPage acp;
	private static final Logger logger = Log.getLogger(); // No need to pass the class
	
	@BeforeMethod
	public void initPageObject()
	{
		acp = new AddToCartPage(driver);
	}
	
	@Test(priority=0, groups = {"smoke", "regression"})
	public void searchProduct()
	{				
		createTest("Searching Product");
						
		String product = TestDataReader.getTestData("searchToAdd");
		
		testInfo(Status.INFO ,"Searched product name : "+ product);
		
		searchProduct(product);		
		
	}
	
	@Test(priority=1, groups = {"smoke", "regression"})
	public void clickOnSearchedProduct() throws InterruptedException
	{
		createTest("Clicking on Product");
		
		//String item = TestDataReader.getTestData("clickOnItem");				
		acp.setClickItem(TestDataReader.getTestData("clickToAdd"));  
		
		acp.clickOnProduct();
		
		Thread.sleep(2000);
	}
	
	@Test(priority=2, groups = {"smoke", "regression"})
	public void addToCart() throws InterruptedException
	{
		createTest("Adding product to Cart");
	
		Set<String> WindowHandles =driver.getWindowHandles();
		
		String originalWindow = driver.getWindowHandle();
		
		for(String handle : WindowHandles)
		{
			if(!handle.equals(originalWindow)) 
			{
				driver.switchTo().window(handle);
				
				logger.info("Switched to Product Details page");
				testPass(Status.PASS, "Switched to Product Details page");
				break;
			}
		}
		
		acp.add_Cart();
		
		String cc = acp.cartCount();
		
		if(cc.equals("1"))
		{
			Assert.assertTrue(cc.equals("1"), "Expected value '1' not found in the input field");
			testPass(Status.PASS, "Cart Count is 1.");	
			Log.info("Cart count validation passed");
		}
		else
		{
			testFail(Status.FAIL, "Cart Count is not 1");
			Log.fatal("Cart count validation failed! Expected 1 but found: " + cc);
		}
		
		System.out.println("Cart Count"+cc);
		Log.info("===== Finished addToCart test =====");
	}
}
