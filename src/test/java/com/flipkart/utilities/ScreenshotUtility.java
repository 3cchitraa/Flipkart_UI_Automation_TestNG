package com.flipkart.utilities;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility
{
	public static void takeScreenshot(WebDriver driver, String fileName)
	{
		try
		{
			TakesScreenshot sreenshot = (TakesScreenshot) driver;
			File src = sreenshot.getScreenshotAs(OutputType.FILE);
		
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		
			File dest = new File("snapsnhots_failures/" + fileName + "_"+ timestamp + ".png");
			
			//dest.getParentFile().mkdirs();
			
			Files.copy(src.toPath(), dest.toPath());
		}
		catch (Exception e) 
		{
        e.printStackTrace();
		}
	}
}


