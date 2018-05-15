package com.avactis.test.integration.adminpageobjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.avactis.test.integration.storepageobjects.Browser;
import com.avactis.test.integration.utility.*;

public class AdminSignInPage extends LoadableComponent<AdminSignInPage>
{
	private String adminPageTitle = ConfigProperties.getProperty("ADMIN_TITLE");;
	//private String url = ConfigProperties.getProperty("ADMIN_URL");
	private String url = "http://avactis.enm2fzis5n.us-east-2.elasticbeanstalk.com/avactis-system/admin/signin.php";

	@FindBy (xpath = "//input[@name='AdminEmail'][@type='text']")
	public WebElement adminUserNameInputBox;
	
	@FindBy (xpath = "//input[@name='Password'][@type='password']")
	public WebElement adminPasswordInputBox;
	
	@FindBy (xpath = "//button[@type='submit']")
	public WebElement adminLogInButton;
	
	public AdminSignInPage()
	{
		PageFactory.initElements(Browser.getEventDriver(), this);	
		Browser.open(url);
	}

	@Override
	protected void isLoaded() throws Error 
	{
		assertTrue(Browser.getEventDriver().getTitle().equalsIgnoreCase(adminPageTitle));		
	}

	@Override
	protected void load() 
	{
		Browser.open(url);
	}
	
	public void close() {
        Browser.close();
    }
	
	public AdminHomePage doSignIn()
	{
		adminUserNameInputBox.clear();
		adminUserNameInputBox.sendKeys(ConfigProperties.getProperty("ADMIN_USER_NAME"));
		adminPasswordInputBox.sendKeys(ConfigProperties.getProperty("ADMIN_PASSWORD"));
		adminLogInButton.click();
		AdminHomePage adminHomePage = new AdminHomePage();
		return adminHomePage;
	}
	
	public boolean SearchOrder()
	{
		AdminHomePage adminHomePage = doSignIn();
		String orderNo = ConfigProperties.getProperty("ORDER_ID");
		Log.info("Fetched the order no. from previous test. "+orderNo);
		Boolean result = adminHomePage.lookupOrder(orderNo);
		return result;
		
	}
	
	
}
