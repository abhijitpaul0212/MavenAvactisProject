package com.avactis.test.integration.storepageobjects;

import static org.testng.AssertJUnit.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.avactis.test.integration.utility.*;



public class HomePage extends LoadableComponent<HomePage>
{
//	private String url = "http://avactis:avactis%40123@sandbox.avactis.com/ketan479/";
	private String url;
	private String title = ConfigProperties.getProperty("HOMEPAGE_TITLE");
//	private int explicitTimeOut = Integer.parseInt(utility.ConfigProperties.getProperty("EXPLICIT_WAIT"));
	
	
	@FindBy (xpath = "//button[contains(text(),'Register')]")
	public static WebElement goToRegistrationPage;
	
	@FindBy (css = "*[href*='ketan479/sign-in.php']")
	public WebElement signInLink;
	
	@FindBy (xpath ="//a[contains(@href,'sign-in.php?')]")	
	public WebElement signOutLink;
	
	@FindBy (css = "a[href='sign-in.php']")
	public WebElement myAccountLink;
	
	@FindBy (css = "*[href*='cart.php']")
	public WebElement cartLink;

	@FindBy (css = "*[href*='checkout.php']")
	public WebElement checkOutLink;
	
	public HomePage()
	{
		url = ConfigProperties.getProperty("STORE_URL");
		PageFactory.initElements(Browser.getDriver(), this);
//		Log.info("Store Home page browser is getting initialized");		
	}

	@Override
	protected void isLoaded() throws Error 
	{
		assertEquals("Acvatis Demo Store page not loaded properly",title,Browser.getDriver().getTitle());
		
	}

	@Override
	protected void load() 
	{
		Browser.open(url);		
//		Log.info("URL invoked: "+url);
	}
	
	public void close()
	{
		Browser.close();
	}
	
	public SignInPage goToSignInPage()
	{
		signInLink.click();
		SignInPage signInPage = new SignInPage();
		return signInPage;
		
	}
	
	 public boolean isUserLoggedIn()
		{			
			if ( Waiting.waitForElementPresent(signOutLink, 10))
		 	{		 	
		 		return true;
		 	}
		 	else
		 	{		 		
		 		return false;
		 	}
		}
	
	 public SignInPage doSignOut()
	 {
		 if (isUserLoggedIn())
		 {
			signOutLink.click();
			SignInPage signInPage = new SignInPage();
			return signInPage;
		 }
		 else
		 {
			 Log.info("Nobody is singed in so cannot signOut");
			 return null;
		 }
	 }
	
	public static void goToRegistrationPageUsingButton()
	{
		goToRegistrationPage.click();
		
	}
	
	public Products goToProductPageUsingMenuAndSubMenu(String mainMenuID, String subMenuID)
	{
			WebElement headerNavigation = Browser.getDriver().findElement(By.className("header-navigation"));
			Actions builder = new Actions(Browser.getDriver());
			String xpathForMainMenu = "//a[contains(@href,'" + mainMenuID + "')]";
//			Log.info("Xpath for main menu item - " + xpathForMainMenu);
		    
			WebElement mainMenu =headerNavigation.findElement(By.xpath(xpathForMainMenu));
            String xpathForSubMenu = "//a[contains(@href,'" + subMenuID + ".html')]";
//          Log.info("Xpath for Sub menu is - " + xpathForSubMenu);
			builder.moveToElement(mainMenu).build().perform();
			
			WebElement subMenu = headerNavigation.findElement(By.xpath(xpathForSubMenu));
			String subMenuText = subMenu.getText();
//			Log.info("Sub menu is "+subMenuText);
			builder.moveToElement(subMenu).build().perform();
			subMenu.click();
			Products products = new Products(subMenuText);
			return products;
		
		
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*if(Waiting.waitForElementPresent(Browser.getDriver().findElement(By.className("header-navigation")), 50))
		{
			WebElement headerNavigation = Browser.getDriver().findElement(By.className("header-navigation"));
			Actions builder = new Actions(Browser.getDriver());
			String xpathForMainMenu = "//a[contains(@href,'" + mainMenuID + "')]";
			Log.info("Xpath for main menu item - " + xpathForMainMenu);
		    WebElement mainMenu =headerNavigation.findElement(By.xpath(xpathForMainMenu));
            String xpathForSubMenu = "//a[contains(@href,'" + subMenuID + ".html')]";
            Log.info("Xpath for Sub menu is - " + xpathForSubMenu);
			builder.moveToElement(mainMenu).build().perform();
			if(Waiting.isElementPresentAndDisplay(Browser.getDriver(), By.xpath(xpathForSubMenu)))
			{
				WebElement subMenu = headerNavigation.findElement(By.xpath(xpathForSubMenu));
				Log.info("Sub menu is "+subMenu.getText());
				builder.moveToElement(subMenu).build().perform();
				subMenu.click();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Products products = new Products(subMenu.getText());
				return products;
			}
		}
		Log.error("Submenu was not visible or available");
		return null;*/
		
	}
	 
	
	
	

