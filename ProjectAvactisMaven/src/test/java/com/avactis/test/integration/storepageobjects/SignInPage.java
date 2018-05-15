package com.avactis.test.integration.storepageobjects;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.avactis.test.integration.utility.Log;
import com.avactis.test.integration.utility.Waiting;

public class SignInPage extends LoadableComponent<SignInPage> 
{
	/*String url;
	private static String title = ConfigProperties.getProperty("HOMEPAGE_TITLE");*/
	
	private static WebDriver driver;
	private String url = "http://avactis.enm2fzis5n.us-east-2.elasticbeanstalk.com/";
	private static String title = "Avactis Demo Store";
	private  Properties obj;
	
	@FindBy(xpath = "//span/a")
//  *[href*='ketan479/sign-in.php']
	WebElement signInLink;
	
	@FindBy(xpath = "//a[contains(@href,'register.php')]")	
	WebElement registerLink;
	
	@FindBy(xpath = "//input[contains(@value,'Sign In')]")	
	WebElement signInButton;
	
	@FindBy(id = "account_sign_in_form_email_id")	
	WebElement emailID;
	
	@FindBy(id = "account_sign_in_form_passwd_id")	
	WebElement password;

	public SignInPage()	
	{
		PageFactory.initElements(Browser.getEventDriver(), this);	
		get();
		
	}
	
	public SignInPage(WebDriver driver)
	{
		/*url = ConfigProperties.getProperty("STORE_URL");
		System.out.println("URL is " + url);*/
		SignInPage.driver = driver;
		PageFactory.initElements(driver, this);
//		Log.info("SignIn Page loaded");
		
		
		/*obj = new Properties();
		try 
		{
			FileInputStream objfile = new FileInputStream(System.getProperty("F:\\SELENIUM\\GIT\\avactis\\config\\avactis.properties"));
			obj.load(objfile);
		
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
	
	@Override
	protected void isLoaded() throws Error 
	{
		Waiting.WaitForElement(Browser.getDriver(), registerLink, 50);	
	}

	@Override
	protected void load() 
	{

		
	}


	public void doSignIn(String emailID, String password)
	{
		signInLink.click();
		this.emailID.sendKeys(emailID);
		this.password.sendKeys(password);
		signInButton.click();
	}




}
