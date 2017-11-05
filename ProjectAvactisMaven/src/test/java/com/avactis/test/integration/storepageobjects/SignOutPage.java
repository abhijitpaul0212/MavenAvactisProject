package com.avactis.test.integration.storepageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class SignOutPage extends LoadableComponent<SignOutPage>
{
	private static WebDriver driver;
	
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
	
	public SignOutPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void signOut()
	{
		driver.findElement(By.xpath("//*[contains(@href,'customer_sign_out')]")).click();
		  //Post logout validation
//		assertEquals("Sign In",driver.findElement(By.xpath("//*[contains(@href,'sign-in')]")).getText());
		  
	}
}
