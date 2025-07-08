package com.homestore.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JSUtils extends Utility {

	/**
	 * This method scrolls the page until the element located by the provided locator is in view,
	 * and returns the WebElement
	 *
	 * @param locator
	 * @return WebElement
	 */
	public static WebElement scrollToElement(By locator) {
		waitForElementToDisplay(10, locator);
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", element);
		return element;
	}
	
	/**
	 * This method scrolls the page until the given WebElement is in view,
	 * and returns the WebElement
	 *
	 * @param element
	 * @return WebElement
	 */
	public static WebElement scrollToElement(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", element);
		return element;
	}
	
	/**
	 * This method clicks the element using JavaScript.
	 *
	 * @param locator
	 * @return WebElement
	 */
	public static WebElement clickElement(By locator) {
		waitForElementToDisplay(5, locator);
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		return element;
	}
	
	/**
	 * This method scrolls to the element and clicks it using JavaScript.
	 *
	 * @param locator
	 * @return WebElement
	 */
	public static WebElement scrollAndClickElement(By locator) {
		WebElement element = scrollToElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		return element;
	}
}
