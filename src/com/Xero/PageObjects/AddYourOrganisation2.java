package com.Xero.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Xero.GenericFunctions.GenericFunctions;

public class AddYourOrganisation2 extends GenericFunctions{

	WebDriver driver;
	@FindBy(css ="a[href*='Demo']")
	public static WebElement demoLink;
	@FindBy(id ="reset_demo")
	public static WebElement reset;
	@FindBy(id ="confirmResetDemo-btnInnerEl")
	public static WebElement confirmreset;
	public AddYourOrganisation2(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		Assert.assertTrue(this.titleContains("My Xero | Home",60,driver)); 
	}
	public OrganisationDashboard gotoDemolink()
	{
		driver.findElement(By.cssSelector("a[href*='Demo']")).click();		
		return new OrganisationDashboard(driver);
	}
}
