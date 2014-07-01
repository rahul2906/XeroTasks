package com.Xero.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Xero.DataObjects.Login;
import com.Xero.GenericFunctions.GenericFunctions;

public class CreateNewLogin extends GenericFunctions {

	private WebDriver driver;
	@FindBy(name="FirstName") 
	public static WebElement firstName;
	@FindBy(name="LastName") 
	public static WebElement lastName;
	@FindBy(name="EmailAddress") 
	public static WebElement emailAddress;
	@FindBy(name="PhoneNumber") 
	public static WebElement phoneNumber;
	@FindBy(name="TermsAccepted")
	public static WebElement termsAccepted;
	@FindBy(css= "button[type='submit']")
	public static WebElement submitButton;

	public CreateNewLogin(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		driver.get("https://www.xero.com/nz/signup/");
		Assert.assertTrue(this.titleContains("Signup for Xero & free trial | Xero Accounting Software",60,driver)); 
	}
	void enterFirstName(String fName)
	{
		firstName.sendKeys(fName);	
	}
	void enterLastName(String lName)
	{
		lastName.sendKeys(lName);	
	}
	void enterEmailAddress(String email)
	{
		emailAddress.sendKeys(email);	
	}
	void enterPhoneNumber(String phone)
	{
		phoneNumber.sendKeys(phone);	
	}
	void clickAcceptTerms()
	{
		termsAccepted.click();   
	}
	void clickSubmit()
	{
		submitButton.click();  
	}
	public EmailSent createNewUsersuccess(Login data)
	{    
		enterFirstName(data.getFirstName());
		enterLastName(data.getLastName());
		enterEmailAddress(data.getEmailAddress());
		enterPhoneNumber(data.getphoneNumber());
		clickAcceptTerms();
		clickSubmit();
		return new EmailSent(driver);
	}
}