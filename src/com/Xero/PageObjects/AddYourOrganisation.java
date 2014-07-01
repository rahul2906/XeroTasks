package com.Xero.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Xero.GenericFunctions.GenericFunctions;

public class AddYourOrganisation extends GenericFunctions {
	WebDriver driver;
	@FindBy(name ="OrganisationName")
	public static WebElement orgName;
	@FindBy(id ="startBtn-btnWrap")
	public static WebElement button;

	public AddYourOrganisation(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		Assert.assertTrue(this.titleContains("Add your organisation",90,driver)); 
	}
	public OrganisationDashboard addOrg(String org){
		orgName.sendKeys(org);
		button.click();
		return new OrganisationDashboard(driver);
	}	
}
