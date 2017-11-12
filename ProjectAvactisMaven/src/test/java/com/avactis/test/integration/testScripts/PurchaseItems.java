/*1. scenario3
1. Go to Avactis Store
2. Purchase 3 products from various product categories
3. Do CheckOut and provide shipping and billing address
4. Select shipment method and payment methods
5. Verify that the products selected, quantity and rate match with the final order
confirmation screen. 
5. Make the payment
6. Verify the order placed is same as what you had selected by verifying the order in admin.*/


package com.avactis.test.integration.testScripts;


import static org.testng.Assert.fail;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.avactis.test.integration.storepageobjects.Browser;
import com.avactis.test.integration.storepageobjects.HomePage;
import com.avactis.test.integration.storepageobjects.Products;
import com.avactis.test.integration.storepageobjects.SignInPage;
import com.avactis.test.integration.utility.ConfigProperties;
import com.avactis.test.integration.utility.ExcelData;
import com.avactis.test.integration.utility.Log;

public class PurchaseItems 
{
  HomePage homePage;
  private static String OrderID;
  
  @Parameters("browser")
  @BeforeClass(alwaysRun = true)
//   public void beforeClass(String browser)
   public void beforeClass(String browser)
  {
//	  String browser = "FF";
// This is to be deleted
	  System.out.println("I am in PurchaseItems.java file");
	  Browser.setBrowser(browser);
	  ConfigProperties.loadProperties();
	  homePage = new HomePage();
   	  homePage.get();
  }  
  
  @DataProvider(name = "DataProvider1")
  public Object[][] createData1() 
  {
     String file = ConfigProperties.getProperty("DATA_PATH")+ConfigProperties.getProperty("DATA_FILE");
	 System.out.println(file);
     Object[][] retObjArr=ExcelData.getTableArray(file,ConfigProperties.getProperty("DATA_SHEET"), ConfigProperties.getProperty("DATA_START_N_END_POINT"));	
     return(retObjArr);
  }    
  
  @Test(groups = "Login", priority=0, description="Login Scenario with valid username and password.")
	public void signIn()
	{
       SignInPage signInPage = homePage.doSignOut();
		if (signInPage == null)
		{
			signInPage = homePage.goToSignInPage();
		}
		signInPage.doSignIn("abhijitpaul_02@yahoo.com", "password123");		
		Log.info("Store signin successfull");
	}  
  
  
 @Test (groups = {"testOrder1"}, dataProvider="DataProvider1", dependsOnGroups = "Login", priority=1, description="Adding Items to Cart and performing Checkout.")
	public void checkOutUsingMyCartMenu(String productPageContent, String mainMenuID,String mainMenuName, String subMenuID, String subMenuName, String productID, String paymentMethod, String shipmentMethod, String moreFlag)
	{
		homePage.get();
		Products productList = homePage.goToProductPageUsingMenuAndSubMenu(mainMenuID, subMenuID);
		if (productList != null)
	      {
			productList.placeOrder(productList,productID,paymentMethod,shipmentMethod,moreFlag );
	      }
		else
		{
			fail("Could not go to required product List. Menu or submenu not available or not visible");
		}
		Log.info("Order got placed successfully. And the Order ID is " + ConfigProperties.getProperty("ORDER_ID"));
		OrderID =ConfigProperties.getProperty("ORDER_ID");
  }
	
 
 @AfterClass
 public void afterClass() 
 {
	 Browser.close();
 }
 
 
	
