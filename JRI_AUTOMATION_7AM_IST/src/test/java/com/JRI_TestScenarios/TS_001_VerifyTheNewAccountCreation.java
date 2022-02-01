package com.JRI_TestScenarios;

import org.testng.annotations.Test;

import com.objectrepository.Locators;
import com.utilities.WrapperClass;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

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
		loaddata("./src/test/resources/testdata/inputdata.properties");
		driver.get(getdata("JRI_HomePage_URL"));
		// Develop the code from here

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
