package com.Xero.Tests;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Xero.Data.Config;
import com.Xero.Data.TestData;
import com.Xero.DataObjects.Inventory;
import com.Xero.PageObjects.*;

public class Task2 {

	WebDriver driver;
	NewRepInvoice invoice;
	Inventory[] inventory;
	RepeatingInvoicesDash dashboard;

	/*Before function Test to login into the Page and reset the Demo organisation and goto Repeating
	  Invoices dashboard*/
	@Parameters ({"browser"})
	@BeforeTest
	public void createsession(String browser)
	{
	
		//Login into the Xero Page
		driver=null; 
		if(browser.equals("FF"))  
		{  	
			this.driver = new FirefoxDriver();	
		}
		else if (browser.equals("IE"))
		{
			Config.initialize();
			driver = new InternetExplorerDriver();
		}
		LoginPage login = new LoginPage(driver);
		OrganisationDashboard org = null;
		/*There are 2 possible outcomes of the Login Page.
		 * 1. Directly to the Dashboard page
		 * 2. MyXero Page
		 * The below logic allows either of the pages to be returned and resets the demo organisation
		 */
		Object page= login.loginSuccess(TestData.userid, TestData.password);
		if(page instanceof MyXeroHome)
		{
			org=((MyXeroHome) page).resetDemo();
		}
		else if(page instanceof OrganisationDashboard)
		{
			MyXeroHome home=((OrganisationDashboard) page).gotoXeroHomePage();
			org=home.resetDemo();
		}

		//Navigate to the Inventory page and fetch all the details of the inventory available.
		InventoryItems inv= org.navigateToInventory();
		inventory=inv.getPriceList();

		//Navigate to Repeating Invoices Page
		SalesDashboard sales = inv.NavigateToSales();
		dashboard=sales.navigateRepeatingInvoices();
	}

	/* Test to verify the 5 validation error messages to be displayed indicating mandatory fields
	 */
	@Test
	public void emptyFieldValidation()
	{
		invoice = dashboard.navigateNewRepeatingInv();

		//Define the list of Validation messages
		List<String> messages = Arrays.asList(
				"Invoice Date cannot be empty.",
				"Due Date cannot be empty.",
				"Please select how you wish this invoice to be treated.",
				"Description cannot be empty.",
				"Invoice to cannot be empty.");
		invoice.clickSave();

		//Fetch the error messages
		List<String> error = invoice.geterrorMessage();

		//Compare the error messages expected and actual
		Assert.assertTrue(CollectionUtils.isEqualCollection(error, messages)); 
		dashboard= invoice.clickCancel();
	}

	/*
	 * Verify the list of items displayed in the items drop down in the table of RepeatingInvoices
	 * The list of items is validated against a list present on the Inventories screen. 
	 * Fetched previously as part of the Before Test
	 */
	@Test
	public void itemDropDownValidation()
	{
		//Fetch list of items from the Repeating Invoices screen
		invoice = dashboard.navigateNewRepeatingInv();
		List<String> inventoryid = invoice.getItemList();

		//Compare inventoryid(Repeating Invoices screen) and inventory[i](Picked from Inventory screen)
		for(int i=0;i<inventory.length;i++)
		{
			Assert.assertTrue(inventoryid.contains(inventory[i].getItemCode()),"Items present on Inventory screen not present on repeating Invoice Items drop down");		
		}

		//Click on cancel for the next test to run
		dashboard= invoice.clickCancel();
	}
	/*
	 * To verify the details displayed in the table once an inventory item is selected
	 * Fields verified -
	 * Item code
	 * Description
	 * Sales Account
	 * Sales Price 
	 * These are verified from the data taken from the Inventory screen
	 */
	@Test
	public void tablevalidation()
	{
		invoice = dashboard.navigateNewRepeatingInv();
		Inventory inventorydetails = invoice.getInventoryDetails(inventory[0].getItemCode());
		Assert.assertTrue((inventorydetails.getDescription().equals(inventory[0].getDescription())));
		Assert.assertTrue((inventorydetails.getSalesAccount().equals(inventory[0].getSalesAccount())));
		Assert.assertTrue((inventorydetails.getSalesPrice().equals(inventory[0].getSalesPrice())));		
		dashboard= invoice.clickCancel();

	}
	/* 
	 * To verify the flow of the application when all the details are entered correctly in the 
	 * application. 
	 * Verification is done at the end of the flow on the Repeating Dashboard for the success message
	 * 
	 */
	@Test
	public void createNewInvoice()
	{
		invoice = dashboard.navigateNewRepeatingInv();
		invoice.createinvoice(inventory[0].getItemCode());
		Assert.assertTrue(driver.getPageSource().contains("Repeating Template Saved"));
	}

	//End of test close the driver
	@AfterTest
	public void closing()
	{
		this.driver.close();
		this.driver.quit();
	}
}