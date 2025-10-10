package com.flipkart.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchOnHomePage 
{
	private WebDriver driver;
	private By searchBar = By.cssSelector("input[placeholder='Search for Products, Brands and More']");
	
	public SearchOnHomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void searchProduct(String item)
	{
		driver.findElement(searchBar).sendKeys(item,Keys.ENTER);
	}
	
	
}
