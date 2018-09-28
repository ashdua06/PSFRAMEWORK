package org.peoplestrong.altworklife.core;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BaseClass {
	
	public WebDriver driver;
	public static final String CURRENT_DIR = System.getProperty("user.dir");
	private long DEFAULT_TIMEOUT=90;
	public static String timeout_value="500";	
	By loadingIconImage = By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	By calendarMonthDropdown = By.cssSelector("select[data-handler='selectMonth']");
	By calendarYearDropDown = By.cssSelector("select[data-handler='selectYear']");
	
	public BaseClass() {
		this.driver=DriverContext.getInstance().driver();
		this.driver.manage().window().maximize();
	}
	
	//returns Webelement 
	public WebElement element(By by) {
		verify(ExpectedConditions.visibilityOfElementLocated(by));
		return driver.findElement(by);
	}
	
	//returns List of WebElements
	public List<WebElement> elements(By by){
		verify(ExpectedConditions.visibilityOfElementLocated(by));
		return driver.findElements(by);
	}
	
	//click on specified locator
	public void click(By by) {	
		verify(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).click();			
			
	}
	
	//clears mentioned input field and enters text in it
	public void enterText(By by,String text) {
		verify(ExpectedConditions.visibilityOfElementLocated(by));
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(text);
	}
	
	//returns title of the page
	public String getPageTitle(By by) {
		return driver.getTitle();
	}
	
	//navigate to specified url
	public void navigateToUrl(String url) {
		driver.get(url);
	}
	
	//move mouse pointer to the specified location
	public void mouseHover(By by) {
		verify(ExpectedConditions.visibilityOfElementLocated(by));
		Actions action = new Actions(driver);
		action.moveToElement(element(by)).perform();
	}
	
	//verify exact element text value	
	public void verifyElementText(By by,String expected) {
		verify(ExpectedConditions.visibilityOfElementLocated(by));
		String actualText= driver.findElement(by).getText();
		Assert.assertTrue((actualText).equals(expected) ,"Expected text:- "+expected+"Actual text:- "+actualText);	
	}
	
	//verify a particular string is contained in particular string
	public void verifyElementTextContained(By by,String expected) {
		verify(ExpectedConditions.visibilityOfElementLocated(by));
		String actualText= driver.findElement(by).getText();
		Assert.assertTrue((actualText).contains(expected) ,"Expected text:- "+expected+"Actual text:- "+actualText);	
	}
	
	
	//returns count of element with particular locator
	public int getElementCount(By by) {
		verify(ExpectedConditions.visibilityOfElementLocated(by));
		return driver.findElements(by).size();
	}
	
	//switch to mentioned frame
	public void switchToFrame(String nameOrId) {		
		driver.switchTo().frame(nameOrId);		
	}
	
	//switch back to default content from frame
	public void switchToDefaultContent() {		
		driver.switchTo().defaultContent();
	}
	
	//select value by passing locator of select  tag
	public void selectByValue(By by,String value) {
		Log.info("value to select is " + value);
		Select selectable = new Select(driver.findElement(by));
		selectable.selectByValue(value);
	}
	
	//select value by index by passing a particular locator
	public void selectByIndex(By by,int index) {
		verify(ExpectedConditions.visibilityOfElementLocated(by));
		Select selectable = new Select(driver.findElement(by));
		selectable.selectByIndex(index);
	} 
	
	/**
     * This method will set any parameter string to the system's clipboard.
     */
	public  void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	
	//upload a file using robot class 
	public  void uploadFile(String fileLocation) {
        try {
        	//Setting clipboard with file location
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
            waitInSeconds(2);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            waitInSeconds(5);
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }
	
	//
	public <T> T verify(ExpectedCondition<T> condition) {
        return verify(condition, DEFAULT_TIMEOUT,Long.parseLong(timeout_value));
	}
	
	 public <T> T verify(ExpectedCondition<T> condition, long timeout, long sleep) {
	       return new WebDriverWait(driver, timeout, sleep)
	             .ignoring(Exception.class)//,NoSuchElementException.class
	             .until(condition);
	 }
	
	/*public void waitInSeconds(int waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}*/
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public void waitInSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// select date from calendra
	// date- Value of date which need to be selected
	// MonthNum- Month number(integer) which need to be displayed, where 0 stands for Jan and 11 for Dec
	// Year- String -year name which need to be selected.
	public void selectDateFromCalendar(int Date,int MonthNum,String Year){
		Select calendarYear = new Select(driver.findElement(calendarYearDropDown));
		calendarYear.selectByValue(Year);
		waitInSeconds(1);
		Select calendarMonth = new Select(driver.findElement(calendarMonthDropdown));
		calendarMonth.selectByIndex(MonthNum);
		this.click(By.xpath("//a[text()='"+Date+"']"));
	}
	
	public void verifyElementAttribute(By by,String attribute,String value) {
		verify(ExpectedConditions.visibilityOfElementLocated(by));
		String actual = driver.findElement(by).getAttribute(attribute);
		Assert.assertEquals(actual, value, "Expected attribute value:- " +value+"Actual value:- "+actual );
	}
	
	public void highLighterMethod( By element){
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid yellow;');", driver.findElement(element));
	}
	
	String firstWinHandle;
	String secondWinHandle;
	public void getPrimaryAndSecondaryWindowHandles() {
		 Set handles = driver.getWindowHandles();		 
		 firstWinHandle = driver.getWindowHandle(); 
		 handles.remove(firstWinHandle);		 
		 String winHandle=(String) handles.iterator().next();		 
		 if (winHandle!=firstWinHandle){		 
		 //To retrieve the handle of second window, extracting the handle which does not match to first window handle
		 secondWinHandle=winHandle;
		 }
	}
	
	public void verifyUrl(String url) {		
		Assert.assertTrue((driver.getCurrentUrl()).contains(url) ,"Expected text:- "+url+"Actual text:- "+driver.getCurrentUrl());	
	}
	
	public void switchToSecondaryWindow() {
		getPrimaryAndSecondaryWindowHandles();
		driver.switchTo().window(secondWinHandle);		
	}
	
	public void switchBackToPrimaryWindow() {
		driver.switchTo().window(firstWinHandle);
	}
	
	public void closeCurrentWindow() {
		driver.close();
	}
	
	public int noOfRowsInTable(By by) {
		int totalrows;
		List  rows = elements(by);
		totalrows=rows.size();
		return(totalrows);
	}
	
	public String getElementText(By by) {
		return driver.findElement(by).getText();
	}
	
}
