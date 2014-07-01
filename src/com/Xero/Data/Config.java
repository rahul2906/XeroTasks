package com.Xero.Data;

public class Config {
	
	public static String iedriverpath ="C:\\Users\\Rk\\Desktop\\Automation Selenium Drivers\\Work\\workspace\\Reference Libraries\\Task\\IEDriverServer.exe";
	
	static
	{
		System.setProperty("webdriver.ie.driver",iedriverpath);
	}

}
