package com.flipkart.pageObjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.flipkart.utilities.Log;

public class CloseLogin 
{
	private WebDriver driver;
	private static final Logger logger = Log.getLogger(); // No need to pass the class
	private By crossMark = By.cssSelector("span._30XB9F");
	
	public CloseLogin(WebDriver webDriver)
	{
		this.driver = webDriver;
	}
	
	public void closeLoginFormOnHomePage()
	{
		try
		{
			if(getCrossMark().isDisplayed())
			{
				getCrossMark().click();
			}
		}catch(Exception ignored) {}
	}

	public WebElement getCrossMark() {
		return driver.findElement(crossMark);
	}

	public void setCrossMark(By crossMark) {
		this.crossMark = crossMark;
	}
}

