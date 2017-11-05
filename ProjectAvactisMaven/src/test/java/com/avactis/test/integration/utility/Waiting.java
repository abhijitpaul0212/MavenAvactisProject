package com.avactis.test.integration.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avactis.test.integration.storepageobjects.Browser;


public class Waiting 
{
 private static final int DEFAULT_WAIT_FOR_PAGE = 30;
 	public static WebElement WaitForElementByLocator(WebDriver driver, final By by, int timeOutInSeconds)
	{
		WebElement element;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //Nullify implicit wait
		
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		
		driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_FOR_PAGE, TimeUnit.SECONDS);
		return element;
	}
	
	public static WebElement WaitForElement(WebDriver driver, WebElement element, int timeOutInSeconds)
	{
	
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //Nullify implicit wait
		
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		element = wait.until(ExpectedConditions.visibilityOf(element));
		
		driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_FOR_PAGE, TimeUnit.SECONDS);
		return element;
	}
	
	
	public static boolean waitForTitle(String title) 
	{
		try
		{
			Browser.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 			
			WebDriverWait wait = new WebDriverWait(Browser.getDriver(),30); 
			wait.until(ExpectedConditions.titleContains(title));			
			Browser.getDriver().manage().timeouts().implicitlyWait(DEFAULT_WAIT_FOR_PAGE, TimeUnit.SECONDS); //reset implicitlyWait
			return true; //return the element
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		} 
		 
		
	}
	
	public static boolean waitForElementPresent(WebElement element, int timeOutInSeconds) 
	{
//		int timeOut = Integer.parseInt(timeOutInSeconds);
		
		try{
			Browser.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 
			
			WebDriverWait wait = new WebDriverWait(Browser.getDriver(),timeOutInSeconds ); 
			wait.until(ExpectedConditions.visibilityOf(element));
			
			Browser.getDriver().manage().timeouts().implicitlyWait(DEFAULT_WAIT_FOR_PAGE, TimeUnit.SECONDS); //reset implicitlyWait
			return true; //return the element
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
//		return null; 
	}
	
	public static boolean isElementPresentAndDisplay(WebDriver driver, By by) {
		try {			
			return driver.findElement(by).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
