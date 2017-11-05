package com.avactis.test.integration.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class MyListner implements WebDriverEventListener, ITestListener
{

	public void afterAlertAccept(WebDriver driver) 
	{
		// TODO Auto-generated method stub
		
	}

	public void afterAlertDismiss(WebDriver arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) 
	{

		Log.info("Inside method afterChangeValueOf on " + arg0.toString());
		
	}

	public void afterClickOn(WebElement element, WebDriver driver) 
	{
		Log.info("Already clicked on " + element.toString());
		try
		{
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("target/screenshots/navigation"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log.fatal("Exception occured at " + e.getMessage());
		}
		
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) 
	{
		// TODO Auto-generated method stub
	}

	public void afterNavigateBack(WebDriver arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateForward(WebDriver arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateRefresh(WebDriver arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateTo(String url, WebDriver driver) 
	{
		Log.info("AfterNavigateTo: "+driver.getCurrentUrl());
		
	}

	public void afterScript(String arg0, WebDriver arg1) 
	{
		// TODO Auto-generated method stub
		
	}

	public void beforeAlertAccept(WebDriver arg0)
	{
		// TODO Auto-generated method stub
		
	}

	public void beforeAlertDismiss(WebDriver arg0)
	{
		// TODO Auto-generated method stub
		
	}

	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) 
	{
		// TODO Auto-generated method stub
		
	}

	public void beforeClickOn(WebElement element, WebDriver driver) 
	{
		Log.info("About to click on the " + element.toString());
		
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) 
	{
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateTo(String url, WebDriver driver) 
	{
		Log.info("BeforeNavigateTo: "+driver.getCurrentUrl());
		
	}

	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onException(Throwable exception, WebDriver driver) 
	{
		try
		{
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("target/screenshots/error"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log.fatal("Exception occured at " + e.getMessage());
		}
		
	}
	
	
	
	
	//TestNG listeners

	public void onFinish(ITestContext arg0) 
	{
		Log.info("***********About to end executing Suite '" + arg0.getName()+"' *************");
	}

	public void onStart(ITestContext arg0) 
	{
		Log.info("***********About to begin executing Suite '" + arg0.getName()+"' *************");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) 
	{
		Log.info("Test execution got failed '" + arg0.getName()+"'");
	}

	public void onTestSkipped(ITestResult arg0) 
	{
		Log.info("Test execution got skipped '" + arg0.getName()+"'");
	}

	public void onTestStart(ITestResult arg0) 
	{
		Log.info("About to start executing Test '" + arg0.getName()+"'");		
	}

	public void onTestSuccess(ITestResult arg0) 
	{
		Log.info("Completed executing Test '" + arg0.getName()+"'");
	}
		
}

