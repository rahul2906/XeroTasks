package com.Xero.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.Xero.GenericFunctions.GenericFunctions;

public class MailerInbox extends GenericFunctions{

	WebDriver driver;
	By firstMail= By.cssSelector("div[class='from ng-binding']");
	By regLink = By.cssSelector("a[href*='https://login.xero.com']");
	Integer mailFrameID=1;

	public MailerInbox(WebDriver driver, String email)
	{
		this.driver=driver;
		driver.get("http://mailinator.com/inbox.jsp?to="+email);
		Assert.assertTrue(driver.getTitle().contains("Mailinator")); 
	}
	
	public void clickFirstMail() {
		this.getWhenVisible(firstMail,30,driver).click();
	}
	
	public String getLink() {
		//this.getWhenVisible("rendermail",30,driver);
		this.sleep(5);
		driver.switchTo().frame(driver.findElement(By.cssSelector("#mailshowdivbody > iframe")));
		return(driver.findElement(regLink).getText());	
	}



}
