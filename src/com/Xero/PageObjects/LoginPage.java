package com.Xero.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Xero.GenericFunctions.GenericFunctions;

public class LoginPage extends GenericFunctions {

	private WebDriver driver;

	@FindBy(id="email") 
	public static WebElement emailAddress;
	@FindBy(id="password") 
	public static WebElement password;
	@FindBy(id="submitButton") 
	public static WebElement submitButton;

	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		driver.get("https://login.xero.com/");
		Assert.assertTrue(this.titleContains("Login | Xero Accounting Software",60,driver)); 
	}
	public Object loginSuccess(String email,String pwd)
	{
		emailAddress.sendKeys(email);
		password.sendKeys(pwd);
		submitButton.click();
		waitForNextPage("Login",driver);

		if(driver.getTitle().trim().contains("My Xero | Home"))
		{
			return new MyXeroHome(driver);
		}
		else
			return new OrganisationDashboard(driver);
	}
}
