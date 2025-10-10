package com.flipkart.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.flipkart.basePage.TestBase;
import com.flipkart.pageObjects.BookFlightPage;
import com.flipkart.utilities.TestDataReader;


public class FlightBookingTest extends TestBase
{
	BookFlightPage bfp;
	
	@BeforeMethod
	public void initPageObject()
	{
		bfp = new BookFlightPage(driver);
	}
	
	 @Test(priority=0, groups = {"regression"})
	 public void clickBookFlight() throws InterruptedException
	 {
		 createTest("Clicking on Flight Bookings");
		 
		 bfp.bookFlightOnHomePage();
		 testInfo(Status.INFO, "Clicked on Flight Bookings on home page");
		 
		 //Thread.sleep(2000);
	 }
	 
	 @Test(priority=1, groups = {"regression"})
	 public void enterFromAndToCity()
	 {
		 createTest("Enter From City and To City");
		 //String cityFrom = TestDataReader.getTestData("flightFrom");
		 
		 bfp.setFromCity(TestDataReader.getTestData("flightFrom"));
		 bfp.setToCity(TestDataReader.getTestData("flightTo"));
		 
		 bfp.enterFromCity();
		 bfp.enterToCity();		
		 
		 testInfo(Status.INFO, "Enter From & To Cities, successfully. From City : "+bfp.getFromCity()+" and To City : "+bfp.getToCity());
	 }
	 
	 @Test(priority=2, groups = {"regression"})
	 public void enterReturnDate() throws InterruptedException 
	 {
		 createTest("Enter Return Date");
		 
		 bfp.setMonth_Year(TestDataReader.getTestData("monthYear"));
		 bfp.setDay(TestDataReader.getTestData("day"));
		 
		 bfp.enterReturnDate();		 
		 testInfo(Status.INFO, "Enter Return Date");
	 }
	 
	 @Test(priority=3, groups = {"regression"})
	 public void clickOnSearch() throws InterruptedException
	 {
		 bfp.clickSearch();
		 testInfo(Status.INFO, "Click on Search");
	 }
	 
	 @Test(priority=4, groups = {"regression"})
	 public void clickOnBook() throws InterruptedException
	 {
		 Thread.sleep(1200);
		 bfp.booking();
		 Thread.sleep(1200);
		 testInfo(Status.INFO, "Click on Book button");
	 }
}
