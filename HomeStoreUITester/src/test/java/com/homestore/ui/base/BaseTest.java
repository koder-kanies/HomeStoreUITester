package com.homestore.ui.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.homestore.ui.config.TestConfig;
import com.homestore.ui.pages.HomePage;
import com.homestore.ui.utils.Utility;

public class BaseTest {
	
	protected WebDriver driver;
	protected BasePage basePage;
	protected HomePage homePage;
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void loadApplication() {
		// Load the url and switch to application frame
		driver.get(TestConfig.BASE_URL);
		driver.switchTo().frame("theme-demo");
		
		// Inititalize BasePage, HomePage and set driver for Utility
		basePage = new BasePage();
		BasePage.setDriver(driver);
		Utility.setUtilityDriver();
		homePage = new HomePage();
	}
	
	public void takeFailureScreenshot() {
		// TODO
	}
	
	@AfterMethod
	public void cleanUp() {
	    driver.manage().deleteAllCookies();
	}
	
	@AfterClass
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
}
