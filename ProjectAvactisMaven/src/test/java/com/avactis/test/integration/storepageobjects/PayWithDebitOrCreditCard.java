package com.avactis.test.integration.storepageobjects;

import static org.testng.AssertJUnit.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.avactis.test.integration.utility.Log;
import com.avactis.test.integration.utility.Waiting;

public class PayWithDebitOrCreditCard extends LoadableComponent<PayWithDebitOrCreditCard> 
{
	@FindBy (xpath = "//a[@id='checkoutAsAGuestBtn']")
	WebElement btn_DebitCardCreditCard;
	
	@FindBy (name = "countryName")
	WebElement drpdwn_Country;
	
	@FindBy (id = "billingState")
	WebElement drpdwn_State;
	
	@FindBy (name = "cardNumber")
	WebElement edtbx_CardNumber;
	
	@FindBy (id = "expiry_value")
	WebElement edtbx_ExpiryMonthYear;
	
	@FindBy (id = "cvv")
	WebElement edtbx_CvvNumber;
	
	@FindBy (id="telephone")
	WebElement edtbx_Mobile;
	
	@FindBy (id="guestTerms")
	WebElement chkbx_Confirm;
	
	@FindBy (id="guestSubmit")
	WebElement btn_Submit;
	
	@FindBy (xpath = "//div[@id='miniCart']/h3")
	WebElement verifyPayment;
	
	@FindBy (id = "minipageSubmitBtn")
	WebElement btn_minipageSubmitBtn;
	
	@FindBy (id = "country_code")
	WebElement drpdwn_country_code;
	
	@FindBy (id = "state")
	WebElement drpdwn_state;
	
	@FindBy (id = "cc_number")
	WebElement edtbx_cc_number;
	
	@FindBy (id = "expdate_month")
	WebElement edtbx_expdate_month;
	
	@FindBy (id = "expdate_year")
	WebElement edtbx_expdate_year;
	
	@FindBy (id = "cvv2_number")
	WebElement edtbx_cvv2_number;
	
	@FindBy (id="H_PhoneNumber")
	WebElement edtbx_PhoneNumber;
	
	@FindBy (xpath = "//p/input[@id='submitBilling']")
	WebElement btn_submitBilling;
	
	@Override
	protected void isLoaded() throws Error 
	{
		assertEquals("PayPal Checkout page not loaded properly", "PayPal Checkout - Log In",  Browser.getDriver().getTitle());		
	}

	@Override
	protected void load() 
	{
		// TODO Auto-generated method stub
		
	}
	
	public PayWithDebitOrCreditCard() 
	{
		Log.info("PayWithDebitOrCreditCard page constructor is called");
		PageFactory.initElements(Browser.getDriver(), this);
		get();
	} 

	
	public void CardPayment()
	{
		Log.info("CardPayment() called");
		Waiting.waitForElementPresent(btn_DebitCardCreditCard, 30);
		btn_DebitCardCreditCard.click();
		Select country = new Select(drpdwn_Country);
		country.selectByVisibleText("India");
		edtbx_CardNumber.sendKeys("4032 0328 1433 5177");
		edtbx_ExpiryMonthYear.sendKeys("0220");
		edtbx_CvvNumber.sendKeys("123");
		Select state = new Select(drpdwn_State);
		state.selectByVisibleText("Chandigarh");
		edtbx_Mobile.sendKeys("8408012847");
		chkbx_Confirm.click();
		btn_Submit.click();
		
		Waiting.waitForElementPresent(btn_minipageSubmitBtn, 30);
		btn_minipageSubmitBtn.click();
		
		Select country_code = new Select(drpdwn_country_code);
		country.selectByVisibleText("India");
		edtbx_cc_number.sendKeys("4032 0328 1433 5177");
		edtbx_expdate_month.sendKeys("02");
		edtbx_expdate_year.sendKeys("20");
		edtbx_cvv2_number.sendKeys("123");
		Select state1 = new Select(drpdwn_state);
		state1.selectByVisibleText("Gujarat");
		edtbx_PhoneNumber.sendKeys("8408012847");
		btn_submitBilling.click();
		
		Waiting.isElementPresentAndDisplay(Browser.getDriver(), By.xpath("//div[@class='layout1']/p[1]"));
		String expText = "At this time, we are unable to process your request. Please return to";
		String actText = Browser.getDriver().findElement(By.xpath("//div[@class='layout1']/p[1]")).getText();
		if (actText.contains(expText))
		{
			Log.info("PASS");
		}
		
		else
			Log.info("FAIL");
		
		
	/*	String verifyPayment = "Your order summary";
		if(verifyPayment.matches(verifyPayment))
		{
			Log.info("Verify passed");
		}
		else
		{
			Log.info("Verify failed");
		}*/
		
		
	}
	
	

}