 /* @Test(priority=1)
  public void shopping()
  {
	  Log.info("---------------- Test Case : 'Placing order' begins ----------------");
	  //Step1: Signin the portal
	  signInP.doSignIn("abhijitpaul_02@yahoo.com", "password123");
	  
	  //Step2: Navigate the menu and adding items to cart	  
	  addToCartP.navigateMenuAddToCart(driver, 2, multiLevelMenu);	  
	  addToCartP.navigateMenuAddToCart(driver, 1, singleLevelMenu);	  
	  
	  //Step2: Go to MyCart and choose respective quantities of items
	  String expectedMenuList = singleLevelMenu+"#"+multiLevelMenu;	 	  
	  expCartItemDetails = checkOutP.myCartCheckOut(expectedMenuList);	  
	  //checkOutP.myCartCheckOut(map,expectedMenuList);
	  
	  //Step3: Go to Billing & Shipping address and provide the necessary details
	  checkOutP.billingShippingAddress();
	  
	  //Step4: Go to Billing & Shipping method and provide the necessary details
	  String shippingMethod="Ground Shipping";
	  checkOutP.billingShippingMethod(shippingMethod);
	  
	  //Step5: Continue to Place order page & after reviewing with expected ITEM, QTY & PRICE book the order
	  OrderID = checkOutP.placeOrder(expCartItemDetails);
	  
	  //Step6: Signout the portal
	  signOutP.signOut();
	  Log.info("---------------- Test Case : 'Placing order' ends ----------------");
  }
  
//  @Test(priority=2)
  public void orderValidating()
  {
	  Log.info("---------------- Test Case : 'Validating placed order' begins ----------------");
//	  String expectedMenuList = singleLevelMenu+"#"+multiLevelMenu;
	  signInP.doSignIn("abhijitpaul_02@yahoo.com", "password123");
//	  myAccountP.searchByOrderID(OrderID);
	  myAccountP.searchByOrderID("00943");
//	  ViewOrderPage.compareItems(driver,expectedMenuList);
	  Boolean returnVal = ViewOrderPage.compareItems(driver,expCartItemDetails);
	  Assert.assertTrue(returnVal);
	  signOutP.signOut();
	  Log.info("---------------- Test Case : 'Validating placed order' ends ----------------");
  }*/
 

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
  
  
  /*public void signIn() 
  {
//	  Homepage.clickSignIn(driver);
	  
	  By waitForAccountSignInForm= By.cssSelector("*[for='account_sign_in_form_email_id']");
	  By waitForOrderSearch = By.className("orders_search1");
	    
	  Waiting.WaitForElement(driver, waitForAccountSignInForm, 30);
	 
	  driver.findElement(By.id("account_sign_in_form_email_id")).sendKeys("abhijitpaul_02@yahoo.com");
	  driver.findElement(By.id("account_sign_in_form_passwd_id")).sendKeys("password123");
	  driver.findElement(By.cssSelector("*[value='Sign In']")).click();
	  
//	  Waiting.WaitForElement(driver, waitForOrderSearch, 30).equals("ORDERS");
//	  assertEquals("ORDERS", driver.findElement(By.className("orders_search1")).getText());
  }*/
  

  /*public void addToCart()
  {
	 String navigateToProduct1 = "DVD>";
	 String navigateToProduct2 = "DVD>";
	    String navigateToProduct3 = "DVD>Classic_Films";
	  String navigateToProduct4 = "Sport>cid45";
	  String navigateToProduct5 = "Furniture";   ;Up;Lost
	  
	  String navigateToProduct1 = "DVD;House_M_D;Lost";
	  String navigateToProduct2 = "Computers;pid172";
	 
//	  String multiLevelMenu = "DVD;House_M_D#Computers;pid172";
	  String multiLevelMenu = "DVD;Forbidden Planet#Computers;mobile";
	  navigateMenuAddToCart(driver, 2, multiLevelMenu);
	  
	  String singleLevelMenu = "Furniture;EKTORP TULLSTA Chair";
	  navigateMenuAddToCart(driver, 1, singleLevelMenu);
	  
	  String expectedMenuList = singleLevelMenu+multiLevelMenu;
	
	//*[contains(@href,'EKTORP Neckroll')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart'
	  
	  navigateMenuAddToCart(driver, 2, navigateToProduct1);
	  driver.findElement(By.xpath("//*[contains(@href,'Up')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
	  driver.navigate().refresh();
//	  navigateMenuAddToCart(driver, 2, navigateToProduct2);
	  
	  
	  try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	  	navigateMenu(driver,2,navigateToProduct1);	
	  driver.findElement(By.xpath("//*[contains(@href,'House_M_D')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
	  try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  driver.navigate().refresh();
	  
	  System.out.println("Done with first add to cart");
	 
	  
  try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  navigateMenu(driver,2,navigateToProduct2);	
		  driver.findElement(By.xpath("//*[contains(@href,'Up')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
		  try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  driver.navigate().refresh();
		  
		  System.out.println("Done with second add to cart");
	  
	  
	  navigateMenu(driver,2,navigateToProduct2);
	  driver.findElement(By.xpath("//*[contains(@href,'Up')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
	  System.out.println("Done with second add to cart");

	  navigateMenu(driver,2,navigateToProduct3);
      driver.findElement(By.xpath("//*[contains(@href,'James')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
      System.out.println("Done with third add to cart");
 	  
	  navigateMenu(driver,1,navigateToProduct5);
	  driver.findElement(By.xpath("//*[contains(@href,'EKTORP_TULLSTA')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
	  System.out.println("Done with fourth add to cart");
  }*/
  
