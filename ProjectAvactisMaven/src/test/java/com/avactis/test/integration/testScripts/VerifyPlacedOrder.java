package com.avactis.test.integration.testScripts;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.avactis.test.integration.adminpageobjects.AdminSignInPage;
import com.avactis.test.integration.storepageobjects.Browser;
import com.avactis.test.integration.utility.ConfigProperties;
import com.avactis.test.integration.utility.Log;

public class VerifyPlacedOrder 
{

  private AdminSignInPage adminSignInPage;
//  private String browser = null;

  @Parameters("browser")
  @BeforeClass(alwaysRun = true)
   public void beforeClass(String browser)
  {
	  System.out.println("I am in VerifyPlacedOrder.java");
	  Browser.setBrowser(browser);
	  ConfigProperties.loadProperties();
	  adminSignInPage = new AdminSignInPage();
	  adminSignInPage.get();
  }
  
  @Test
  public void verifyPlacedOrderUsingID() 
  {
	  assertTrue(adminSignInPage.SearchOrder());
  }

  @AfterClass
  public void afterClass() 
  {
	  adminSignInPage.close();
  }

}
