package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WrapperClass extends BaseClass {

	// Constructor: By default constructor will run first method in a class.
	public WrapperClass() {

		File screenshotPath = new File(".\\screenshots");
		if (screenshotPath.exists()) {
			System.out.println("screenshot folder is exits***************");
		} else {
			screenshotPath.mkdir();
			System.out.println("screenshot folder is NOT available, system has created a Folder ***************");
		}
		System.out.println("**********************************************");

		File ExtentReportsPath = new File(".\\ExtentReports");
		if (ExtentReportsPath.exists()) {
			System.out.println("ExtentReports folder is exits***************");
		} else {
			ExtentReportsPath.mkdir();
			System.out.println("ExtentReports folder is NOT available, system has created a Folder ***************");
		}

		System.out.println("**********************************************");

		File FailedTestsScreenshotsPath = new File(".\\FailedTestsScreenshots");
		if (FailedTestsScreenshotsPath.exists()) {
			System.out.println("FailedTestsScreenshots folder is exits***************");
		} else {
			FailedTestsScreenshotsPath.mkdir();
			System.out.println(
					"FailedTestsScreenshots folder is NOT available, system has created a Folder ***************");
		}

	}

	/**************** Validate the error message ****************/
	public void validateTheErrorMessage(String expectedErrorMessage, By locator) {
		String actualErrorMessage = driver.findElement(locator).getText();
		if (expectedErrorMessage.equals(actualErrorMessage)) {
			System.out.println("Test Case is passed");
		} else {
			System.out.println("Test Case is failed");
		}
	}

	/****************** time stamp ********************/
	public String timeStamp() {
		// Timestamp
		Date d = new Date();
		System.out.println(d);
		DateFormat df = new SimpleDateFormat("yyyyMMMdd_HHmmss");
		String timeStamp = df.format(d);
		return timeStamp;
	}

	/******************
	 * Validate the element is present on DOM(Document Object Model) [Current
	 * Screen]
	 ******/
	public void validateWebelementPresent(By locator) {
		if (driver.findElements(locator).size() > 0) {
			System.out.println("The given locator has present on screen, the test case has passed");
		} else {
			System.out.println("The given locator has NOT present on screen, the test case has failed");
		}
	}

	/***************** get the element text by using any locator *****************/
	public String getTheElementTextUsingAnyLocator(By locator) {
		WebElement element = driver.findElement(locator);

		String eleText = driver.findElement(locator).getText();
		System.out.println(eleText);
		return eleText;
	}

	/************* get all the hyperlinks **************/

	public void getAllHyperLinksAndPrint() {
		List<WebElement> allHyperLinks = driver.findElements(By.tagName("a"));
		int linksCount = allHyperLinks.size();
		System.out.println(linksCount);// 80
		for (WebElement abc : allHyperLinks) {
			System.out.println(abc.getText());
		}

	}

	public void chromeBrowserLaunch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	public void firefoxBrowserLaunch() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	public void edgeBrowserLaunch() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}

	public void operaBrowserLaunch() {
		WebDriverManager.operadriver().setup();
		driver = new OperaDriver();
	}

	public void multibrowserlaunch(String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			chromeBrowserLaunch();
		} else if (browserName.equalsIgnoreCase("edge")) {
			edgeBrowserLaunch();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			firefoxBrowserLaunch();
		} else if (browserName.equalsIgnoreCase("opera")) {
			operaBrowserLaunch();
		} else if (browserName.equalsIgnoreCase("HtmlUnitDriver")) {
			// Declaring and initialising the HtmlUnitWebDriver (HEADLESSbROWSER)
			// HtmlUnitDriver unitDriver = new HtmlUnitDriver();
			// open demo site webpage
			// unitDriver.get("https://abc.com/");
		} else {
			System.out.println("******Please check the browerName******");
		}

	}

	/*******
	 * SendKeys
	 * 
	 * @throws Exception
	 ************************/
	public void sendkeysByAnyLocator(By locator, String inputData) throws Exception {
		WebElement element = driver.findElement(locator);
		// isDisplay
		if (element.isDisplayed()) {
			// isEnabled
			if (element.isEnabled()) {
				highlightElement(element);
				element.clear();
				highlightElement(element);
				element.sendKeys(inputData);
			} else {
				System.out.println("Element is not enabled state, please check the locator*******");
			}
		} else {
			System.out.println("Element is not displayed, please check the locator*******");
		}

	}

	/*******
	 * Click
	 * 
	 * @throws Exception
	 ************************/
	public void clickByAnyLocator(By locator) throws Exception {
		WebElement element = driver.findElement(locator);
		// isDisplay
		if (element.isDisplayed()) {
			// isEnabled
			if (element.isEnabled()) {
				highlightElement(element);
				element.click();
				// TODO:: implicitWait(10);
				implicitWait(1);
			} else {
				System.out.println("Element is not enabled state, please check the locator*******");
			}
		} else {
			System.out.println("Element is not displayed, please check the locator*******");
		}

	}

	public void clickUsingJavaScript(By locator) throws Exception {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		highlightElement(element);
		executor.executeScript("arguments[0].click();", element);

	}

	public void highlightElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);
	}

	/*********** timestamp **********/
	public String timestamp() {
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("ddMMMyyy_HHmmss");
		String timeTamp = df.format(d);
		return timeTamp;
	}

	/*****
	 * takescreenshot
	 * 
	 * @throws Exception
	 ************/
	public void takeScreenshot() throws Exception {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = ".\\Screenshots\\";
		FileHandler.copy(scrFile, new File(screenshotPath + "Nordstrom" + timestamp() + ".PNG"));
		System.out.println("Screenshot taken*** ");
	}

	public void takeScreenshot(ITestResult res) throws Exception {
		projectDir = System.getProperty("user.dir");
		screenshotPath = projectDir + "\\Screenshots\\";
		className = res.getTestClass().getName().trim();
		methodName = res.getName().trim();
		// STATUS_PackageName.ClassName_MethodName_Timestamp.PNG
		if (res.getStatus() == ITestResult.SUCCESS) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File successPath = new File(
					screenshotPath + "PASS_" + className + "_" + methodName + "_" + timestamp() + ".PNG");
			FileHandler.copy(scrFile, successPath);
			System.out.println("SUCCESS :: Screenshot saved at : " + successPath.getAbsolutePath());
		}
		if (res.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File failPath = new File(
					screenshotPath + "FAIL_" + className + "_" + methodName + "_" + timestamp() + ".PNG");
			FileHandler.copy(scrFile, failPath);
			System.out.println("FAILURE :: Screenshot saved at : " + failPath.getAbsolutePath());
		}

	}

	/****************** Dropdown selection **************************************/

	public void selectByVisibleText(By locater, String visibleText) {

		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByVisibleText(visibleText);
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void selectByIndex(By locater, int index) {
		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByIndex(index);
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void selectByValue(By locater, String value) {
		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByValue(value);
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void printAllDropdownValues(By locater) {
		WebElement element = driver.findElement(locater);

		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				List<WebElement> dropdownValues = dropdown.getOptions();
				// Print the size of dropdown values
				System.out.println(dropdownValues.size());
				// Print the dropdown values
				for (WebElement allvalue : dropdownValues) {
					System.out.println(allvalue.getText());
				}
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void selectCustomiseOptionFromTheDropdownValues(By locater, String visibleText) {
		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {

				Select dropdown = new Select(element);
				List<WebElement> dropdownValues = dropdown.getOptions();
				// Print the size of dropdown values
				System.out.println(dropdownValues.size());
				// Print the dropdown values
				for (int i = 0; i < dropdownValues.size(); i++) {
					System.out.println(dropdownValues.get(i).getText());

					// Select Aug option from the dropdown
					if (dropdownValues.get(i).getText().equals(visibleText)) {
						dropdown.selectByIndex(i);
						break;
					}
				}

			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	/************** Alert Handle *************************/
	public void alertHandleByAccept() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Alert text is: " + alertText);
		alert.accept();
	}

	public void alertHandleByDismiss() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Alert text is: " + alertText);
		alert.dismiss();
	}

	/************
	 * popupHandle
	 * 
	 * @throws InterruptedException
	 *********************************/
	public void popupHandleToCloseTheChildWindow() throws InterruptedException {
		// get the main windown name
		String mainWindowName = driver.getWindowHandle();
		System.out.println("mainWindowName:" + mainWindowName);

		Set<String> allWindowNames = driver.getWindowHandles();
		System.out.println("allWindowNames:" + allWindowNames);

		// Close the child window (popups)
		for (String abc : allWindowNames) {// 2
			// validate the window name is parent window /Child window?
			if (!mainWindowName.equals(abc)) {
				// switch to child window
				driver.switchTo().window(abc);
				Thread.sleep(3000);
				// Close my child window
				driver.close();
			}
		}
		// move cursor point to back to mainwindow
		driver.switchTo().window(mainWindowName);
	}

	public void navigateToPopupWindow() throws InterruptedException {
		// get the main windown name
		String mainWindowName = driver.getWindowHandle();
		System.out.println("mainWindowName:" + mainWindowName);

		Set<String> allWindowNames = driver.getWindowHandles();// 4
		System.out.println("allWindowNames:" + allWindowNames);

		// Close the child window (popups)
		// for (int i = 0; i < array.length; i++) { }
		for (String string : allWindowNames) {
			// validate the window name is parent window /Child window?
			if (!mainWindowName.equals(string)) {
				// switch to child window
				driver.switchTo().window(string);
				Thread.sleep(3000);
			}
		}

	}

	/*********** SwithchToWindow using Tab ***************************/
	public void switchToNewTab() {
		// Get the current window handle
		String windowHandle = driver.getWindowHandle();// abc

		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));

		// Switch back to original window
		// driver.switchTo().window(windowHandle);
	}

	/***********
	 * SwithchToWindow using Tab then close the New Tab againg back to Parent Window
	 ***************************/
	public void switchAndCloseNewTab() {
		// Get the current window handle
		String windowHandle = driver.getWindowHandle();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		// Close the newly Opened Window
		driver.close();
		// Switch back to original window
		driver.switchTo().window(windowHandle);
	}

	/******************** Frames Handling *******************/

	public int iframeCount() {
		driver.switchTo().defaultContent();
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		int numberOfFrames = 0;
		numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("Number of iframes on the page are: " + numberOfFrames);
		return numberOfFrames;
	}

	public void switchToFrameByInt(int i) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(i);
	}

	public int loopAllFramesForElement(By locator) {

		int elementpresenceCount = 0;
		int loop = 0;
		int maxFramaecount = iframeCount();// 6
		// if given locater has present on webpage, then the element size would be '1'
		// else '0'
		elementpresenceCount = driver.findElements(locator).size();// 0
		while (elementpresenceCount == 0 && loop < maxFramaecount) {
			try {
				switchToFrameByInt(loop);
				elementpresenceCount = driver.findElements(locator).size();// 1
				System.out.println("Try LoopAllframesAndReturnWebEL : " + loop + "; ElementpresenceCount: "
						+ elementpresenceCount);
				if (elementpresenceCount > 0 || loop > maxFramaecount) {
					break;
				}
			} catch (Exception ex) {
				System.out.println("Catch LoopAllframesAndReturnWebEL Old: " + loop + "; " + ex);
			}
			loop++;// 1
		}
		return elementpresenceCount;
	}

	/************ waits inselenium ***********************/
	public void explicitWait(By locator, int timeinSeconds) {
		WebElement element = driver.findElement(locator);
		// webdriver wait (Explicit wait)
		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(timeinSeconds));
		ww.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementVisible(By locator, int time_in_seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time_in_seconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementToBeClickable(By locator, int time_in_seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time_in_seconds));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForAlert(int time_in_seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time_in_seconds));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		System.out.println("Implicit wait method used for " + time + " seconds***");

	}

	/***
	 * instead of fluent wait use customized While loop statement**@throws Exception
	 *****/

	public void waitforElement(By locater, int time_in_ms) throws Exception {
		int i = 0;
		while (driver.findElements(locater).size() < 1) {
			Thread.sleep(time_in_ms);
			System.out.println("Wait for the element***************");
			// Suppose system has not present the element it will repeat 30 time
			// then stop
			// the execution using break
			if (i > 5) {
				System.out.println("Element is not present");
				break;
			}
			i++;
		}
	}

