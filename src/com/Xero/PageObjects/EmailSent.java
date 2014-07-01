package com.Xero.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.Xero.GenericFunctions.*;

public class EmailSent extends GenericFunctions{

	WebDriver driver;

	By heading= By.cssSelector("h2.heading");

	public EmailSent(WebDriver driver)
	{
		String headingText=this.getWhenVisible(heading,30,driver).getText();
		Assert.assertTrue(headingText.contains("Check your inbox"));
	}
}
