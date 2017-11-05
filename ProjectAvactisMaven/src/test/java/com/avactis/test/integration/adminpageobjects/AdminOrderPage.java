package com.avactis.test.integration.adminpageobjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.avactis.test.integration.storepageobjects.Browser;
import com.avactis.test.integration.utility.*;

public class AdminOrderPage extends LoadableComponent<AdminOrderPage>
{
	@FindBy (xpath = "//input[@name='order_id']")
	public WebElement orderIDSearchBox;
	
	@FindBy (xpath = "//a[@class='btn btn-default input-sm']")
	public WebElement orderIDSearchButton;
	
	@FindBy (xpath = "//a[@title='Order Info']")
	public WebElement searchedOrder;
	
	@Override
	protected void isLoaded() throws Error 
	{
		assertTrue(Browser.getEventDriver().getTitle().equals("Orders - "+ConfigProperties.getProperty("ADMIN_TITLE")));
		
	}

	@Override
	protected void load() 
	{
		// TODO Auto-generated method stub
		
	}
	
	public AdminOrderPage()
	{
		PageFactory.initElements(Browser.getEventDriver(), this);
	}
	
	public boolean verifyOrder(String orderID)
	{
		orderIDSearchBox.sendKeys(orderID);
		orderIDSearchButton.click();

		if(searchedOrder.getText().equals(orderID))
		{
			Log.info("Order found & matched");
			return true;
		}
		return false;
		
	}

}
