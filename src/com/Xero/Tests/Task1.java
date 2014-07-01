package com.Xero.Tests;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Xero.Data.TestData;
import com.Xero.DataObjects.Login;
import com.Xero.PageObjects.*;


public class Task1 {

	WebDriver driver;
	@BeforeTest
	public void createsession()	{
		//Create a new session of FireFoxDriver
		this.driver = new FirefoxDriver();	
	}
	
	@Test
	public void task1() {
		//Generate random email Address for registration
		String email="xero"+(new Random().nextInt(25000));;
		
		System.out.println();
		
		//Enter data and create new Xero Credentials
		Login credentials = new Login(TestData.firstName,TestData.lastName,email+"@mailinator.com",TestData.phone);
		CreateNewLogin registeruser= new CreateNewLogin(driver);
		registeruser.createNewUsersuccess(credentials);

		//Open the mailbox "www.mailinator.com" and retrieve the Sign up Link
		MailerInbox mail= new MailerInbox(driver,email);
		mail.clickFirstMail();
		String mailerLink = mail.getLink();

		//Open Account Activation page and set new password
		ActivateAccount activate = new ActivateAccount(driver,mailerLink);
		
		//Setup the password as Test1234
		Object object1 = activate.activateAccount(TestData.password);
		OrganisationDashboard org;
		/*Two possible pages can be sent back
		1. AddYourOrgansiation Page where the website forces to create a organisation customer
		2. AddYourOrgainsation2 Page where the website allows to directly goto the Demo Organisation.
		Below logic checks which page gas been returned and proceeds to goto the Demo organisation page
		*/
		if(object1 instanceof AddYourOrganisation)
		{
			//Type Cast the object to the page and Add a new company called Test Company
			AddYourOrganisation addOrg = (AddYourOrganisation) object1;
			org = addOrg.addOrg("Test Company");
			Assert.assertTrue(org.returnWelcomePopUpWindowHeader());
			
			//Goto MyXeroHomePage and click on Demo link
			org.closeWelcomePopUpWindow();;
			MyXeroHome home =org.gotoXeroHomePage();
			org=(OrganisationDashboard)home.gotoDemoOrganisation();
		}
		else
		{	//If the demo link is already available click on it and proceed  
			AddYourOrganisation2 add = (AddYourOrganisation2) object1;
		org = add.gotoDemolink();
		}
		
		//Verify the Demo company is returned
		Assert.assertTrue(org.returnPageHeader().contains("Demo Company (NZ)"));
		
		//Navigate to Sales Page
		SalesDashboard sales = org.NavigateToSales();
		Assert.assertTrue(sales.returnHeader().contentEquals("Sales"));
		
		//Navigate to Repeating Invoices Page
		sales.navigateRepeatingInvoices();
	}
	@AfterTest
	public void closing()
	{
		//Close and quit the driver
		this.driver.close();
		this.driver.quit();
	}
}


