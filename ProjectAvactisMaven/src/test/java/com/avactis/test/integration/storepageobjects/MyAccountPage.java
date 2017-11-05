package com.avactis.test.integration.storepageobjects;

import static org.testng.AssertJUnit.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.avactis.test.integration.utility.Log;

public class MyAccountPage extends LoadableComponent<MyAccountPage>
{
	private static WebDriver driver;
	private String title = "Avactis Demo Store";
	private com.avactis.test.integration.utility.Waiting wait;
	
	@FindBy (xpath = "//*[@name='order_id']")
	WebElement searchOrderTextBox;
	
	@FindBy (xpath = "//*[@class='col-lg-1']/*[@class='en btn blue button_order_search input_submit']")
	WebElement searchOrderButton;
	
	@FindBy (xpath = "//*[@title='Order Info']")
	WebElement clickOnSearchedOrder;
	
	public MyAccountPage(WebDriver driver) 
	{
		 PageFactory.initElements(driver, this);
//		 Log.info("My Account page is launched");
	}
	
	
	public void searchByOrderID(String orderId)
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Log.info("Searching for order no.: "+orderId);
		searchOrderTextBox.sendKeys(orderId);
		searchOrderButton.click();
		  try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  clickOnSearchedOrder.click();
		  Log.info("Searched order found. Checking for the items ... ");
	}	
	
	
	@Override
	protected void isLoaded() throws Error 
	{
		assertEquals("Acvatis Demo Store page not loaded properly", title,  driver.getTitle());	
		
	}

	@Override
	protected void load() 
	{
		
		
	}

}
