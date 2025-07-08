package com.homestore.ui.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.homestore.ui.pages.shop.ShopPage;
import com.homestore.ui.utils.JSUtils;

public class CheckoutPage extends ShopPage {
	
	private By firstNameField = By.id("billing_first_name");
	private By lastNameField = By.id("billing_last_name");
	private By countryDropdownField = By.id("billing_country");
	private By addressField = By.id("billing_address_1");
	private By cityField = By.id("billing_city");
	private By stateDropdownField = By.id("billing_state");
	private By pincodeField = By.id("billing_postcode");
	private By phoneField = By.id("billing_phone");
	private By emailField = By.id("billing_email");
	private By createAccountCheckbox = By.id("createaccount");
	private By passwordField = By.id("account_password");
	private By passwordEyeButton = By.xpath(
			"//p[@id='account_password_field']//span[contains(@class,'password-input')]//span[contains(@class,'password-input')]");
	private By openEyeButton = By.xpath(
			"//p[@id='account_password_field']//span[contains(@class,'password-input')]/span[contains(@class, 'display-password')]");
	private By placeOrderButton = By.id("place_order");
	private By errorMessageAlert = By.xpath("//ul[@class='woocommerce-error']//li");
	
	/**
	 * This method fills in all user details to create a new account during
	 * checkout.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param country
	 * @param address
	 * @param city
	 * @param state
	 * @param pincode
	 * @param phone
	 * @param email
	 * @param password
	 */
	public void createNewAccount(String firstName, String lastName, String country, String address, String city, String state, 
			String pincode, String phone, String email, String password) {
		
		// Fill personal and address information
		inputFirstName(firstName);
		inputLastName(lastName);
		inputCountry(country);
		inputAddress(address);
		inputCity(city);
		inputState(state);
		inputPincode(pincode);
		
		// Fill contact details
		inputPhoneNumber(phone);
		inputEmail(email);
		
		// Enable account creation and set password
		selectCreateAccountCheckbox();
		setPassword(password);
	}
	
	/**
	 * This method enters the user's first name into the form.
	 * 
	 * @param firstName
	 */
	public void inputFirstName(String firstName) {
		JSUtils.scrollToElement(firstNameField).sendKeys(firstName);
	}
	
	/**
	 * This method enters the user's last name into the form.
	 * 
	 * @param lastName
	 */
	public void inputLastName(String lastName) {
		JSUtils.scrollToElement(lastNameField).sendKeys(lastName);
	}
	
	/**
	 * This method selects the user's country from the dropdown.
	 * 
	 * @param country
	 */
	public void inputCountry(String country) {
		WebElement selectElement = JSUtils.scrollAndClickElement(countryDropdownField);
		By countryOption = By.xpath("//option[text()='" + country + "']");
		String value = selectElement.findElement(countryOption).getDomAttribute("value");
		Select select = new Select(selectElement);
		select.selectByValue(value);
	}
	
	/**
	 * This method enters the user's address into the form.
	 */
	public void inputAddress(String address) {
		JSUtils.scrollToElement(addressField).sendKeys(address);
	}
	
	/**
	 * This method enters the user's city into the form.
	 * 
	 * @param city
	 */
	public void inputCity(String city) {
		JSUtils.scrollToElement(cityField).sendKeys(city);
	}
	
	/**
	 * This method selects the user's state from the dropdown.
	 * 
	 * @param state
	 */
	public void inputState(String state) {
		WebElement selectElement = JSUtils.scrollAndClickElement(stateDropdownField);
		By stateOption = By.xpath("//option[text()='" + state + "']");
		String value = selectElement.findElement(stateOption).getDomAttribute("value");
		Select select = new Select(selectElement);
		select.selectByValue(value);
	}	
	
	/**
	 * This method enters the user's postal code into the form.
	 * 
	 * @param pincode
	 */
	public void inputPincode(String pincode) {
		JSUtils.scrollToElement(pincodeField).sendKeys(pincode);
	}
	
	/**
	 * This method enters the user's phone number into the form.
	 * 
	 * @param phone
	 */
	public void inputPhoneNumber(String phone) {
		JSUtils.scrollToElement(phoneField).sendKeys(phone);
	}
	
	/**
	 * This method enters the user's email address into the form.
	 * 
	 * @param email
	 */
	public void inputEmail(String email) {
		JSUtils.scrollToElement(emailField).sendKeys(email);
	}
	
	/**
	 * This method selects the checkbox to create a new account.
	 */
	public void selectCreateAccountCheckbox() {
		JSUtils.scrollAndClickElement(createAccountCheckbox);
	}
	
	/**
	 * This method sets the password for the new account.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		JSUtils.scrollToElement(passwordField).sendKeys(password);
	}
	
	/**
	 * This method ensures the password is visible by clicking the eye icon if needed.
	 */
	public void showPassword() {
		boolean isVisible = false;
		isVisible =  driver.findElements(openEyeButton).size() > 0;
		if(!isVisible) {
			JSUtils.scrollAndClickElement(passwordEyeButton);
		}
	}
	
	/**
	 * This method ensures the password is hidden by clicking the eye icon if it's visible.
	 */
	public void hidePassword() {
		boolean isVisible = true;
		isVisible =  driver.findElements(openEyeButton).size() > 0;
		if(isVisible) {
			JSUtils.scrollAndClickElement(passwordEyeButton);
		}
	}
	
	/**
	 * This method reveals the password, retrieves its value, and then hides it again.
	 */
	public String returnInputPassword() {
		showPassword();
		String password = find(passwordField).getDomProperty("value");
		hidePassword();
		return password;
	}
	
	/**
	 * This method clicks the "Place Order" button to submit the checkout form.
	 */
	public void placeOrder() {
		JSUtils.scrollAndClickElement(placeOrderButton);
	}
	
	/**
	 * This method returns the error message displayed on the checkout page.
	 */
	public String getErrorMessage() {
		return JSUtils.scrollToElement(errorMessageAlert).getText();
	}
}
