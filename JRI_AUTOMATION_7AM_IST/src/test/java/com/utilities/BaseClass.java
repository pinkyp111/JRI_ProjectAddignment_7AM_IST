package com.utilities;

import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.objectrepository.Locators;

public class BaseClass {

	public static RemoteWebDriver driver;
	public Properties prop = new Properties();
	public Locators loc = new Locators();
	
	public String projectDir;
	public String screenshotPath;
	public String className;
	public String methodName;

}
