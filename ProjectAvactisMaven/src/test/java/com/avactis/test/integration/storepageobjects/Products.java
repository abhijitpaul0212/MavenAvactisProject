package com.avactis.test.integration.storepageobjects;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avactis.test.integration.utility.Log;
import com.avactis.test.integration.utility.Waiting;

public class Products extends LoadableComponent<Products>
{
	private String title = "Avactis Demo Store";
	private WebDriverWait wait;
//	private int explicitTimeOut = Integer.parseInt(utility.ConfigProperties.getProperty("EXPLICIT_WAIT"));

	
	@FindBy (css = "a[href='cart.php']")
	WebElement myCartLink;
	
	/*@FindBy (xpath = "//a[text()='Continue Shopping']/following-sibling::a[1]")
	WebElement checkOutLink;
	
	@FindBy (xpath = "//*[@name='billingInfo[Firstname]']")
	WebElement firstNameTextBox;
	
	@FindBy (xpath = "//*[@value='Continue Checkout'][contains(@onclick,'1')]")
	WebElement billingAddressCheckoutButton;
	
	@FindBy (xpath = "//div[@class='checkout_buttons']/input[contains(@onclick,'submitStep(2)')]")
	WebElement billingShippingCheckoutButton;
	
	@FindBy (xpath = "//*[@value='Place Order']")
	WebElement placeOrder;
	
	@FindBy (xpath = "//label[contains(text(),'Order Id')]/following-sibling::div")
	WebElement orderId;*/
	
	private String selectedMenuString; 
	public Products(String selectedMenuString) 
	{
		this.selectedMenuString = selectedMenuString;
		PageFactory.initElements(Browser.getEventDriver(), this);
//		Log.info("Product page constructor is called");	
		get();
	}

	/*@Override
	protected void isLoaded() throws Error 
	{
		if(selectedMenuString !="")
		{
			assertTrue(Browser.getDriver().getTitle().equals(selectedMenuString+" - "+title));
		}
		else
		{
			assertTrue(Browser.getDriver().getTitle().equals(title));
		}
					
	}
	public Products() 
	{
		PageFactory.initElements(Browser.getDriver(), this);
		Log.info("Product page constructor is called");	
		get();
	}
*/
	@Override
	protected void isLoaded() throws Error 
	{
	
					
	}

	@Override
	protected void load() 
	{
			//unimplemented
	}
	
	
	public boolean addToCartButton(String productID)
	{
		String AddtoCartButtonForGivenProduct = "ProductForm_" + productID;
		Waiting.waitForElementPresent(Browser.getEventDriver().findElement(By.id(AddtoCartButtonForGivenProduct)), 70);
		WebElement AddToCart;
		try
		{
			AddToCart = Browser.getEventDriver().findElement(By.xpath("//*[@id='"+AddtoCartButtonForGivenProduct+"']/descendant::input[@value='Add To Cart']"));
			AddToCart.click();
			Log.info(productID+" product got added to cart");
		}
		catch (NoSuchElementException e) 
		{
			Log.error("Add to Cart button not found");
			return false;
		}
		/*wait = new WebDriverWait(Browser.getDriver(),30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ajax_message_box_text")));		*/
		return true;
	}
	
