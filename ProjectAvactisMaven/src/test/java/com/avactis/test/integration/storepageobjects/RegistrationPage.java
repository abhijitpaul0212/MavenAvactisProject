package com.avactis.test.integration.storepageobjects;

import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.avactis.test.integration.utility.Log;
import com.avactis.test.integration.utility.Waiting;

public class RegistrationPage extends LoadableComponent<RegistrationPage>
{
	private static WebDriver driver;
	private String url = "http://avactis:avactis%40123@sandbox.avactis.com/ketan479/";
	private String title = "Avactis Demo Store";

	

	@FindBy (xpath = "//button[contains(text(),'Register')]")
	public WebElement goToRegistrationPage;
	
	@FindBy (xpath= "//input[@name='customer_info[Customer][Email]']")
	public WebElement emailID;
	
	@FindBy (xpath= "//input[@name='customer_info[Customer][Password]']")
	public WebElement pwd;
	
	@FindBy (xpath= "//input[@name='customer_info[Customer][RePassword]']")
	public WebElement rePwd;
	
	@FindBy (xpath= "//input[@name='customer_info[Customer][FirstName]']")
	public WebElement firstName;
	
	@FindBy (xpath= "//input[@name='customer_info[Customer][LastName]']")
	public WebElement lastName;
	
	@FindBy (name= "customer_info[Customer][Country]")
	public WebElement country;
	
	@FindBy (name= "customer_info[Customer][State]")
	public WebElement state;
	
	@FindBy (xpath= "//input[@name='customer_info[Customer][ZipCode]']")
	public WebElement zipCode;
	
	@FindBy (xpath= "//input[@name='customer_info[Customer][City]']")
	public WebElement city;
	
	@FindBy (xpath= "//input[@name='customer_info[Customer][Streetline1]']")
	public WebElement address1;
	
	@FindBy (xpath= "//input[@name='customer_info[Customer][Streetline2]']")
	public WebElement address2;
	
	@FindBy (xpath= "//input[@name='customer_info[Customer][Phone]']")
	public WebElement phone;
	
	@FindBy (xpath= "//input[@value='Register']")
	public WebElement registerButton;
	
	@FindBy (css = "*[href*='ketan479/sign-in.php']")
	public WebElement signInLink;
	
/*	@FindAll (value = 
		{ @FindBy (xpath = "//div[@class='note note-danger'][1]"),
		  @FindBy (xpath = "//div[@class='note note-danger'][2]"),
		  @FindBy (xpath = "//div[@class='note note-danger'][3]"),
		  @FindBy (xpath = "//div[@class='note note-danger'][4]")})
	public WebElement errorMessages;*/

	
	public RegistrationPage(WebDriver driver) //Constructor
	{
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
		 Log.info("Browser launched");
		 driver.manage().window().maximize();
	}		
		
	@Override
	protected void isLoaded() throws Error 
	{
//		Waiting.waitForTitle(driver,title);
	    assertEquals("Acvatis Demo Store page not loaded properly", title,  driver.getTitle());		
	}

	@Override
	protected void load() 
	{
//		driver.manage().window().maximize();
		driver.get(url);
		
	}
	
	public void close()
	{
		driver.quit();
		Log.info("Browser closed");
	}
	
	public void register(String email, String password, String firstname,String lastname,String pincode,String city,String address1,String address2,String mobile)
	{
		//at this point findElement method got executed internally by Selenium
		signInLink.click();
		goToRegistrationPage.click();
		emailID.sendKeys(email);
		pwd.sendKeys(password);
		rePwd.sendKeys(password);
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		Select country1 = new Select(country);
		country1.selectByVisibleText("India");
		Select state = new Select(driver.findElement(By.name("customer_info[Customer][State]")));
		state.selectByVisibleText("Assam");
		zipCode.sendKeys(pincode);
		this.city.sendKeys(city);
		this.address1.sendKeys(address1);
		this.address2.sendKeys(address2);
		phone.sendKeys(mobile);
		registerButton.click();		
	}
	
	public void errorVerification(int expectedNumberOfErrors)
	{
		Waiting.WaitForElementByLocator(driver, By.xpath("//div[@class='note note-danger']"), 30);
		  
		  int sizeOfErrorList = driver.findElements(By.xpath("//div[@class='note note-danger']")).size();	  
		  int actualNumberOfErrors = 0;
		  for(int i = 1; i <= sizeOfErrorList; i++)
		  {
			  String actError = driver.findElement(By.xpath("//div[@class='note note-danger']["+i+"]")).getText();
			  if(RegistrationPageErrorMessages().contains(actError))
			  {
				  actualNumberOfErrors++;
				  continue;
			  }
		  }
		  if(actualNumberOfErrors==expectedNumberOfErrors)
		  {
			  Log.info("PASS: Post registration, expected error(s) are "+expectedNumberOfErrors+" and actual error(s) are "+actualNumberOfErrors);
		  }
		  else
		  {
			  Log.info("FAIL: Post registration, expected error(s) are "+expectedNumberOfErrors+" and actual error(s) are "+actualNumberOfErrors);
		  }
		 
		  assertEquals("Error(s) count doesnot match with expected",expectedNumberOfErrors,actualNumberOfErrors);
	}
	
	
	public static ArrayList<String> RegistrationPageErrorMessages()
	{
		  String unMatchedPassword = "The password you entered is incorrect. Please enter the correct password.";
		  String invalidEmail = "Invalid data in field 'E-mail'.";
		  String invalidFirstname = "Invalid data in field 'First Name'.";
		  String invalidLastname ="Invalid data in field 'Last Name'.";
		  String duplicateUserName = "This account name is already taken. Please choose a different account name.";
		  
		  ArrayList<String> expErrorList = new ArrayList<String>();
		  expErrorList.add(unMatchedPassword);
		  expErrorList.add(invalidEmail);
		  expErrorList.add(invalidFirstname);
		  expErrorList.add(invalidLastname);
		  expErrorList.add(duplicateUserName);
		  return expErrorList;
	}
}