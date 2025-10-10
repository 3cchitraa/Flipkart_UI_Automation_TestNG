package com.flipkart.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerifyProductTitleNewPage
{
	private WebDriver driver;
	private String clickItem;

	
	public VerifyProductTitleNewPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickOnProduct()
	{		
		String product = "//a[text()='" + clickItem + "']"; 
		driver.findElement(By.xpath(product)).click();
	}
	
	public String getClickItem() {
		return clickItem;
	}

	public void setClickItem(String clickItem) {
		this.clickItem = clickItem;
	}	
	
}
