package com.avactis.test.integration.storepageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.avactis.test.integration.utility.*;

public class Browser 
{
	private static WebDriver driver;
//	private static FirefoxOptions options;
	private static EventFiringWebDriver eventDriver;
	private static MyListner myListner;
	
	public static void setBrowser(String browser)
	{
		if(browser.equals("CH"))
		{
//			Log.info("Test will run on Chrome Browser");
			System.setProperty("webdriver.chrome.driver", "src\\test\\java\\resources\\chromedriver.exe");
	    	driver = new ChromeDriver();	
	    	eventDriver = new EventFiringWebDriver(driver);
			myListner = new MyListner();
			eventDriver.register(myListner);
//	    	Log.info("Chrome driver initiated");
		}
		else if(browser.equals("FF")) 
		{
//			Log.info("Test will run on Firefox Browser");
			System.setProperty("webdriver.gecko.driver", "src\\test\\java\\resources\\geckodriver64bit.exe");
			/*options = new FirefoxOptions();
			
			String pathName = "Program Files\\Mozilla Firefox\\";
		    String fileName = "firefox.exe";
		    String path = pathName+fileName;
		    System.out.println("Path of firebox binary "+path);
			options.setBinary(path);
			*/
			
			/*DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		    capabilities.setCapability("marionette", true);
			*/
			driver = new FirefoxDriver();
			eventDriver = new EventFiringWebDriver(driver);
			myListner = new MyListner();
			eventDriver.register(myListner);
//			Log.info("Firefox driver initiated");
		}
	}
	
	public static WebDriver getDriver()
	{
		return driver;

	}
	
	public static WebDriver getEventDriver()
	{
       return eventDriver;
	}
	
	public static void open(String url)
	{
		/*driver.manage().window().maximize();
		driver.get(url);
		*/
		eventDriver.manage().window().maximize();
		eventDriver.get(url);
	}
	
	public static void close() 
	{
//		driver.quit();
        eventDriver.quit();
	}

}