//		public void Fluent_Wait(final WebElement El) {
	//
//			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
//					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
//			WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
//				public WebElement apply(WebDriver driver) {
//					return El;
//				}
//			});
//		}
	/************************* Actions ************/

	public void moveToOnElement(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).build().perform();
		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
	}

	public void mouseHoverClickandHold(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.clickAndHold(element).build().perform();
		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void mouseHoverContextClick(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.contextClick(element).build().perform();

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void doubleClick(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.doubleClick(element).build().perform();

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void dragandDrop(By sourceelementLocator, By destinationelementLocator) {
		try {
			WebElement sourceElement = driver.findElement(sourceelementLocator);
			WebElement destinationElement = driver.findElement(destinationelementLocator);

			if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
				Actions action = new Actions(driver);
				action.dragAndDrop(sourceElement, destinationElement).build().perform();
			} else {
				System.out.println("Element(s) was not displayed to drag / drop");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + sourceelementLocator + "or" + destinationelementLocator
					+ "is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sourceelementLocator + "or" + destinationelementLocator
					+ " was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error occurred while performing drag and drop operation " + e.getStackTrace());
		}
	}

	public void dragandDropUsingJavaScript(By sourceelementLocator, By destinationelementLocator) {
		final String java_script = "var src=arguments[0],tgt=arguments[1];var dataTransfer={dropEffe"
				+ "ct:'',effectAllowed:'all',files:[],items:{},types:[],setData:fun"
				+ "ction(format,data){this.items[format]=data;this.types.append(for"
				+ "mat);},getData:function(format){return this.items[format];},clea"
				+ "rData:function(format){}};var emit=function(event,target){var ev"
				+ "t=document.createEvent('Event');evt.initEvent(event,true,false);"
				+ "evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);};emit('"
				+ "dragstart',src);emit('dragenter',tgt);emit('dragover',tgt);emit("
				+ "'drop',tgt);emit('dragend',src);";

		((JavascriptExecutor) driver).executeScript(java_script, sourceelementLocator, destinationelementLocator);
	}

	/** Verify Broken Links In a Web Page **************/
	public static void verifylink(String linkurl) {
		try {
			URL url = new URL(linkurl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(3000);
			connection.connect();
			if (connection.getResponseCode() == 200) {
				System.out.println(linkurl + " - " + connection.getResponseMessage());
			}
			if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linkurl + "-" + connection.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***********
	 * Get Random number with in the range
	 ********************************/
	public int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public int RandomNo(int Max) {
		int randomInt = 0;
		Random rg = new Random();
		for (int idx = 1; idx <= Max; ++idx) {
			randomInt = rg.nextInt(Max);
			if (randomInt == 0) {
				randomInt = 1;
			}
			System.out.println("Generated : " + randomInt);
		}
		return randomInt;
	}

	/****************** Check Multiple Checkboxes ************************/

	public void safeSelectCheckBoxes(int waitTime, WebElement... elements) throws Exception {
		WebElement checkElement = null;
		try {
			if (elements.length > 0) {
				for (WebElement currentElement : elements) {
					checkElement = currentElement;
					WebDriverWait wait = new WebDriverWait(driver, waitTime);
					wait.until(ExpectedConditions.elementToBeClickable(currentElement));

					WebElement checkBox = currentElement;
					if (checkBox.isSelected())
						System.out.println("CheckBox " + currentElement + " is already selected");
					else
						checkBox.click();
				}
			} else {
				System.out.println("Expected atleast one element as argument to safeSelectCheckboxes function");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println(
					"Element - " + checkElement + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + checkElement + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to select checkbox " + e.getStackTrace());
		}
	}

	/******** Assertion using TestNG ******************/

	public void assertByElementText(By locator, String expectedText) {
		WebElement element = driver.findElement(locator);
		try {
			String ActualMsg = element.getText();
			String ExpectedMsg = expectedText;
			Assert.assertEquals(ExpectedMsg, ActualMsg);
			System.out.println("************Assertion Done**********");
		} catch (StaleElementReferenceException e) {
			System.out.println("Element - " + element + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to find element " + e.getStackTrace());
		}
	}

	public void assertEquals(String receivedMessage, String expectedMessage) throws Exception {
		Thread.sleep(2000);
		Assert.assertEquals(receivedMessage, expectedMessage);
	}

	/******************* To Clear the edit/Text box ***********/
	public void clearText(WebElement element) {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				element.clear();
			} else {
				System.out.println("Element existance and enabled status Failed");
				Reporter.log("Element existance and enabled status Failed");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element - " + element + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());

		}
	}

	/************************
	 * ScrollToElement
	 * 
	 * @throws Exception
	 **********************/

	/****************** ScrollToElementBottom *****************************/

	public void ScrollToElementBottom(WebElement element) {
		try {
			System.out.println("***ScrollToElementBottom:  ***");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		} catch (StaleElementReferenceException e) {
			System.out.println("Element - " + element + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());

		}
	}

	public void scrollintoviewelement(WebElement element) {
		try {
			// JavascriptExecutor js = (JavascriptExecutor) driver;
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500);
		} catch (StaleElementReferenceException e) {
			System.out.println("Element - " + element + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());
		}

	}

	/********* Read Data from Properties file *********************/

	Properties prop = new Properties();

	// to get the data from Property file
	public void loaddata(String path) {

		File file = new File(path);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void readFileAndReplaceData(String filePath, String toReplace, String replacementString) {

		Path path = Paths.get(filePath);
		Charset charset = StandardCharsets.UTF_8;

		String content;
		try {
			content = new String(Files.readAllBytes(path), charset);
			content = content.replaceAll(toReplace, replacementString);
			Files.write(path, content.getBytes(charset));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getdata(String key) {
		String keyvlaue = null;
		try {

			keyvlaue = prop.getProperty(key);
			System.out.println("keyvlaue: " + keyvlaue);

		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());
		}
		return keyvlaue;

	}

	/********************** TestDataPathOf ******************/
	public String TestDataPathOf(String TestDataFileName) throws IOException {

		String TestDataPath = ".\\TestData\\" + TestDataFileName;
		return TestDataPath;

	}

	public boolean elmentisdisplayed(WebElement element) {
		boolean elementdisplayedflag = false;
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				elementdisplayedflag = true;
			} else {
				System.out.println("Element existance and enabled status Failed");
			}

		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());
		}
		return elementdisplayedflag;

	}

	/******************* Get webElement attribute value ****************/
	public String elementgetAttributevalue(WebElement element, String p_in_attributename) {
		String attributevalue = "";
		try {
			if (element.isDisplayed()) {
				attributevalue = element.getAttribute(p_in_attributename);
			} else {
				System.out.println("Element existance and enabled status Failed");
			}

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
		return attributevalue;
	}

	/********************** Get the webelement text ************************/
	public String getelementtext(WebElement element) {
		String textvalue = "";
		try {
			if (element.isDisplayed()) {
				textvalue = element.getText();
			} else {
				System.out.println("Element existance and enabled status Failed");
			}

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
		return textvalue;
	}

	public void check_Checkbox(WebElement element) {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				if (element.isSelected()) {
					System.out.println("Check box already selected");
				} else {
					element.click();
				}

			} else {
				System.out.println("Check box existance and enabled status Failed");
			}
		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());
		}
	}

//		/******************* Get & verify URLStatus *******************/
//		public void verifyURLStatus(String URL) {
//			// http://roadtoautomation.blogspot.in/2013/04/road-to-verify-200-response-code-of-web.html
//			try {
//				WebClient webClient = new WebClient();
//				HtmlPage htmlPage = webClient.getPage(URL);
//				System.out.println("Status Code: " + htmlPage.getWebResponse().getStatusCode() + "; Web Response: "
//						+ htmlPage.getWebResponse().getStatusMessage());
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

	/**************** GetYLocationOfElement *******************/
	public int GetYLocationOfElement(WebElement element) {
		System.out.println("***GetYLocationOfElement: Don't forget to move to your frame.***");
		Point point = element.getLocation();
		int y = point.getY();
		System.out.println("Y Coordination of the Element: " + y);
		return y;
	}

	public int GetXLocationOfElement(WebElement element) {
		System.out.println("***GetXLocationOfElement: Don't forget to move to your frame.***");
		Point point = element.getLocation();
		int x = point.getX();
		System.out.println("X Coordination of the Element: " + x);
		return x;
	}

	public void ScrollToXY(int x, int y) {
		System.out.println(
				"***ScrollToXY: Move to default Content explicitly. Otherwise it won't work. 'Call switchToDefaultFrame and don't forget to move to your frame.' ***");
		String script = "window.scrollTo(" + x + "," + y + ");";
		((JavascriptExecutor) driver).executeScript(script);
	}

	/*********** HighlightElement ******************/
	public void HighlightElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);
	}

	/***********************
	 * String functions
	 **********************************/
	public String RemoveSpaceAndNonAlphaFromString(String inputString) {
		String Output = inputString.replaceAll("[^a-zA-Z]", "");
		return Output;
	}

	public String RemoveNonAlphaExcludingSpaceFromString(String inputString) {
		String Output = inputString.replaceAll("[^a-z A-Z]", "");
		return Output;
	}

	public String RemoveNonAlphaNumericFromString(String inputString) {
		String Output = inputString.replaceAll("[^a-z A-Z0-9]", "");
		return Output;
	}

	public String RemoveNonNumericFromString(String inputString) {
		String Output = inputString.replaceAll("[^0-9]", "");
		return Output;
	}

	public String RemoveSpaceAndAlpha(String inputString) {
		String Output = inputString.replaceAll("[a-z A-Z]", "");
		return Output;
	}

	public void SafeClick(final By locator) throws InterruptedException {
		WebElement wesafe = driver.findElement(locator);
		Boolean flag = false;
		int loop = 0;
		while (flag == false) {
			try {
				Thread.sleep(500);
				wesafe.click();
				Thread.sleep(2000);
				flag = true;
				System.out.println("Try: SafeClick: " + loop);
				loop++;
				if (loop > 12) {
					break;
				}
			} catch (Exception ex) {
				flag = false;
				Thread.sleep(1000);
				System.out.println("Catch: SafeClick: " + loop);
			}

		}
	}

	public void clearByAnyLocator(By locator) {
		WebElement ele = driver.findElement(locator);
		try {
			if (ele.isDisplayed() && ele.isEnabled()) {
				ele.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
