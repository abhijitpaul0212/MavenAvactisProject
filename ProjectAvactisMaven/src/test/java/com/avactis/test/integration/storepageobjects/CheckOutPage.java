package com.avactis.test.integration.storepageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avactis.test.integration.utility.*;

public class CheckOutPage extends LoadableComponent<CheckOutPage>
{
	private String title = "Avactis Demo Store";
//	private int explicitTimeOut = Integer.parseInt(utility.ConfigProperties.getProperty("EXPLICIT_WAIT"));
	private ArrayList<List<String>> myCartItemDetails = new ArrayList<List<String>>();
	private ArrayList<List<String>> orderPreviewItemDetails = new ArrayList<List<String>>(); 
	
//	WebDriverWait wait = new WebDriverWait(driver, 30);
	
	@FindBy (css = "a[href='cart.php']")
	WebElement myCartLink;
	
	@FindBy (xpath = "//a[text()='Continue Shopping']/following-sibling::a[1]")
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
	WebElement orderId;
	
	@Override
	protected void isLoaded() throws Error 
	{

		
	}

	@Override
	protected void load() 
	{
		// TODO Auto-generated method stub
		
	}
	
	public CheckOutPage()
	{
		PageFactory.initElements(Browser.getEventDriver(), this);
		get();
	}
	
	public ArrayList<List<String>> checkOutStepOne()
	{
		Waiting.WaitForElement(Browser.getEventDriver(), checkOutLink, 70);
		/*try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Log.info("Go to MyCart page and click on Checkout");
		List<WebElement> tableElement = new ArrayList<WebElement>();
		  tableElement = Browser.getEventDriver().findElements(By.xpath("//tr"));
//		  Log.info("Size of table in MYcart Page is "+(tableElement.size()));
		  for(int j = 2; j <= tableElement.size(); j++)
		  {
			  String item = Browser.getEventDriver().findElement(By.xpath("//table[@summary='Shopping cart']//tr["+j+"]//td[2]")).getText();
			  String qty = Browser.getEventDriver().findElement(By.xpath("//table[@summary='Shopping cart']//tr["+j+"]//td[3]//option[@selected='selected']")).getText();
			  String price = Browser.getEventDriver().findElement(By.xpath("//table[@summary='Shopping cart']//tr["+j+"]//td[4]")).getText();
			  
//			  this.myCartItemDetails = new ArrayList<List<String>>();
			   myCartItemDetails.add(Arrays.asList(item,qty,price));
			  
		  }
		  
		  for(int j = 0; j < myCartItemDetails.size(); j++)
		  {				  
			  Log.info("Expected cart details: "+myCartItemDetails.get(j));
			  
		  }
		  checkOutLink.click();
		  return myCartItemDetails;
	}
	
	
	public void billingShippingAddress()
	  {
//		Log.info("Go to Billing&ShippingAddress page and click on Checkout");
		/*try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		billingAddressCheckoutButton.click();
		*/
		
		WebDriverWait wait = new WebDriverWait(Browser.getEventDriver(), 50);
		wait.until(ExpectedConditions.visibilityOf(firstNameTextBox));  
		
		String firstName = firstNameTextBox.getAttribute("value");
		wait.until(ExpectedConditions.visibilityOf(billingAddressCheckoutButton));		
		if(!firstName.isEmpty())
		{
			billingAddressCheckoutButton.click();
		}
		
		else
		{
			Log.info("Billing and shipping details is blank and needs to be filled");
		}
	
	  }	  
	  
	  public void billingShippingMethod(String strShippingMethod, String strPaymentMethod, ArrayList<List<String>> expCartItemDetails )
	  {		  
//		  Log.info("Go to Billing&ShippingMethod page and click on Checkout");
		  WebDriverWait wait = new WebDriverWait(Browser.getEventDriver(), 90);
		  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class ='shipping_method_name']/label")));
		  
		  //ShippingMethod
		  List<WebElement> shippingMethods = Browser.getEventDriver().findElements(By.xpath("//div[@class ='shipping_method_name']/label"));
		  for(WebElement shippingMethod:shippingMethods)
		  {
			  if(shippingMethod.getText().equalsIgnoreCase(strShippingMethod))
			  {
				  System.out.println(shippingMethod);
				  shippingMethod.click();
				  Log.info("Shipping method '"+strShippingMethod+"' is selected");
			  }
		  
		  }
		  
