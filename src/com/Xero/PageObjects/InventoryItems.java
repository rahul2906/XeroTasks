package com.Xero.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Xero.DataObjects.Inventory;
import com.Xero.GenericFunctions.GenericFunctions;

public class InventoryItems extends GenericFunctions {

	WebDriver driver;
	@FindBy(id= "pricelistItems")
	public static WebElement table;
	@FindBy(css ="li.xn-h-menu:nth-child(2)") 
	public static WebElement accountMenuLink;	
	@FindBy(css ="a[href*='Accounts/Receivable/Dashboard/'][data-type='menu-focus']") 
	public static WebElement salesMenuLink;	

	public InventoryItems(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		Assert.assertTrue(this.strSpaceRemover(driver.getTitle()).contains("Xero | Inventory Items"));
	}

	public Inventory[] getPriceList()
	{
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		Inventory[] inv=new Inventory[rows.size()-1];
		for (int i = 1;i<rows.size();i++)
		{
			List<WebElement> cols=rows.get(i).findElements(By.xpath("td"));
			inv[i-1]=new Inventory();
			inv[i-1].setItemCode((cols.get(1).getText()));
			inv[i-1].setDescription((cols.get(2).getText()));
			inv[i-1].setSalesPrice((cols.get(6).getText()));
			inv[i-1].setSalesAccount((cols.get(7).getText()));
		}
		return inv;
	}
	public SalesDashboard NavigateToSales()
	{
		accountMenuLink.click();
		this.isVisible(salesMenuLink,40,driver).click();
		return new SalesDashboard(driver);
	}
}
