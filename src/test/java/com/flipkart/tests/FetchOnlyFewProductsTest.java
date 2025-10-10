package com.flipkart.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.flipkart.basePage.TestBase;
import com.flipkart.pageObjects.ProductNavigationOnHomePage;

public class FetchOnlyFewProductsTest extends TestBase
{
	private ProductNavigationOnHomePage pnp;
	
	@BeforeMethod()
	public void initPageObject()
	{
		pnp = new ProductNavigationOnHomePage(driver);
	}
	
	@Test(priority=0, groups = {"smoke", "sanity"})
	public void navigateToFewLeftProducts() throws InterruptedException
	{	
		createTest("Navigating via Menu, Sub Menu to Only Few Left Products Test");
		
		pnp.hoverMenu_SubMenu(10);
		testInfo(Status.INFO, "Navigated through Menu - Sub Menu from Home Page");
	}
	
	@Test(priority=1, groups = {"smoke", "sanity"})
	public void fetchOnlyFewLeftProducts()
	{
		createTest("Fetching & Printing \"Only Few Left\" Products");
				
		pnp.printFewLeftProducts();
	}
	
	
}