		  //PaymentMethod and place order
		  if(!strPaymentMethod.equals("CashOnDelivery"))
		  {
			  Browser.getEventDriver().findElement(By.xpath("//div[@class ='payment_method_list_row'][2]//input")).click();
			  billingShippingCheckoutButton.click();
			  placeOrder(expCartItemDetails);
			  Log.info("Ordered is placed ...waiting for Order No. to be generated ");
			  PayWithDebitOrCreditCard cardPayment = new PayWithDebitOrCreditCard();
			  cardPayment.CardPayment();
		  }		  
		  else
		  {
			  billingShippingCheckoutButton.click();
			  placeOrder(expCartItemDetails);
			  fetchOrderID();
		  }
		  

		  
		  /*List<WebElement> paymentMethods = Browser.getDriver().findElements(By.xpath("//div[@class ='payment_method_name']"));
		  for(WebElement paymentMethod:paymentMethods)		 
		  {
			  boolean match = paymentMethod.getText().equals(strPaymentMethod);
			  System.out.println("Match is: "+match);
			  if((!paymentMethod.isSelected()) && (match) && (paymentMethod.isDisplayed()))
			  {
				  Log.info("Payment method: "+paymentMethod.getText());
				  System.out.println(paymentMethod);
				  paymentMethod.click();
				  
			  }
			  else
			  {
				  continue;
			  }
		  }*/
		  
		
	  }
	  
	  public void placeOrder(ArrayList<List<String>> expCartItemDetails)
	  { 		 
		  Log.info("Go to PlaceOrder page and click on PlaceOrder");		  
		  WebDriverWait wait = new WebDriverWait(Browser.getEventDriver(),70);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr")));
		  
		  List<WebElement> tableElement1 = new ArrayList<WebElement>();
		  tableElement1 = Browser.getEventDriver().findElements(By.xpath("//tr"));
		  
		  for(int j = 2; j <= tableElement1.size(); j++)
		  {
			  String item = Browser.getEventDriver().findElement(By.xpath("//table[@class='order_items without_images']//tr["+j+"]//td[1]")).getText();
			  String qty = Browser.getEventDriver().findElement(By.xpath("//table[@class='order_items without_images']//tr["+j+"]//td[2]")).getText();
			  String price = Browser.getEventDriver().findElement(By.xpath("//table[@class='order_items without_images']//tr["+j+"]//td[3]")).getText();
			  orderPreviewItemDetails.add(Arrays.asList(item,qty,price));
		  }
		  for(int j = 0; j < orderPreviewItemDetails.size(); j++)
		  {				  
			  Log.info("Actual cart details: "+orderPreviewItemDetails.get(j));
			  
		  }
		  if(expCartItemDetails.equals(orderPreviewItemDetails))
		  {
	        	Log.info("Order got placed succesfully as comparison result is matched");
	        	if(Waiting.waitForElementPresent(placeOrder, 70))
	        	{
	        		placeOrder.click();
	        		Log.info("Place order button is clicked");
	        	}
	        	else
	        	{
	        		Log.error("Place order button is not interactable");
	        	}
	        	
	       }
	       else
	       {
	        	Log.error("Order placing got failed due to comparison result didnt matched");
	       }   	
			
	  }
	  
	  public void fetchOrderID()
	  {
		  WebDriverWait wait = new WebDriverWait(Browser.getEventDriver(),70);
		  wait.until(ExpectedConditions.visibilityOf(orderId));
		  String orderIds = orderId.getText();
		  String[] orderId = orderIds.split("#");
		  ConfigProperties.setProperty("ORDER_ID", orderId[1]);
		  Log.info("Your order ID is "+orderId[1]);  
	  }
	  
	
}	
	
