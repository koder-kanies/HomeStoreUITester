package com.homestore.ui.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
	
	public static WebDriver driver;
	
	public static void setDriver(WebDriver driver) {
		BasePage.driver = driver;
	}
	
	// Method to find an element
	public static WebElement find(By locator) {
		return driver.findElement(locator);
	}

    // Method to click an element
    public void click(By locator) {
        find(locator).click();
    }

    // Method to send text to an input field
    public void sendKeys(By locator, String text) {
        find(locator).sendKeys(text);
    }

}
