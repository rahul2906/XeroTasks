package com.Xero.PageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.Xero.DataObjects.Inventory;
import com.Xero.GenericFunctions.GenericFunctions;

public class NewRepInvoice extends GenericFunctions{

	WebDriver driver;
	@FindBy(css= "button[type='submit']") 
	public static WebElement submitButton;
	@FindBy(xpath= "//span[contains(text(),'Save')]")
	public static WebElement saveButton;
	@FindBy(css= "#notify01 > div > ul > li")
	public static List<WebElement> errormessages;
	@FindBy(css= ".show")
	public static WebElement showmessage;
	@FindBy(id= "DueDateDay")
	public static WebElement dueDateDay;
	@FindBy(id= "StartDate")
	public static WebElement startDate;
	@FindBy(id= "EndDate")
	public static WebElement endDate;
	@FindBy(id= "saveAsDraft")
	public static WebElement draftRadio;
	@FindBy(id= "saveAsAutoApproved")
	public static WebElement autoAppRadio;
	@FindBy(id= "saveAsAutoApprovedAndEmail")
	public static WebElement emailRadio;
	@FindBy(css= "input[id*=PaidToName][type=text]")
	public static WebElement textInvoiceTo;
	@FindBy(css= "input[id*=Reference][type=text]")
	public static WebElement reference;
	@FindBy(css= "table[class*=x-grid3-row-table]")
	public static WebElement table;
	@FindBy(css="input[id='ext-comp-1001']+img")
	public static WebElement tableItemDropDown;
	@FindBy(css= ".x-combo-list-inner >*")
	public static List<WebElement> tableItemdroplist;
	@FindBy(css= "a[href*=SearchRepeating] >span")
	public static WebElement cancel;

	//Constructor to use PageFactory and get all the objects on the page and verify title
	public NewRepInvoice(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		Assert.assertTrue(this.strSpaceRemover(driver.getTitle()).contains("Xero | New Repeating Invoice"));
	}
	
	public RepeatingInvoicesDash clickCancel()
	{
		cancel.click();		
		return new RepeatingInvoicesDash(driver);
	}
	//To get the list of items from the drop down list of the table from the first row
	public List<String> getItemList()
	{
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		List <String> txt =  new ArrayList<String>();
		for (WebElement row : rows)
		{
			//Get all the columns for the row
			List<WebElement> cols=row.findElements(By.xpath("td"));
			//Click on column 1 (Item ID)
			cols.get(1).click();
			sleep(1);
			//Click on the drop down arrow
			tableItemDropDown.click();
			//Add all the text in the elements to the table
			for (WebElement element : tableItemdroplist)
			{	
				txt.add(element.getText());
			}  
		}
		return txt;         
	}

	public void clickSave()
	{
		saveButton.click();
		sleep(4);
	}

	public List<String> geterrorMessage()
	{
		//To get the list of Validation error messages displayed
		sleep(4);
		showmessage.click();
		System.out.println(errormessages.size());
		List <String> txt =  new ArrayList<String>();
		for (WebElement element : errormessages)
		{
			txt.add(element.getText());
		}
		return txt;
	}

	//To get the list of Inventory details from the First row of the table after selecting an item(ID)
	public Inventory getInventoryDetails(String id)
	{
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		Inventory inv=new Inventory();
		List<WebElement> cols=rows.get(0).findElements(By.xpath("td"));
		cols.get(1).click();
		sleep(1);
		tableItemDropDown.click();
		for (WebElement element : tableItemdroplist)
		{
			if(id.contentEquals((element.getText())))
			{
				//Select it if the item is equal to the expected item
				element.click();
				inv.setItemCode(id);
				break;				
			}
		}
		//Allow the tables to refresh
		sleep(2);
		rows=table.findElements(By.tagName("tr"));
		cols=rows.get(0).findElements(By.xpath("td/div"));
		
		//Fetch the values from the columns for Description, Price, And Sales Account
		inv.setDescription(cols.get(2).getText());
		inv.setSalesPrice(cols.get(4).getText());
		inv.setSalesAccount((cols.get(6).getText()));	
		return inv;	    
	}

	//Create a new invoice with an item with id as the parameter
	public void createinvoice(String id)
	{
		//Get the data in the format accepted for today +5 and today +50
		DateFormat dateFormat= new SimpleDateFormat("dd MMM yyyy");
		Calendar c = Calendar.getInstance();    
		c.setTime(new Date());
		c.add(Calendar.DATE, 5);
		startDate.sendKeys(dateFormat.format(c.getTime()));
		dueDateDay.sendKeys("4");
		c.add(Calendar.DATE, 50);
		endDate.sendKeys(dateFormat.format(c.getTime()));
		
		// Create a draft Invoice and save it
		draftRadio.click();
		textInvoiceTo.sendKeys("Testing");
		reference.sendKeys("Reference");
		getInventoryDetails(id);
		saveButton.click();
		sleep(5);
	}
}