 /* @Test(priority=3)
  public void checkOut()
  {
	driver.navigate().refresh();
	WebElement checkOutPreview = driver.findElement(By.xpath("//*[contains(@class,'preview cartpreview')]/i"));
	Actions action4 = new Actions(driver);
	action4.moveToElement(checkOutPreview).build().perform();
	driver.findElement(By.xpath("//*[@class='top-cart-content']//*[contains(text(),'Checkout')]")).click();
  }*/
  
  
/*  public void myCart()
  {
	driver.navigate().refresh();
	driver.findElement(By.cssSelector("a[href='cart.php']")).click();
	try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("I am about to click Checkout button");
//	driver.findElement(By.cssSelector("*[class='top-cart-content'] a[href*='checkout.php']")).click();
	driver.findElement(By.xpath("html/body/div[4]/div/div/div/div/div/div[1]/div/div/div[3]/a[2]")).click();
	
//	"//*[@class='top-cart-content']//a[contains(@href,'checkout.php')]"
//	html/body/div[4]/div/div/div/div/div/div[1]/div/div/div[3]/a[1]
	//*[@class='top-cart-content']//a[contains(@href,'checkout.php')]
  }*/
  
  
 /* public void billingShippingAddress()
  {
	String firstName = driver.findElement(By.xpath("//*[@name='billingInfo[Firstname]']")).getAttribute("value");
	//System.out.println(firstName);
	try {
		Thread.sleep(9000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(!firstName.isEmpty())
	{
		driver.findElement(By.xpath("//*[@value='Continue Checkout'][contains(@onclick,'1')]")).click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	else
	{
		System.out.println("Billing and shipping details is blank and needs to be filled");
	}
	

  }*/
  
 
  /*public void billingShippingMethod()
  {
	  List<WebElement> shippingMethods = driver.findElements(By.xpath("//div[@class ='shipping_method_name']/label"));
	  for(WebElement shippingMethod:shippingMethods)
	  {
		  if(shippingMethod.getText().equalsIgnoreCase("Ground Shipping"))
		  {
			  shippingMethod.click();
		  }
	  }
	  driver.findElement(By.xpath("//div[@class='checkout_buttons']/input[contains(@onclick,'submitStep(2)')]")).click();
  }*/
  
 
/*  public String placeOrder()
  { 
	 try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 driver.findElement(By.xpath("//*[@value='Place Order']")).click();
//	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 try {
		Thread.sleep(15000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 WebDriverWait wait = new WebDriverWait(driver, 30);
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Order Id')]")));
//	 Waiting.WaitForElement(driver, By.xpath("//label[contains(text(),'Order Id')]"), 30);
	 String orderIds = driver.findElement(By.xpath("//label[contains(text(),'Order Id')]/following-sibling::div")).getText();
	 String[] orderId = orderIds.split("#");
	 Log.info("Your order ID is "+orderId[1]);
	 return orderId[1];
	 
//	 validateOrder(driver, orderId[1]);
	 
  } */
  
 
 /* public void signOut()
  {
	  driver.findElement(By.xpath("//*[contains(@href,'customer_sign_out')]")).click();
	  //Post logout validation
	  assertEquals("Sign In",driver.findElement(By.xpath("//*[contains(@href,'sign-in')]")).getText());
	  
  }*/
  
  

  /*public void handleUserAuthentication() //This method runs on Internet Explorer only
  {
	  String uname = "avactis";
	  String pwd = "avactis@123";
	  
	  WebDriverWait wait = new WebDriverWait(driver, 10);      
	  Alert alert = wait.until(ExpectedConditions.alertIsPresent());     
	  alert.authenticateUsing(new UserAndPassword(uname, pwd));	  
  }*/
  