	public void placeOrder(Products productList, String productID, String paymentMethod, String shipmentMethod, String moreFlag) 
	{
//		String orderID = null;
		if (productList.addToCartButton(productID))
	        	Log.info("Add To Cart Is Successful");
	    else 
        {
        	System.out.println("Add To Cart Is Not Successful");
        	assertTrue(false);
        	fail("Add To Cart Failed");
        }
	    if (!moreFlag.contains("Y"))
	    {
	    	Waiting.WaitForElement(Browser.getEventDriver(), myCartLink, 70);
	    	myCartLink.click();		
//			Waiting.WaitForElement(Browser.getDriver(), checkOutLink, 60);
			CheckOutPage checkOut = new CheckOutPage();			
			ArrayList<List<String>> expCartItemDetails = checkOut.checkOutStepOne();
			checkOut.billingShippingAddress();
			checkOut.billingShippingMethod(shipmentMethod,paymentMethod,expCartItemDetails);
/*			checkOut.billingShippingMethod("Ground Shipping","PayPal Website Payments Standard"); //"PayPal Website Payments Standard","CashOnDelivery"
			String orderID = checkOut.placeOrder(expCartItemDetails);
			Log.info("Your order is: "+orderID);
			
			utility.ConfigProperties.setProperty("ORDER_ID", orderID);*/
	    }
	    else
        	Log.info("Looking out for Flag = N");
	    
//	    return orderID;
	   /* MyCart myCart = homePage.goToMyCartUsingUrl();
	        if (myCart.IsNotEmpty())
	        {
	        	Checkout checkout = myCart.doCheckoutUsingUrl();
	        	CheckoutSteptwo checkoutsteptwo;
	        	if ( !homePage.isUserLoggedIn())
	        	{
	        		checkoutsteptwo = checkout.continueCheckout(true);
	        	} else
	        	{
	        		checkoutsteptwo = checkout.continueCheckout(false);
	        	}
		        checkoutsteptwo.selectPaymentMethod(paymentMethod);
		        if (paymentMethod.equals("Authorize.Net (Credit Card)"))
		        {		        	
		        	checkoutsteptwo.authorizedDotNetCreditCardFillDetails();
		        } 
		        else if (paymentMethod.equals("Stripe Gateway"))
		        {
		        	checkoutsteptwo.stripeCreditCardFillDetails();
		        }
		        checkoutsteptwo.selectShippingMethod(shipmentMethod);
		        CheckoutStepthree checkoutstepthree = checkoutsteptwo.continueNextCheckout();
		        System.out.println("TestSuite-Before placing the order");
		        
		        checkoutstepthree.placeOrder();
		        if (paymentMethod.equals("PayPal Website Payments Standard"))
		        {
		        	AvactisPaymentMethods.payPalWebsitePaymentsStandard();
		        }
		        System.out.println("End of Place order Method");
        	}*/
	        
       
	}
	
	
}	
	
	/*public void navigateMenuAddToCart(WebDriver driver, int menuLevels, String menuString)
		  {
//			  Log.info("Adding to cart started");
			  WebElement AddToCart;
			  wait = new WebDriverWait(driver,30);
//			  HashMap<String, List<String>> map = new HashMap<String, List<String>>();
			  List<String> values = new ArrayList<String>();
			  
			  String qty = "2";
			  
			  String[] fullMenu = menuString.split("#");
			  for(int i = 0; i < fullMenu.length; i++)
			  {
				  String[] partialMenu = fullMenu[i].split(";");  
				
			      switch (menuLevels) 
			      {
			          case 1:  driver.findElement(By.cssSelector("*[class='header-navigation'] *[href*='"+partialMenu[0]+"']")).click();
			          		   wait.until(ExpectedConditions.titleContains(partialMenu[0]));
			          
			          		   for(int j = 1; j < partialMenu.length;j++)
			        		   { 
			          			 AddToCart = driver.findElement(By.xpath("//h3[text()='"+partialMenu[j]+"']/ancestor::a[1]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']"));
			          			//h3[text()='Up']/ancestor::a[1]/following-sibling::div[@class='product_buttons']//input			          			 
//			          			 itemPrice = driver.findElement(By.xpath("//h3[text()='"+partialMenu[j]+"']/ancestor::a[1]/following-sibling::div[@class='product_sale_price pi-price']//span")).getText();
//			          			 Log.info("Price of '"+partialMenu[j]+"' is "+itemPrice);
			          			 AddToCart.click();
//			          			 d.put(partialMenu[j], itemPrice); //Adding item-price to dictionary object
			          			  
			          			//Adding item-price to HashMap List
			          			values.add(itemPrice);
			          			values.add("2");
//			          			map.put(partialMenu[j], values);
			        		
//			          			Log.info(partialMenu[j]+" is added to cart. "+map.get(partialMenu[j]).get(0)+" is the price."+map.get(partialMenu[j]).get(1)+" is the qty");
			        			 
			        			 try {
									Thread.sleep(10000);
								} catch (InterruptedException e) 
			        			 {
									e.printStackTrace();
			        			 }
			        		   }
			          		   break;
			                   
			          case 2:  driver.findElement(By.cssSelector("*[href*='"+partialMenu[0]+"'][class='dropdown-toggle']")).click(); 
			          		   wait.until(ExpectedConditions.titleContains(partialMenu[0]));
			          		   for(int j = 1; j < partialMenu.length;j++)
			          		   { 
			          			 AddToCart = driver.findElement(By.xpath("//h3[text()='"+partialMenu[j]+"']/ancestor::a[1]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']"));
//			          			 itemPrice = driver.findElement(By.xpath("//h3[text()='"+partialMenu[j]+"']/ancestor::a[1]/following-sibling::div[@class='product_sale_price pi-price']//span")).getText();
//			          			 Log.info("Price of '"+partialMenu[j]+"' is "+itemPrice);
			          			 AddToCart.click();
//			          			 d.put(partialMenu[j], itemPrice);
			          			
			          			 //Adding item-price to HashMap List
			          			values.add(itemPrice);
			          			values.add("2");
			          			map.put(partialMenu[j], values);
			          			
			          			Log.info(partialMenu[j]+" is added to cart. "+map.get(partialMenu[j]).get(0)+" is the price."+map.get(partialMenu[j]).get(1)+" is the qty");
			          			 try {
									Thread.sleep(10000);
								} catch (InterruptedException e) 
			          			 {
									e.printStackTrace();
			          			 }
			          		   }
			    	  		   break;    
				  
			      }
			  }*/
			  
			  /*String[] fullMenu1 = menuString.split("#");
			  for(int i = 0; i < fullMenu1.length; i++)
			  {
				  String[] partialMenu1 = fullMenu1[i].split(";"); 
				  for(int j = 1; j < partialMenu1.length;j++)
	   		   { 
	     			 System.out.println(partialMenu1[j]+" is the key");
	     			 List<String> values1 = map.get(partialMenu1[j]);  
	     			 System.out.println(values1.get(0)+" is the price & "+values1.get(1)+" is the quantity");
	     		}
			  }*/
			  
			  /*for (String Key : map.keySet())
			  {
	              List<String> fruit = map.get(Key);    
	              System.out.println("Key :" +Key);
	              System.out.println("Value : "+fruit.get(0));
	              System.out.println("Value : "+fruit.get(1));
			  }
			return map;*/
//			  Log.info("Adding to cart completed");


	


