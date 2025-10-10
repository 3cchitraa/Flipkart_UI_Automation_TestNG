package com.flipkart.pageObjects;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import com.flipkart.basePage.TestBase;
import com.flipkart.utilities.Log;
import com.flipkart.utilities.WaitUtils;

public class SearchResultsPage extends TestBase
{
	private WebDriver driver;
	private By searchBar = By.cssSelector("input[placeholder='Search for Products, Brands and More']");  //Search bar
	private static final Logger logger = Log.getLogger(); // No need to pass the class
	//Locating the Product Name after performing Product Search
	private By searchedProductTitles = By.cssSelector("div .DOjaWF > div .KzDlHZ");  
	private By lowToHigh = By.xpath("//div[contains(text(),'Low to High')]");
	private By allPrices = By.cssSelector("div .DOjaWF > div .Nx9bqj");
	
	
	public SearchResultsPage(WebDriver driver)
	{
		this.driver = driver;
	}	
	
	//to get first 3 searched products
	public List<WebElement> getFirstThreeProductElements()
	{
		WaitUtils.waitForAllElementsToBeVisible(driver, searchedProductTitles, 10);		
		
		List<WebElement> allTitles = driver.findElements(searchedProductTitles);
		
		List<WebElement> firstThree = new ArrayList<>();
		
		for(int i = 0 ; i < Math.min(3, allTitles.size()) ; i++)
		{
			if(!allTitles.isEmpty())
			{
				firstThree.add(allTitles.get(i));
				//System.out.println(firstThree.get(i).getText());
				testPass(Status.PASS, "Top 3 product's titles fetched \n"+firstThree.get(i).getText());
			}
			else
			{
				testFail(Status.FAIL, "Error! Product title's List is Empty.");
			}
			
		}
		
		return firstThree;
	}
	
	//to verify the title of the first 3 searched results
	public boolean verifyTitlesContainSearchedProduct(List<WebElement> ProductList, String expectedTitle)
	{
	//	List<WebElement> results = getFirstThreeProductElements();
		
		for(WebElement list : ProductList)
		{
			String actualTitle = list.getText().toLowerCase();
			
			if(!actualTitle.contains(expectedTitle.toLowerCase()))
			{
				return false;   // to fail fast - if condition not met 
			}			
		}
		return true;
	}
	 
	public WebElement getLowToHigh() {
		return driver.findElement(lowToHigh);
	}

	public void setLowToHigh(By lowToHigh) {
		this.lowToHigh = lowToHigh;
	}
	
	//To click on Sort products by : Price -- Low to High
//	public void clickSortByPriceLowToHigh()
//	{
//		getLowToHigh().click();
//	}

	public ArrayList<Integer> getFirstNPrices(int n)
	{
		getLowToHigh().click();
		WaitUtils.waitForAllElementsToBeVisible(driver, searchedProductTitles, 10);
		
		List<WebElement> prices = driver.findElements(allPrices);
		
		ArrayList<Integer> firstFivePrices = new ArrayList<>();
		
		System.out.println("Top "+n+" item's prices fetched \n");
		
		for (WebElement price : prices) 
		{
		    String priceTag = price.getText().replaceAll("[^0-9]", "");
		    //System.out.println(price.getText());
		    if (!priceTag.isEmpty()) 
		    {
		    	firstFivePrices.add(Integer.parseInt(priceTag));
		    }
		    else
			{
				testFail(Status.FAIL, "Error! Product's price List is Empty.");
				Log.fatal("Product's price list is empty.");
			}		    		  
		}	
		return firstFivePrices;
	}
}
//		for(int i = 0 ; i < n; i++)
//		{
//			String priceTag = prices.get(i).getText().replaceAll("[^0-9]", "");
//			//System.out.println("Top "+n+" item's prices fetched \n");
//			
//			if(!priceTag.isEmpty())
//			{
//				firstFivePrices.add(Integer.parseInt(priceTag));
//				
//				testPass(Status.PASS, ""+firstFivePrices.get(i).toString());
//				System.out.println(firstFivePrices);							
//			}		
//			else
//			{
//				testFail(Status.FAIL, "Error! Product's price List is Empty.");
//			}
//		}
		
	