//	public void myCartCheckOut(HashMap<String, String> mapExp, String expectedMenuList)
	/*public ArrayList<List<String>> myCartCheckOut(String expectedMenuList)
	  {
		WebElement AddToCart;
//		HashMap<String,String> mapAct = new HashMap<String,String>();
//		driver.navigate().refresh();
		myCartLink.click();		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(checkOutLink));
		
		myCartItemDetails = new ArrayList<List<String>>();
		  String[] fullMenu = expectedMenuList.split("#");
		  for(int i = 0; i < fullMenu.length; i++)
		  {
			  String[] partialMenu = fullMenu[i].split(";"); 
			  for(int j = 1; j < partialMenu.length; j++)  
   		   		{  
     			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@summary='Shopping cart']//a[text()='"+partialMenu[j]+"']//ancestor::td//following-sibling::td[@class='product_quantity_selector goods-page-quantity']//select")));
     			 try
     			  {
     				Select qty = new Select(driver.findElement(By.xpath("//table[@summary='Shopping cart']//a[text()='"+partialMenu[j]+"']//ancestor::td//following-sibling::td[@class='product_quantity_selector goods-page-quantity']//select")));
	     			 qty.selectByVisibleText("6");
	     			 Thread.sleep(5000);
     			  }
     			 	catch(Exception e)
     			  {
     				  e.printStackTrace();
     				  e.getMessage();
     			  }
     			
			  }  
		  }
		  
		  
			  
			  List<WebElement> tableElement = new ArrayList<WebElement>();
			  tableElement = driver.findElements(By.xpath("//tr"));
			  for(int j = 2; j <= tableElement.size(); j++)
			  {
				  String item = driver.findElement(By.xpath("//table[@summary='Shopping cart']//tr["+j+"]//td[2]")).getText();
                  String qty = driver.findElement(By.xpath("//table[@summary='Shopping cart']//tr["+j+"]//td[3]//option[@selected='selected']")).getText();
				  String price = driver.findElement(By.xpath("//table[@summary='Shopping cart']//tr["+j+"]//td[4]")).getText();
				  
				  myCartItemDetails.add(Arrays.asList(item,qty,price));
				  
			  }
			  
			  for(int j = 0; j < myCartItemDetails.size(); j++)
			  {				  
				  Log.info("Expected cart details: "+myCartItemDetails.get(j));
				  
			  }
			  checkOutLink.click();

			  return myCartItemDetails;*/
			  
			  /*
				  String price = driver.findElement(By.xpath("//table[@class='order_items without_images']//a[text()='"+item.getText()+"']//ancestor::td//following-sibling::td[@class='product_sale_price']/span")).getText();
				  String qty = driver.findElement(By.xpath("//table[@class='order_items without_images']//a[text()='mobile']//ancestor::td//following-sibling::td[@class='product_quantity_selector']")).getText();
				  Log.info("Actual Item: "+item.getText()+" Quantity: "+qty+" Price :"+price);
			  */
			  
		  
		
		
		
		
		  /*for (String Key : map.keySet())
		  {
              List<String> fruit = map.get(Key);    
              System.out.println("Key :" +Key);
              System.out.println("Value : "+fruit.get(0));
		  }*/
		
		/*String item = map.keySet().iterator().next();
		System.out.println("Key Set is: "+map.keySet());
		System.out.println("List value are: "+map.get(item));
		
		int itemSize = map.keySet().size();
		for(int i = 0; i < itemSize; i++)
		{
			System.out.println("For loop List value are: "+map.get(map.keySet().iterator().next()));
		}*/
		
		
		
		
	/*	  String[] fullMenu = expectedMenuList.split("#");
		  for(int i = 0; i < fullMenu.length; i++)
		  {
			  String[] partialMenu = fullMenu[i].split(";"); 
			  for(int j = 1; j < partialMenu.length;j++)
			  {
				  Log.info("Expected Item: "+partialMenu[j]+" and its Price: "+mapExp.get(partialMenu[j]));
			  }
		  }*/
		  
	/*	  List<WebElement> items = driver.findElements(By.xpath("//td[@class='goods-page-description']//a"));
		  for(WebElement item : items)
		  {
//			  System.out.println(item.getText()+" is the name of item on confirmation page");
//			  driver.findElements(By.xpath("//table[@summary]//h3/a[text()='Forbidden Planet']//ancestor::td//following-sibling::td[@class='goods-page-price']/*"));
			  String price = driver.findElement(By.xpath("//table[@summary]//h3/a[text()='"+item.getText()+"']//ancestor::td//following-sibling::td[@class='goods-page-price']/*")).getText();
			  Log.info("Actual Item: "+item.getText()+" on confirmation page and its Price is "+price);
			  mapAct.put(item.getText(), price);
		  }
		  
        Boolean cmp = compareHashMaps(mapExp, mapAct);
        Log.info("HashMap comparasion result: "+cmp);
        if(cmp)
        {
        	checkOutLink.click();
        }
        else
        {
        	Log.error("HashMap comparasion result: "+cmp);
        }
		  */
		  
      
