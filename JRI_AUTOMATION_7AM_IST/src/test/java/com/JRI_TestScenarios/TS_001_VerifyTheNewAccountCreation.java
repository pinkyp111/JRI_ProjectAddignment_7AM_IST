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
	String userDirectory = System.getProperty("user.dir");
	String JRI_Homepage = null;

	boolean enableOfflineMode = true;
	String jriOfflinePage1Path = null;
	String jriOfflinePage2Path = null;

	@BeforeClass
	public void beforeClass() {
		loaddata("./src/test/resources/testdata/inputdata.properties");
		if (enableOfflineMode) {
			System.out.println("userDirectory=" + userDirectory);
			// load offline url
			jriOfflinePage1Path = getdata("JRI_HomePage_Offline_URL");
			jriOfflinePage1Path = jriOfflinePage1Path.replace("$", userDirectory);
			jriOfflinePage1Path = jriOfflinePage1Path.replace("\\", "/");
			System.out.println("jriOfflinePath=" + jriOfflinePage1Path);

			jriOfflinePage2Path = jriOfflinePage1Path.replace("page1", "page2");
			readFileAndReplaceData(jriOfflinePage1Path.replace("file:///", ""),
					"https://www.justrechargeit.com/SignUp.aspx", jriOfflinePage2Path);
			JRI_Homepage = jriOfflinePage1Path;

		} else {
			// load actual url
			JRI_Homepage = getdata("JRI_HomePage_URL");
		}
		chromeBrowserLaunch();
	}

	@Test
	public void tc_001_Invoke_the_JRI_Home_page() throws Exception {
		System.out.println("%%% Executing TC1");
		driver.get(JRI_Homepage);
		// Develop the code from here

	}

	@Test(dependsOnMethods = "tc_001_Invoke_the_JRI_Home_page")
	public void tc_002_Verify_the_Create_Newaccount_page_will_display() throws Exception {
		System.out.println("%%% Executing TC2");
		// TODO:
//		waitForElementToBeClickable(loc.JRI_HomePage_CreateAnAccount_Link, time_in_seconds);
		clickByAnyLocator(loc.JRI_HomePage_CreateAnAccount_Link);
		System.out.println("The current URL" + driver.getCurrentUrl());
		// TODO
//		waitForElementVisible(loc.JRI_HomePage_CreateAccount_Button, time_in_seconds);

	}

//	@Test
	public void tc_003_Verify_the_Username_error_message_display() throws Exception {
		System.out.println("%%% Executing TC3");
		// TODO
//		waitForElementToBeClickable(loc.JRI_HomePage_CreateAccount_Button, time_in_seconds);
		clickByAnyLocator(loc.JRI_HomePage_CreateAccount_Button);
		String username_error = driver.findElement(By.id("nameTD")).getText();
		System.out.println("***Error message is***" + username_error);

	}

//	@Test
	public void tc_004_Verify_Mobile_No_error_message_display() throws Exception {
		System.out.println("%%% Executing TC4");
		String name = getdata("Name");
		sendkeysByAnyLocator(loc.JRI_HomePage_Name_EditBox, name);
		clickByAnyLocator(loc.JRI_HomePage_CreateAccount_Button);
		String mobileno_error = driver.findElement(By.id("mobilenoTD")).getText();
		System.out.println("***Error message is***" + mobileno_error);

	}

//	@Test
	public void tc_005_Verify_Email_error_message_display() throws Exception {
		System.out.println("%%% Executing TC5");
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
		System.out.println("%%% Executing TC6");
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

	// @Test
	public void tc_007_Verify_Terms_error_message_display() throws Exception {
		System.out.println("%%% Executing TC7");
		String name = getdata("Name");
		String mobileno = getdata("Mobile");
		String email = getdata("Email");
		String password = getdata("Password");

		Thread.sleep(1000);
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

	// @Test
	public void tc_008_Check_the_refresh_button() throws Exception {
		System.out.println("%%% Executing TC8");
		String name = getdata("Name");
		String mobileno = getdata("Mobile");
		String email = getdata("Email");
		String password = getdata("Password");
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Name_EditBox, name);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Mobile_EditBox, mobileno);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Email_EditBox, email);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Password_EditBox, password);
		driver.navigate().refresh();
	}

	// @Test
	public void tc_009_Verify_the_invalid_data_validation_messages() throws Exception {
		System.out.println("%%% Executing TC9");
		String Invalidname = getdata("Invalidname");
		String Invalidmobileno = getdata("Invalidmobile");
		String Invalidemail = getdata("Invalidemail");
		String Invalidpassword = getdata("Invalidpassword");
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Name_EditBox, Invalidname);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Mobile_EditBox, Invalidmobileno);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Email_EditBox, Invalidemail);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Password_EditBox, Invalidpassword);
		clickByAnyLocator(loc.JRI_HomePage_CreateAccount_Button);
	}

	@Test(dependsOnMethods = "tc_002_Verify_the_Create_Newaccount_page_will_display")
	public void tc_0010_Check_user_willbe_able_to_createaccount_with_valid_data() throws Exception {
		System.out.println("%%% Executing TC10");
		String name = getdata("Name");
		String mobileno = getdata("Mobile");
		String email = getdata("Email");
		String password = getdata("Password");
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Name_EditBox, name);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Mobile_EditBox, mobileno);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Email_EditBox, email);
		Thread.sleep(1000);
		sendkeysByAnyLocator(loc.JRI_HomePage_Password_EditBox, password);
		clickByAnyLocator(loc.JRI_HomePage_Terms_CheckBox);

		clickByAnyLocator(loc.JRI_HomePage_CreateAccount_Button);
	}

	@AfterMethod
	public void afterMethod(ITestResult res) throws Exception {
		takeScreenshot(res);
	}

	@AfterClass
	public void afterClass() {
		if (enableOfflineMode) {
			readFileAndReplaceData(jriOfflinePage1Path.replace("file:///", ""), jriOfflinePage2Path,
					"https://www.justrechargeit.com/SignUp.aspx");
		}
		// driver.quit();
	}

}
