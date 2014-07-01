package com.Xero.GenericFunctions;

import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericFunctions {

	/*
	 * Function to control timeouts of locators. 
	 * Allows to pass the locator, timeout seconds and the driver
	 */
	public WebElement getWhenVisible(By locator, int timeout,WebDriver driver) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}
	/*
	 * Allows to pass a webelement directly and wait for the element to appear
	 * 
	 */
	public	WebElement isVisible(WebElement element, int timeout,WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/*
	 * Function to wait for the title to change before running assertion functions
	 * Parameters: New title, Seconds, and the driver
	 */
	public	Boolean titleContains(String title, int timeout,WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.titleContains(title));
	}

	/*
	 * Sleep function for explicit waits
	 */
	public void sleep(Integer n)
	{
		try {
			Thread.sleep(n*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Function to verify if the element is present on a page.
	 * This function also allows to catch the exception incase the locator is not present
	 */
	public boolean isElementPresent(WebDriver driver, By locator) { 
		try { 
			driver.findElement(locator); 
			return true; 
		} catch (NoSuchElementException e) { 
			return false; 
		} 
	}

	/*
	 * Function to wait for the next page. This function checks for the current page title
	 * and waits till it changes. If the page does not change in 60 seconds it times out
	 */
	public void waitForNextPage(String currentTitle,WebDriver driver)
	{
		Integer i=0;
		while(driver.getTitle().trim().contains(currentTitle) && i<60)
		{
			sleep(1);
			i=i+1;
		}
	}
	
	/*
	 * Function to remove extra spaces present in a string. (Even spaces in between the string) 
	 * This allows easy compare of titles.
	 */
	public String strSpaceRemover(String str){
		StringTokenizer st = new StringTokenizer(str, " ");
		StringBuffer sb = new StringBuffer();
		while(st.hasMoreElements()){
			sb.append(st.nextElement()).append(" ");
		}
		return sb.toString().trim();
	}
}
