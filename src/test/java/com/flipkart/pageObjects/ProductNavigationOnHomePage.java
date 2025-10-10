package com.flipkart.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import com.flipkart.basePage.TestBase;
import com.flipkart.utilities.ActionsUtils;
import com.flipkart.utilities.WaitUtils;

public class ProductNavigationOnHomePage extends TestBase
{
	private WebDriver driver;
    private ActionsUtils actionsUtils;
	private By homeFurniture = By.xpath("//span[text()='Home & Furniture']");
	private By kitchenDining = By.xpath("//a[text()='Kitchen & Dining']");
	private By kitchenTools = By.xpath("//a[text()='Kitchen tools']");
	private By onlyFew = By.xpath("//div[contains(text(),\"left\")]/ancestor::div[@class='slAVV4']/a[2]");
	
	
	//private List<WebElement> onlyFew = (List<WebElement>) By.xpath("//div[contains(text(), 'left')]"); 
	
	public ProductNavigationOnHomePage(WebDriver webDriver)
	{
		this.driver = webDriver;		
		actionsUtils = new ActionsUtils(driver);
	}
	
	public void hoverMenu_SubMenu(int timeoutSeconds) throws InterruptedException
	{
		Thread.sleep(1200);
		actionsUtils.moveToElementUsingLocator(homeFurniture);	
		System.out.println("Hovered on Main Menu : "+getHomeFurniture().getText());
		
		System.out.println("Hovered on below Sub Menus : ");
		
		WaitUtils.waitForVisibilityOfWebElement(driver, getKitchenDining(), timeoutSeconds);
		System.out.println(getKitchenDining().getText());
		actionsUtils.moveToElementUsingElement(getKitchenDining());		
		
		Thread.sleep(1200);
		WaitUtils.waitForVisibilityOfWebElement(driver, getKitchenTools(), timeoutSeconds);
		System.out.println(getKitchenTools().getText());
		getKitchenTools().click();
		
	}
	
	public List<WebElement> fetchFewLeftProducts()
	{
		return driver.findElements(onlyFew);
	}

	public void printFewLeftProducts()
	{
		List<WebElement> fewLeft = fetchFewLeftProducts();
		System.out.println("Total no. of \"Only few left\" product(s) is : "+fewLeft.size()+" and are listed below : \n");
		
		for(WebElement few : fewLeft)
		{			
			if(!few.getText().isEmpty())
			{ 
				testPass(Status.PASS, " "+few.getText());    //prints on the Extent Report
				System.out.println(few.getText());          // prints on the console
			}	 
			else
			{
				testFail(Status.FAIL, "List is empty");
			}
		}
	}
	
	public WebElement getHomeFurniture() {
		return driver.findElement(homeFurniture);
	}

	public void setHomeFurniture(By homeFurniture) {
		this.homeFurniture = homeFurniture;
	}

	public WebElement getKitchenDining() {
		return driver.findElement(kitchenDining);
	}

	public void setKitchenDining(By kitchenDining) {
		this.kitchenDining = kitchenDining;
	}

	public WebElement getKitchenTools() {
		return driver.findElement(kitchenTools);
	}

	public void setKitchenTools(By kitchenTools) {
		this.kitchenTools = kitchenTools;
	}

	public List<WebElement> getOnlyFew() {
		return driver.findElements(onlyFew);
	}

	public void setOnlyFew(By onlyFew) {
		this.onlyFew = onlyFew;
	}	
	
}
