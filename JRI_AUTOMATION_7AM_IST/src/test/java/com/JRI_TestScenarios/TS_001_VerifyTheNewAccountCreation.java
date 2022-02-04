package com.JRI_TestScenarios;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.objectrepository.Locators;
import com.utilities.WrapperClass;

public class TS_001_VerifyTheNewAccountCreation extends WrapperClass {
	Locators loc = new Locators();

	@BeforeClass
	public void beforeClass() {
		chromeBrowserLaunch();
	}

	@Test
	public void tc_001_Invoke_the_JRI_Home_page() throws Exception {
		loaddata("./src/test/resources/testdata/inputdata.properties");
		driver.get(getdata("JRI_HomePage_URL"));
		// Develop the code from here

	}

	@Test
	public void tc_002_Verify_the_Create_Newaccount_page_will_display() throws Exception {

		// TODO:
//		waitForElementToBeClickable(loc.JRI_HomePage_CreateAnAccount_Link, time_in_seconds);
		clickByAnyLocator(loc.JRI_HomePage_CreateAnAccount_Link);
		System.out.println("The current URL" + driver.getCurrentUrl());
		// TODO
//		waitForElementVisible(loc.JRI_HomePage_CreateAccount_Button, time_in_seconds);

	}

//	@Test
	public void tc_003_Verify_the_Username_error_message_display() throws Exception {
		// TODO
//		waitForElementToBeClickable(loc.JRI_HomePage_CreateAccount_Button, time_in_seconds);
		clickByAnyLocator(loc.JRI_HomePage_CreateAccount_Button);
		String username_error = driver.findElement(By.id("nameTD")).getText();
		System.out.println("***Error message is***" + username_error);

	}

//	@Test
	public void tc_004_Verify_Mobile_No_error_message_display() throws Exception {
		loaddata("./src/test/resources/testdata/inputdata.properties");
		String name = getdata("Name");
		sendkeysByAnyLocator(loc.JRI_HomePage_Name_EditBox, name);
		clickByAnyLocator(loc.JRI_HomePage_CreateAccount_Button);
		String mobileno_error = driver.findElement(By.id("mobilenoTD")).getText();
		System.out.println("***Error message is***" + mobileno_error);

	}

//	@Test
	public void tc_005_Verify_Email_error_message_display() throws Exception {
		loaddata("./src/test/resources/testdata/inputdata.properties");
		String name = getdata("Name");
		String mobileno = getdata("Mobile");
		sendkeysByAnyLocator(loc.JRI_HomePage_Name_EditBox, name);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Mobile_EditBox, mobileno);
		clickByAnyLocator(loc.JRI_HomePage_CreateAccount_Button);
		String email_error = driver.findElement(By.id("emailTD")).getText();
		System.out.println("***Error message is***" + email_error);

	}

//	@Test
	public void tc_006_Verify_Password_error_message_display() throws Exception {
		loaddata("./src/test/resources/testdata/inputdata.properties");
		String name = getdata("Name");
		String mobileno = getdata("Mobile");
		String email = getdata("Email");
		sendkeysByAnyLocator(loc.JRI_HomePage_Name_EditBox, name);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Mobile_EditBox, mobileno);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Email_EditBox, email);
		clickByAnyLocator(loc.JRI_HomePage_CreateAccount_Button);
		String password_error = driver.findElement(By.id("passwordTD")).getText();
		System.out.println("***Error message is***" + password_error);
	}


	@Test
	public void tc_007_Verify_Terms_error_message_display() throws Exception {
		System.out.println("%%% Executing TC7");
		loaddata("./src/test/resources/testdata/inputdata.properties");
		String name = getdata("Name");
		String mobileno = getdata("Mobile");
		String email = getdata("Email");
		String password = getdata("Password");

		sendkeysByAnyLocator(loc.JRI_HomePage_Name_EditBox, name);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Mobile_EditBox, mobileno);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Email_EditBox, email);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Password_EditBox, password);
		clickByAnyLocator(loc.JRI_HomePage_CreateAccount_Button);
		String terms_error = driver.findElement(By.id("tdcondition")).getText();
		System.out.println("***Error message is***" + terms_error);

	}

	@AfterMethod
	public void afterMethod(ITestResult res) throws Exception {
		takeScreenshot(res);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
