package com.Xero.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Xero.GenericFunctions.GenericFunctions;
public class OrganisationDashboard extends GenericFunctions{
	WebDriver driver;
	@FindBy(css="span.x-window-header-text") 
	public static WebElement welcomePopUpHeader;
	@FindBy(css="div.x-tool.x-tool-close") 
	public static WebElement welcomePopUpclosebutton;
	@FindBy(css=".xn-h-org") 
	public static WebElement orgNameLink;
	@FindBy(css="a.myxero-link") 
	public static WebElement myXeroLink;
	@FindBy(id ="title") 
	public static WebElement myXeroHeader;
	@FindBy(css ="li.xn-h-menu:nth-child(2)") 
	public static WebElement accountMenuLink;	
	@FindBy(css ="a[href*='Accounts/Receivable/Dashboard/'][data-type='menu-focus']") 
	public static WebElement salesMenuLink;	
	@FindBy(css ="li.xn-h-menu:nth-child(6)") 
	public static WebElement settingMenuLink;	
	@FindBy(css ="a[href*='/Inventory/'][data-type='menu-focus']") 
	public static WebElement inventoryLink;	

	public OrganisationDashboard(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		Assert.assertTrue(this.titleContains("Xero | Dashboard | ",60,driver)); 
	}
	public Boolean returnWelcomePopUpWindowHeader()
	{
		return (welcomePopUpHeader.getText().trim().contains("You're all ready to go!"));
	}
	public void closeWelcomePopUpWindow()
	{
		welcomePopUpclosebutton.click();
	}
	public MyXeroHome gotoXeroHomePage()
	{	
		orgNameLink.click();
		this.isVisible(myXeroLink,60,driver).click();
		return new MyXeroHome(driver);
	}
	public String returnPageHeader()
	{
		return(myXeroHeader.getText().trim());
	}
	public SalesDashboard NavigateToSales()
	{
		accountMenuLink.click();
		this.isVisible(salesMenuLink,60,driver).click();
		return new SalesDashboard(driver);
	}
	public InventoryItems navigateToInventory(){
		settingMenuLink.click();
		this.isVisible(inventoryLink,60,driver).click();
		return new InventoryItems(driver);
	}
}
