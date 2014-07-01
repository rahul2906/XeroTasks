package com.Xero.DataObjects;

public class Login {

	 String firstName;
	String lastName;
	String emailAddress;
	String phoneNumber;
	//Setter methods
	public Login(String firstName,String lastName,String emailAddress,String phoneNumber)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.emailAddress=emailAddress;
		this.phoneNumber=phoneNumber;
	}
	public void setFirstName(String firstName)
	{
		this.firstName=firstName;
	}
	public void setLastName(String lastName)
	{
		this.lastName=lastName;
	}
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress=emailAddress;
	}
	public void setphoneNumber(String phoneNumber)
	{
		this.phoneNumber=phoneNumber;
	}
	//Getter methods
	public String getFirstName()
	{
		return(this.firstName);
	}
	public String getLastName()
	{
		return(this.lastName);
	}
	public String getEmailAddress()
	{
		return(this.emailAddress);
	}
	public String getphoneNumber()
	{
		return(this.phoneNumber);
	}
}
