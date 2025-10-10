package com.flipkart.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverReader 
{
	protected WebDriver driver;
	
	public  WebDriver initializeDriver(String browserName)
	{		
		if(browserName.toLowerCase().equals("chrome"))
		{
			//set up Chrome driver
			WebDriverManager.chromedriver().setup();  
			//initialize WebDriver
			try {
				driver = new ChromeDriver();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(browserName.toLowerCase().equals("firefox"))
		{
			//set up Firefox driver
			WebDriverManager.firefoxdriver().setup();  
			//initialize WebDriver
			driver = new FirefoxDriver();
		}
		else if(browserName.toLowerCase().equals("edge"))
		{
			//set up Edge driver
			WebDriverManager.edgedriver().setup();  
			//initialize WebDriver
			driver = new EdgeDriver();
		}
		else
		{
			throw new IllegalArgumentException("Not a supported browser "+ browserName);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getConfig("implicitWait"))));
		return driver;
	}
	public WebDriver getDriver()
	{
		return driver;
	}
	
	public void quitDriver()
	{
		if(driver!=null)
		{
			driver.quit();
		}
		
	}
}
