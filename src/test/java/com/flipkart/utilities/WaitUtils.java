package com.flipkart.utilities;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils 
{
	public static List<WebElement> waitForAllElementsToBeVisible(WebDriver driver, By locator, int timeoutInSeconds)
	{
		try
		{
			WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		catch(Exception e)
		{
			//System.out.println("All elements not visible within timeout - "+timeoutInSeconds +" seconds.");
			e.printStackTrace();
			return Collections.emptyList();
		}	
	}
	
	public static WebElement waitForVisibilityOfWebElement(WebDriver driver, WebElement element, int timeoutInSeconds)
	{
		try
		{
			WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			
			return wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch(TimeoutException e)
		{
			System.err.println("Timeout!! Element "+element+" not visible within mentioned timeout - "+timeoutInSeconds+" seconds");							
		}
		return null;
	}
	
	
}
