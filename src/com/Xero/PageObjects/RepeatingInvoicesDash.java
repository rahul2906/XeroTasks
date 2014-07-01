package com.Xero.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Xero.GenericFunctions.GenericFunctions;

public class RepeatingInvoicesDash extends GenericFunctions{

	WebDriver driver;
	@FindBy(css= "button[type='submit']")
	public static WebElement submitButton;
	@FindBy(css= "#ext-gen31 > a")
	public static WebElement newrepeatinginvoicebutton;
	@FindBy(css ="li.xn-h-menu:nth-child(5)") 
	public static WebElement settingMenuLink;	
	@FindBy(css ="a[href*='/Inventory/'][data-type='menu-focus']") 
	public static WebElement inventoryLink;	

	public RepeatingInvoicesDash(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		Assert.assertTrue(this.titleContains("Xero | Invoices ",60,driver));
	}
	public NewRepInvoice navigateNewRepeatingInv()
	{
		newrepeatinginvoicebutton.click();
		return new NewRepInvoice(driver);
	}
	public InventoryItems navigateToInventory(){
		settingMenuLink.click();
		this.isVisible(inventoryLink,20,driver).click();
		return new InventoryItems(driver);
	}
}
