package com.flipkart.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtils
{
	private WebDriver driver;
	private Actions actions;
	
	
	  //Constructor to initialize - driver and actions object
	public ActionsUtils(WebDriver webDriver)
	{
		this.driver = webDriver;
		this.actions = new Actions(driver);
	}
	
     // Move the mouse cursor to the specified - WebElement (hover over)
     public void moveToElementUsingElement(WebElement element) 
     {
    	 actions.moveToElement(element).perform();;
     }
     
  // Move the mouse cursor using the locator
     public void moveToElementUsingLocator(By locator) 
     {
    	 WebElement element = driver.findElement(locator);
    	 moveToElementUsingElement(element);
     }
     
     
	
	
}
