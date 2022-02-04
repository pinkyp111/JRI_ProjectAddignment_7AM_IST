package com.utilities;

import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {

	public static RemoteWebDriver driver;
//	public Properties prop = new Properties();
//	public Locators loc = new Locators();
	// TODO: modify back to 5 seconds
	public int time_in_seconds=5000;
	public String projectDir;
	public String screenshotPath;
	public String className;
	public String methodName;

}