 /* public void navigateMenu(WebDriver driver, int menuLevels, String menuString) //DVD > TV on DVD > House_M_D
  {
	  String[] menus = menuString.split(">");
	  System.out.println(menus[0]);
	  System.out.println(menus[1]);
	
	  WebElement mainMenu;
	  Actions action;
      switch (menuLevels) 
      {
          case 1:  Waiting.WaitForElement(driver, By.cssSelector("*[class='header-navigation'] *[href*='"+menus[0]+"']"), 20);
        	  	   driver.findElement(By.cssSelector("*[class='header-navigation'] *[href*='"+menus[0]+"']")).click();
                   break;
          case 2:  Waiting.WaitForElement(driver, By.cssSelector("*[href*='"+menus[0]+"'][class='dropdown-toggle']"), 20);
        	  	   driver.findElement(By.cssSelector("*[href*='"+menus[0]+"'][class='dropdown-toggle']")).click();
        	  	   mainMenu = driver.findElement(By.cssSelector("*[href*='"+menus[0]+"'][class='dropdown-toggle']"));
    	  		   action = new Actions(driver);
    	  		   action.moveToElement(mainMenu).build().perform();
    	  		   driver.findElement(By.cssSelector("*[class='dropdown dropdown-megamenu'] *[href*='"+menus[1]+"']")).click();
    	  		   break;    
	  
      }  

  }
*/  
/*  public void navigateMenuAddToCart(WebDriver driver, int menuLevels, String menuString)
  {
	  WebElement AddToCart;
	  
	  
	  String[] fullMenu = menuString.split("#");
	  for(int i = 0; i < fullMenu.length; i++)
	  {
		  String[] partialMenu = fullMenu[i].split(";");  
		
	      switch (menuLevels) 
	      {
	          case 1:  driver.findElement(By.cssSelector("*[class='header-navigation'] *[href*='"+partialMenu[0]+"']")).click();
//	        	  	   for (int i = 1; i < items.size(); i++) 
	          		   for(int j = 1; j < partialMenu.length;j++)
	        		   { 
//	        	  		 AddToCart = driver.findElement(By.xpath("//*[contains(@href,'"+partialMenu[j]+"')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']"));
	          			 AddToCart = driver.findElement(By.xpath("//h3[text()='"+partialMenu[j]+"']/ancestor::a[1]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']"));
	          			 AddToCart.click();
	        	  		 System.out.println(AddToCart);
	        			 System.out.println(partialMenu[j]+" is added to cart");
	        			 try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	//        			 driver.navigate().refresh();
	        		   }
	                   break;
	                   
	          case 2:  driver.findElement(By.cssSelector("*[href*='"+partialMenu[0]+"'][class='dropdown-toggle']")).click();
//	          		   for (int i = 1; i < items.size(); i++) 
	          		   for(int j = 1; j < partialMenu.length;j++)
	          		   { 
//	          			 AddToCart = driver.findElement(By.xpath("//*[contains(@href,'"+partialMenu[j]+"')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']"));
	          			 AddToCart = driver.findElement(By.xpath("//h3[text()='"+partialMenu[j]+"']/ancestor::a[1]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']"));
	          			 AddToCart.click();
	        	  		 System.out.println(AddToCart);
	          			 System.out.println(partialMenu[j]+" is added to cart");
	          			 try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	//          			 driver.navigate().refresh();
	          		   }
	    	  		   break;    
		  
	      }
	  }

  }
*/  
/*  public int[] validateOrder(WebDriver driver, String orderId, String expectedItems)
  {
	  driver.findElement(By.xpath("//*[@href='sign-in.php']")).click();
	  driver.findElement(By.xpath("//*[@name='order_id']")).sendKeys(orderId);
	  driver.findElement(By.xpath("//*[@class='col-lg-1']/*[@class='en btn blue button_order_search input_submit']")).click();
	  try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  driver.findElement(By.xpath("//*[@title='Order Info']")).click();
//	  System.out.println("Validation started");
	  
	  
	  List<WebElement> actualOrderItems = driver.findElements(By.xpath("//div[@class = 'product_name']"));		
	  String actualItems = "";
	  
	  for(WebElement allItems: actualOrderItems)
		{
//			System.out.println(allItems.getText());
			actualItems = allItems.getText()+";"+actualItems;			
		}
//	  System.out.println(actualItems);
	  int[] result = compareItems(expectedItems, actualItems);
	  return result;
	  
		
	  
  }
  
  public static int[] compareItems(String expectedMenuList, String actualMenuList)
	{
		String matchValue = null;
		int actMatch = 0;
		int expMatch = 0;
		
		 String[] fullMenu = expectedMenuList.split("#");
		  for(int i = 0; i < fullMenu.length; i++)
		  {
			  String[] partialMenu = fullMenu[i].split(";");
			  expMatch = expMatch + (partialMenu.length -1);
		  }
		
		
		
		String replaceExp = expectedMenuList.replaceAll("#|;", ">"); 
		String[] fullMenuExp = replaceExp.split(">");        
		List<String> itemsExp= new ArrayList<String>();
		for(int i = 0; i< fullMenuExp.length ; i++)
		{
			itemsExp.add(fullMenuExp[i]);
		}
//		System.out.println("Added items to expected list "+itemsExp);
		String replaceAct = actualMenuList.replaceAll(" \\(.*?\\)", "");
//		System.out.println("Modified actual list "+replaceAct);
		for(String item: itemsExp)
		{
//			System.out.println(item);
			if (replaceAct.matches("(.*)"+item+"(.*)"))
			{
				actMatch++;
//				System.out.println("Actual match count "+actMatch);
			}
		}
    ArrayList<Integer> result = new ArrayList<Integer>();
    result.add(expMatch);
    result.add(actMatch);
  	return result;  	
  	
		int[] result = new int[2];
		result[0] = expMatch;
		result[1] = actMatch;
  	
		System.out.println(result[0]+":"+result[1]);
		return result;
  	if (actMatch==expMatch)
  	{
  		return matchValue = "Matched. Expected count: "+expMatch+" and Actual count: "+actMatch; 
  		
  	}
		return matchValue = "Not matched. Expected count: "+expMatch+" and Actual count: "+actMatch;
		
	}*/
  
}
