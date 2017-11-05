package com.avactis.test.integration.adminpageobjects;

import static org.testng.Assert.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.avactis.test.integration.storepageobjects.Browser;
import com.avactis.test.integration.utility.Waiting;

public class AdminHomePage extends LoadableComponent<AdminHomePage> 
{
	private static String adminHomePagetitle = "Home - Avactis Shopping Cart";

	@FindBy (xpath = "//a[@href='orders.php']/parent::li[@id='menu-orders']")
	WebElement orderMenu;
	
	@Override
	protected void isLoaded() throws Error 
	{
//	 assertTrue(Browser.getDriver().getTitle().equalsIgnoreCase(adminHomePagetitle));
		
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	public AdminHomePage()
	{
		PageFactory.initElements(Browser.getDriver(),this);
	}
	
	public Boolean lookupOrder(String getOrder)
	{
		Waiting.waitForElementPresent(orderMenu, 50);
		orderMenu.click();
		AdminOrderPage adminOrderPage = new AdminOrderPage();
		return adminOrderPage.verifyOrder(getOrder);
		
	}

}