//	return myCartItemDetails;
	  	  
	/*  public void billingShippingAddress()
	  {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(firstNameTextBox));  
		String firstName = firstNameTextBox.getAttribute("value");
		wait.until(ExpectedConditions.visibilityOf(billingAddressCheckoutButton));		
		if(!firstName.isEmpty())
		{
			billingAddressCheckoutButton.click();
		}
		
		else
		{
			Log.info("Billing and shipping details is blank and needs to be filled");
		}
	
	  }	  
	  
	  public void billingShippingMethod(String strShippingMethod)
	  {
		  WebDriverWait wait = new WebDriverWait(driver, 50);
		  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class ='shipping_method_name']/label")));
		  
		  List<WebElement> shippingMethods = driver.findElements(By.xpath("//div[@class ='shipping_method_name']/label"));
		  for(WebElement shippingMethod:shippingMethods)
		  {
			  if(shippingMethod.getText().equalsIgnoreCase(strShippingMethod))
			  {
				 shippingMethod.click();
				 Log.info("Shipping method '"+strShippingMethod+"' is selected");
			  }
		  }
		  billingShippingCheckoutButton.click();
	  }
	  
	  public String placeOrder(ArrayList<List<String>> expCartItemDetails)
	  { 
//		 HashMap<String,String> mapAct = new HashMap<String,String>();
		 
		 HashMap<String, List<String>> mapAct = new HashMap<String, List<String>>();
		 List<String> values = new ArrayList<String>();
		 
		 WebDriverWait wait = new WebDriverWait(driver, 50);
		 wait.until(ExpectedConditions.visibilityOf(placeOrder));
		 
		 
		 List<WebElement> items = driver.findElements(By.xpath("//td[@class='product_data']//a"));
		  for(WebElement item : items)
		  {
//			  System.out.println(item.getText()+" is the name of item on confirmation page");
//			  driver.findElements(By.xpath("//table[@summary]//h3/a[text()='Forbidden Planet']//ancestor::td//following-sibling::td[@class='goods-page-price']/*"));
			  String price = driver.findElement(By.xpath("//table[@class='order_items without_images']//a[text()='"+item.getText()+"']//ancestor::td//following-sibling::td[@class='product_sale_price']/span")).getText();
			  String qty = driver.findElement(By.xpath("//table[@class='order_items without_images']//a[text()='mobile']//ancestor::td//following-sibling::td[@class='product_quantity_selector']")).getText();
			  Log.info("Actual Item: "+item.getText()+" Quantity: "+qty+" Price :"+price);			  
			  values.add(price);
			  values.add(qty);
//			  hm.put("Key1", values);
			  mapAct.put(item.getText(), values);
		  }
		 
		 
		 List<WebElement> tableElement = new ArrayList<WebElement>();
		 myCartItemDetails = new ArrayList<List<String>>();
		  tableElement = driver.findElements(By.xpath("//tr"));
		  for(int j = 2; j <= tableElement.size(); j++)
		  {
			  String item = driver.findElement(By.xpath("//table[@class='order_items without_images']//tr["+j+"]//td[1]")).getText();
			  String qty = driver.findElement(By.xpath("//table[@class='order_items without_images']//tr["+j+"]//td[2]")).getText();
			  String price = driver.findElement(By.xpath("//table[@class='order_items without_images']//tr["+j+"]//td[3]")).getText();
			  
			  myCartItemDetails.add(Arrays.asList(item,qty,price));
			  
		  }
		  
		  for(int j = 0; j < myCartItemDetails.size(); j++)
		  {				  
			  Log.info("Actual cart details: "+myCartItemDetails.get(j));
			  
		  }
		 
		  Boolean cmp = compareHashMaps(map, mapAct);
	        
	        if(cmp)
	        {
	        	Log.info("Order got placed succesfully as HashMap comparasion result: "+cmp);
	        	placeOrder.click();
	        }
	        else
	        {
	        	Log.error("Order placing got failed due to HashMap comparasion result: "+cmp);
	        	driver.quit();
	        }
		  
		  Boolean cmp = expCartItemDetails.equals(myCartItemDetails);
				  
	        if(cmp)
	        {
	        	Log.info("Order got placed succesfully as comparison result: "+cmp);
	        	placeOrder.click();
	        }
	        else
	        {
	        	Log.error("Order placing got failed due to comparison result: "+cmp);
//	        	driver.quit();
	        }
		 
		 
//		 placeOrder.click();
		 wait.until(ExpectedConditions.visibilityOf(orderId));
		 String orderIds = orderId.getText();
		 String[] orderId = orderIds.split("#");
		 Log.info("Your order ID is "+orderId[1]);
		 return orderId[1];
	  } 
	  
	  
	  public boolean compareHashMaps(HashMap<String, List<String>> mapExp, HashMap<String, List<String>> mapAct)
	  {
		   if (mapExp==null || mapAct==null || mapExp.size() != mapAct.size()) {
		        return false;
		    }

		    for (Object key: mapExp.keySet()) {
		        if (!mapExp.get(key).equals(mapAct.get(key))) {
		            return false;
		        }
		    }
		    return true;
		  
	  }
	   */

