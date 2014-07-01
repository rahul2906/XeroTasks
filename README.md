XeroTasks
=========

ReadMe file

Xero Automation Exercise

This document provides a quick overview of the approach taken to complete the 2 tasks provided.

Software Used:
Tools Used- Selenium(2.42.0), Eclipse IDE(Kepler), TestNG Framework(6.8)
Language - Java
Browsers - Internet Explorer 32 bit(10.0), Firefox(30.0)

Configuration requirement:
The JAR files required for Selenium and TestNG have been placed in the lib folder.
The Data requirements are put into the Data package with Config and TestData. The Config file requires updates for the location of the IE driver.

Package Structure: The Source files have been divided into 5 packages.

Data: These are used to maintain the data used for logging in and also contains the Configuration file for the drivers.
DataObjects: Used for maintaining data in a logical manner such as Inventory and Login Credentials.
GenericFunctions: Used for keeping all the generic functions for usage across page objects
PageObjects: Each page in the flow of Xero has been broken down to Pages. Each of these pages have been made as a page object with all the variables and locators present at a single place. This allows the code to be maintainable and reduces duplicate code. It also contains the functions for each of the pages(Such as navigation)
Tests: These are the 2 class files representing the 2 tasks which have been requested as part of this exercise.

Task 1: Sign up for a Demo NZ organisation from the Xero Marketing Site. Within the Demo NZ organisation, navigate to the Sales screen and then to the Repeating tab.

The Task 1 class file contains the functions required for navigation of the flow for registering a new customer and navigating to the Repeating tab. There have been assertions put at each page to verify the flow of the pages. A random email ID is generated in the script of the a website (www.Mailinator.com). This website has been chosen as there is no sign up required for receiving the mails. Once the customer completes the details a mail is sent to the given ID and the link is retrieved from the website. This is used to complete the remaining part of the flow of selecting a Demo Organisation and navigating to the repeating tab.

Within this flow, there are 2 possible sub paths. One path allows the user to create a demo organisation without creating a Organisation while the other forces the user to create a new organisation before allowing to view the demo organisation. Both the flows have been handled in the code.


Task 2: Using whatever language you prefer (.NET is ideal, but we won't hold it against you if you use your favourite open source cutting edge framework, provided we can easily execute your tests afterwards.)  Create some tests that execute the Repeating Invoices UI component in the manner it is intended.

For the Repeating Invoices component I have created 4 sample tests.

Test 1 : To Verify the validation error messages received if all the mandatory fields are left blank in the Page.

Test 2: To Verify the list of drop down values which are present as part of the Items dropdown on the "New Repeating Invoice" Page. The drop down values are picked up from the Inventory page and then compared with the values in the New Repeating Invoice page.

Test 3: To Verify the values being populated in the description, Account and Price columns of the Items table in the "New Repeating Invoice" Page. The values are picked up from the Inventory page and then compared with the values in the New Repeating Invoice page.

Test 4: Test a Successful flow of creation of a new Repeating invoice and verification that it has been successfully created.

There can be multiple variations of these tests created with the existing functions. For testing purposes I have used FireFox Browser and Internet Explorer.

To run the Tests,"ToExecuteTests.xml" is used. The parameter browser is used to decide on the browser(FF or IE)
