package com.flipkart.pageObjects;

import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import com.flipkart.basePage.TestBase;
import com.flipkart.utilities.Log;

public class AddToCartPage extends TestBase
{
	private WebDriver driver;
	private String clickOnItem;
	private By addToCart = By.xpath("//button[text()='Add to cart']");
	private static final Logger logger = Log.getLogger(); // No need to pass the class
	
	public AddToCartPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickOnProduct()
	{		
		String product = "//a[text()='" + clickOnItem + "']"; 
		WebElement item = driver.findElement(By.xpath(product));
		item.click();
		
		logger.info("Clicking on searched product - " +item.getText());
		testInfo(Status.INFO, "Clicking on searched product - "+item.getText());
	}
	
	public void add_Cart() throws InterruptedException
	{
		getAddToCart().click();
		Thread.sleep(2000);
		logger.info("Added product to the cart.");
		testInfo(Status.INFO, "Added product to the cart.");
	}
	
	@SuppressWarnings("deprecation")
	public String cartCount() throws InterruptedException
	{
		WebElement CartCount = driver.findElement(By.xpath("//input[@class='p6sArZ']"));
		Thread.sleep(2000);
		
		String count = CartCount.getAttribute("value");
		
		//logger.info("Cart Count = "+count);
		//testInfo(Status.INFO, "Cart Count = "+count);
		
		return count;
		
	}	
	

	public WebElement getAddToCart() {
		return driver.findElement(addToCart);
	}

	public void setAddToCart(By addToCart) {
		this.addToCart = addToCart;
	}

	public String getClickItem() {
		return clickOnItem;
	}

	public void setClickItem(String clickItem) {
		this.clickOnItem = clickItem;
	}	
	
}
