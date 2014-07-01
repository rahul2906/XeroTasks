package com.Xero.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Xero.GenericFunctions.GenericFunctions;

public class MyXeroHome extends GenericFunctions {
	public WebDriver driver;

	@FindBy(css= "a[href*='Demo']")
	public static WebElement DemoLink;
	@FindBy(id ="reset_demo")
	public static WebElement reset;
	@FindBy(id ="confirmResetDemo-btnInnerEl")
	public static WebElement confirmreset;

	public MyXeroHome(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		Assert.assertTrue(this.titleContains("My Xero | Home",60,driver)); 
	}

	public OrganisationDashboard gotoDemoOrganisation()
	{
		DemoLink.click();
		return new OrganisationDashboard(driver);
	}
	
	public OrganisationDashboard resetDemo()
	{
		reset.click();
		confirmreset.click();
		return new OrganisationDashboard(driver);
	}
}


