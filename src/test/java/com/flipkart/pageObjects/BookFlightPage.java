package com.flipkart.pageObjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.flipkart.basePage.TestBase;
import com.flipkart.utilities.Log;

public class BookFlightPage extends TestBase
{
	private WebDriver driver;
	private static final Logger logger = Log.getLogger(); // No need to pass the class
	private By bookFlight = By.xpath("//span[contains(text(),'Flight Bookings')]");
	private By fromInputBox = By.xpath("//input[@name='0-departcity']");
	private By toInputBox = By.xpath("//input[@name='0-arrivalcity']");
	private String fromCity;
	private String toCity;	
	private By dateToBox = By.xpath("//input[@name='0-dateto']");
	private String month_Year;
	private String day;
	//private By clickNext = By.xpath("//div[@class='au1mSN']/button");
	private By search = By.xpath("//span[text()='SEARCH']");
	private String airline;
	private By book = By.xpath("//div[2]/div[2]/div/div/div[1]/div[3]");
		

	public BookFlightPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void enterFromCity()
	{
		//getFromInputBox().clear();
		getFromInputBox().sendKeys(fromCity);
		
		String suggestedFrom = "//div[@class='_98hP1j'][1]/div/div/span[text()='" + fromCity + "']";
		
		driver.findElement(By.xpath(suggestedFrom)).click();
		
		logger.info("Entering From City - " +fromCity);
		testInfo(Status.INFO, "Entering From City - "+fromCity);
		
	}

	public void enterToCity()
	{
		//getToInputBox().clear();
		getToInputBox().sendKeys(toCity);
		
		String suggestedTo = "//div[@class='_98hP1j'][1]/div/div/span[text()='" + toCity + "']";
		
		driver.findElement(By.xpath(suggestedTo)).click();
		
		logger.info("Entering To City - " +toCity);
		testInfo(Status.INFO, "Entering To City - "+toCity);		
	}	

	public void enterReturnDate() throws InterruptedException
	{
		getDateToBox().click();		
		
		String monthYearXpath = "//div[normalize-space()='" + month_Year + "'] ";
		WebElement monYearXpath = driver.findElement(By.xpath(monthYearXpath));
		monYearXpath.click();
		
		//driver.findElement(clickNext).click();
		
		String dateXpath = "//button[@class='pl8ttv' and normalize-space(text())='" + day + "']";
		WebElement date = driver.findElement(By.xpath(dateXpath));
		date.click();			
		Thread.sleep(1200);
		
		logger.info("Entering Return Date");
		testInfo(Status.INFO, "Entering Return Date");
	}
	
	public void clickSearch() throws InterruptedException
	{
		getSearch().click();
		Thread.sleep(1200);
		logger.info("Clicking Search button");
		testInfo(Status.INFO, "Clicking Search button");
	}
	
	public void selectAirline()
	{
		String airLineXpath = "//div[2]/div/div/div[1]/div[1]/div[1]/div[1]/div[2]/span[contains(text(),'" + airline +"')]";
		WebElement airLine = driver.findElement(By.xpath(airLineXpath));
		airLine.click();
		
		logger.info("Selecting Airline");
		testInfo(Status.INFO, "Selecting Airline");
	}
	
	public void bookFlightOnHomePage() throws InterruptedException
	{
		Thread.sleep(1200);
		getBookFlight().click();
		Thread.sleep(1200);
		logger.info("Clicking Flight Bookings on Home Page");
		testInfo(Status.INFO, "Clicking Flight Bookings on Home Page");
	}
	
	public void booking()
	{
		getBook().click();
		logger.info("Clicking on Book button");
		testInfo(Status.INFO, "Clicking on Book button");		
	}

	public WebElement getBook() {
		return driver.findElement(book);
	}

	public void setBook(By book) {
		this.book = book;
	}
	
	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	public WebElement getSearch() {
		return driver.findElement(search);
	}

	public void setSearch(By search) {
		this.search = search;
	}
	
	public String getMonth_Year() {
		return month_Year;
	}

	public void setMonth_Year(String month_Year) {
		this.month_Year = month_Year;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
//	public WebElement getDateFromBox() {
//		return driver.findElement(dateFromBox);
//	}
//
//	public void setDateFromBox(By dateFromBox) {
//		this.dateFromBox = dateFromBox;
//	}

	public WebElement getDateToBox() {
		return driver.findElement(dateToBox);
	}

	public void setDateToBox(By dateToBox) {
		this.dateToBox = dateToBox;
	}

	public WebElement getFromInputBox() {
		return driver.findElement(fromInputBox);
	}

	public void setFromInputBox(By fromInputBox) {
		this.fromInputBox = fromInputBox;
	}

	public WebElement getToInputBox() {
		return driver.findElement(toInputBox);
	}

	public void setToInputBox(By toInputBox) {
		this.toInputBox = toInputBox;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public WebElement getBookFlight() {
		return driver.findElement(bookFlight);
	}

	public void setBookFlight(By bookFlight) {
		this.bookFlight = bookFlight;
	}
}
