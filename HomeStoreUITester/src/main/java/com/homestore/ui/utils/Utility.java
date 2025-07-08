package com.homestore.ui.utils;

import java.time.Duration;
import java.time.Instant;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.homestore.ui.base.BasePage;

public class Utility {
	
public static WebDriver driver;
	
	public static void setUtilityDriver() {
		driver = BasePage.driver;
	}
	
	/**
     * Method to wait for an element to be visible
     * 
     * @param seconds
     * @param locator
     */
	public static void waitForElementToDisplay(int seconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));		
	}
	
	/**
     * Test waits for provided duration
     * 
     * @param seconds
     */
	public static void wait_inSeconds(int seconds) {
		long expectedEpochTime = Instant.now().getEpochSecond() + seconds;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until((WebDriver driver1) -> {
            // Get the current epoch time after waiting
            long currentEpochTimeAfterWait = Instant.now().getEpochSecond();
            return currentEpochTimeAfterWait == expectedEpochTime;
        });
		
		Reporter.log("Test is waiting for " + seconds + " seconds.", true);
	}
	
	/**
     * Validates response values
     * 
     * @param message
     * @param actual
     * @param expected
     */
    public static void validateResponse(String message, Object actual, Object expected) {
        try {
        	Assert.assertEquals(actual, expected, message + " - FAILED | Expected: " + expected + " Actual: " + actual);
            Reporter.log(message + " - PASSED | Value: " + actual, true);
        } catch (AssertionError e) {
            Reporter.log(message + " - FAILED | Expected: " + expected + " Actual: " + actual, true);
            throw e;
        }
    }
}
