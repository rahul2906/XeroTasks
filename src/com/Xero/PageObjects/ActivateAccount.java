package com.Xero.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Xero.GenericFunctions.GenericFunctions;

public class ActivateAccount extends GenericFunctions{

	WebDriver driver;
	@FindBy(id="Password") 
	public static WebElement password;
	@FindBy(id="submitButton") 
	public static WebElement submit;

	public ActivateAccount(WebDriver driver, String link)
	{
		this.driver=driver;
		driver.get(link);
		PageFactory.initElements(driver, this);
		Assert.assertTrue(titleContains("Activate Account | Xero Accounting",30,driver));
	}
	public void enterPassword(String pass)
	{
		password.sendKeys(pass);
	}
	public void clicksubmit()
	{
		submit.click();
	}
	public Object activateAccount(String password)
	{
		enterPassword(password);
		clicksubmit();
		waitForNextPage("Activate Account ",driver);
		if(driver.getTitle().trim().contains("Add your organisation"))
		{
			return new AddYourOrganisation(driver);
		}
		else
			return new AddYourOrganisation2(driver);
	}
}
