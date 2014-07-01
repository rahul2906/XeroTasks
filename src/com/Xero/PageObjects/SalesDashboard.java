package com.Xero.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Xero.GenericFunctions.GenericFunctions;

public class SalesDashboard extends GenericFunctions{

	WebDriver driver;
	@FindBy(css ="a[href*='Accounts/Receivable/Dashboard/'][data-type='menu-focus']") 
	public static WebElement menuSalesLink;	
	@FindBy(css ="#page_title > div > h1") 
	public static WebElement SalesHeader;	
	@FindBy(css="a[href*='SearchRepeating']") 
	public static WebElement repeatingInvoicesLink;

	public SalesDashboard(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		Assert.assertTrue(this.titleContains("Xero | Sales | ",60,driver)); 
	}
	public String returnHeader()
	{
		return SalesHeader.getText().trim();
	}
	public RepeatingInvoicesDash navigateRepeatingInvoices()
	{
		repeatingInvoicesLink.click();
		return new RepeatingInvoicesDash(driver);
	}
}
