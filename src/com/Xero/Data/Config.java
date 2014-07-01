package com.Xero.Data;

public class Config {
	
	public static String iedriverpath ="C:\\Users\\Rk\\Desktop\\Automation Selenium Drivers\\Work\\workspace\\Reference Libraries\\Task\\IEDriverServer.exe";
	
	//To set the driver for WebDriver IE
	
	public static void initialize()
	{
		System.setProperty("webdriver.ie.driver",iedriverpath);
	}

}
