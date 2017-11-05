package com.avactis.test.integration.storepageobjects;

import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.avactis.test.integration.utility.Log;
import com.avactis.test.integration.utility.Waiting;

public class ViewOrderPage extends LoadableComponent<ViewOrderPage>
{
	  private static WebDriver driver;
	  private String title = "Avactis Demo Store";
	  private Waiting wait;
	  private static ArrayList<List<String>> myCartItemDetails;
	
	  //Constructor
	  public ViewOrderPage(WebDriver driver)
	  {
		  PageFactory.initElements(driver, this);
//		  Log.info("View order page is launched");
	  }
		
	  @Override
	  protected void isLoaded() throws Error 
	  {
		  assertEquals("Acvatis Demo Store page not loaded properly", title,  driver.getTitle());	
	  }
	
	  @Override
	  protected void load() 
	  {
		  // TODO Auto-generated method stub
	  }	
	  
	  
	  public static Boolean compareItems(WebDriver driver, ArrayList<List<String>> expCartItemDetails)
		{
			String matchValue = null;
			String actualItems = null;
			int actMatch = 0;
			int expMatch = 0;
			Boolean returnVal = false;
			
			Log.info("Comparasion between expected & actual number of items started.");
	
			/*//Getting the list of actual items purchased		
			List<WebElement> actualOrderItems = driver.findElements(By.xpath("//div[@class = 'product_name']"));		
			for(WebElement allItems: actualOrderItems)
			{
				actualItems = allItems.getText()+";"+actualItems;			
			}
			
			//Getting the list of expected items to be purchased		
			String[] fullMenu = expCartItemDetails.split("#");
			for(int i = 0; i < fullMenu.length; i++)
			{
				String[] partialMenu = fullMenu[i].split(";");
				expMatch = expMatch + (partialMenu.length - 1);
			}
			
			String replaceExp = expCartItemDetails.replaceAll("#|;", ">"); 
			String[] fullMenuExp = replaceExp.split(">");        
			List<String> itemsExp= new ArrayList<String>();
			for(int i = 0; i< fullMenuExp.length ; i++)
			{
				itemsExp.add(fullMenuExp[i]);
			}
			String replaceAct = actualItems.replaceAll(" \\(.*?\\)", "");
			for(String item: itemsExp)
			{
				if (replaceAct.matches("(.*)"+item+"(.*)"))
				{
					actMatch++;
				}
			}
			*/
			
			 List<WebElement> tableElement = new ArrayList<WebElement>();
			 myCartItemDetails = new ArrayList<List<String>>();
			  tableElement = driver.findElements(By.xpath("//tr"));
			  for(int j = 2; j <= tableElement.size(); j++)
			  {
				  String item = driver.findElement(By.xpath("//table[@class='order_items without_images']//tr["+j+"]//td[1]")).getText();
	             String qty = driver.findElement(By.xpath("//table[@class='order_items without_images']//tr["+j+"]//td[2]")).getText();
				  String price = driver.findElement(By.xpath("//table[@class='order_items without_images']//tr["+j+"]//td[3]")).getText();
				  String actItem = item.replaceAll(" \\(.*?\\)", "");
				  System.out.println("Actual Item is "+actItem);
				  myCartItemDetails.add(Arrays.asList(actItem,qty,price));
				  
			  }
			  
			  for(int j = 0; j < myCartItemDetails.size(); j++)
			  {				  
				  Log.info("Actual cart details: "+myCartItemDetails.get(j));
				  
			  }
			  
			  for(int j = 0; j < expCartItemDetails.size(); j++)
			  {				  
				  Log.info("Expected cart details: "+expCartItemDetails.get(j));
				  
			  }
			
			  /*returnVal = expCartItemDetails.equals(myCartItemDetails);
			  */return returnVal;
			
		}
	  


}
