package com.flipkart.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader 
{
	private static Properties config;	
	
	public static void initProperties()
	{	
		config = new Properties();
		
		try
		{			
			FileInputStream configFis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\config.properties");
			config.load(configFis);					
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}	
	}
	
	public static String getConfig(String propName)
	{
		return config.getProperty(propName);		
	}
	
}
