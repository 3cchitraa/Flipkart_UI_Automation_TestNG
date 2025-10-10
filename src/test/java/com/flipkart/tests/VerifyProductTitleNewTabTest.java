package com.flipkart.tests;

import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.flipkart.basePage.TestBase;
import com.flipkart.pageObjects.SearchResultsPage;
import com.flipkart.pageObjects.VerifyProductTitleNewPage;
import com.flipkart.utilities.TestDataReader;

public class VerifyProductTitleNewTabTest extends TestBase
{
	private VerifyProductTitleNewPage vpt;	
	
	@BeforeMethod
	public void initPageObject()
	{
		vpt = new VerifyProductTitleNewPage(driver);
	}
	
	@Test(priority=0, groups = {"regression, sanity"})
	public void searchProduct()
	{
		createTest("Searching Product");
						
		String product = TestDataReader.getTestData("search_product");
		testInfo(Status.INFO ,"Searched product name : "+ product);
		
		searchProduct(product);				
	}
	
	@Test(priority=1, groups = {"regression, sanity"})
	public void clickOnSearchedProduct() throws InterruptedException
	{
		createTest("Clicking on Product");
		
		//String item = TestDataReader.getTestData("clickOnItem");				
		vpt.setClickItem(TestDataReader.getTestData("clickOnItem"));
		
		vpt.clickOnProduct();
		
		testInfo(Status.INFO ,"Clicked on product with name : "+ TestDataReader.getTestData("clickOnItem"));	
		Thread.sleep(2200);
	}
	
	@Test(priority=2, groups = {"regression, sanity"})
	public void verifyNewTabTitle()
	{
		createTest("Verifying Title of Product on New Tab");
	
		Set<String> WindowHandles =driver.getWindowHandles();
		
		String originalWindow = driver.getWindowHandle();
		
		for(String handle : WindowHandles)
		{
			if(!handle.equals(originalWindow)) 
			{
				driver.switchTo().window(handle);
				testPass(Status.PASS, "Switched to new opened TAB with Title "+ driver.getTitle());
				break;
			}
		}
		
		String actualT = driver.getTitle();
		String expectedT =  TestDataReader.getTestData("expectedTitle");
		System.out.println("Atual Title = "+ actualT +"\n Expected Title = "+ expectedT);
		
		if(actualT.equals(expectedT)) 
		{
			testPass(Status.PASS,"Actual & Expected Titles matched.");
		}
		else
		{
			testFail(Status.FAIL, "Actual & Expected Titles  do not match.");
		}
		
		Assert.assertEquals(actualT, expectedT, "Title doesn't match.");
		
	}
}
