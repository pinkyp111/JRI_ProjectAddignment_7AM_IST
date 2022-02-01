package com.objectrepository;

import org.openqa.selenium.By;

public class Locators {

	public final By JRI_HomePage_Sigin_Link = By.id("jriTop_signin9");
	public final By JRI_HomePage_CreateAnAccount_Link = By.id("signup-link9");
	public final By JRI_HomePage_Name_EditBox = By.name("signup_name");
	public final By JRI_HomePage_Mobile_EditBox = By.id("signup_mobileno");
	public final By JRI_HomePage_Email_EditBox = By.id("signup_email");
	public final By JRI_HomePage_Password_EditBox = By.id("signup_password");
	public final By JRI_HomePage_Newsletters_CheckBox = By.id("checkbox1");
	public final By JRI_HomePage_Terms_CheckBox = By.id("checkbox");
	public final By JRI_HomePage_CreateAccount_Button = By.id("imgbtnSubmit");

	
	//Invalid error messages
	public final By JRI_HomePage_Name_ErroeMsg = By.xpath("//td[@id='nameTD']/span");
	public final By JRI_HomePage_Mobile_ErroeMsg = By.xpath("//td[@id='mobilenoTD']/span");


	


}
