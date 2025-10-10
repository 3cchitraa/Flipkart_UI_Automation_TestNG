package com.flipkart.pageObjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.flipkart.basePage.TestBase;
import com.flipkart.utilities.Log;

public class NoResultsPage extends TestBase
{
	private WebDriver driver;
	private static final Logger logger = Log.getLogger(); // No need to pass the class
	private By noResult = By.xpath("//div[contains(text(),'Sorry, no results found!')]");
			
	public NoResultsPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String verifyNoResults()
	{
		WebElement noResultMsg = driver.findElement(noResult);
		
		String msg = noResultMsg.getText();
				
		logger.info("Message after searching non-existent product - " +noResultMsg.getText());
		testInfo(Status.INFO, "Message while searching non-existent product- "+noResultMsg.getText());
		
		return msg;
		
	}
	
}
