package com.flipkart.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataReader 
{
	private static Properties testData;
	
	public static void initTestData()
	{			
		testData = new Properties();
		
		try
		{						
			FileInputStream testFis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testdata.properties");
			testData.load(testFis);						
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}	
	}
	
	public static String getTestData(String propName)
	{
		return testData.getProperty(propName);	
	}
}
