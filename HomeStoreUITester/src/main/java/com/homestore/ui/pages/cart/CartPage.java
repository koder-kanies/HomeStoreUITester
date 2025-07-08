package com.homestore.ui.pages.cart;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.homestore.ui.pages.checkout.CheckoutPage;
import com.homestore.ui.pages.shop.ShopPage;
import com.homestore.ui.utils.JSUtils;
import com.homestore.ui.utils.Utility;

public class CartPage extends ShopPage {
	
	private By cartItemElements = By.xpath(
			"//form[@class='woocommerce-cart-form']/descendant::tr[contains(@class, 'woocommerce-cart-form__cart-item')]");
	private By cartTotalPrice = By.xpath("//tr[@class='order-total']//bdi");
	private By updateCartButton = By.xpath("//button[@name='update_cart']");
	private By updateCartMessage = By.xpath("//div[@class='woocommerce-message']");
	private By checkoutButton = By.xpath(
			"//div[@class='wc-proceed-to-checkout']//a[contains(@class,'checkout-button')]");

	/**
     * This method returns name of all the products in cart
     */
	public List<String> getProductNameList() {
		List<String> productNameList = new ArrayList<String>();
		Utility.wait_inSeconds(5);
		Utility.waitForElementToDisplay(10, cartItemElements);
		List<WebElement> allItems = driver.findElements(cartItemElements);
		for(int i=0; i<allItems.size(); i++) {
			WebElement element = allItems.get(i);
			JSUtils.scrollToElement(element);
			String itemName = element.findElement(By.xpath(".//td[@class='product-name']//a")).getText();
			productNameList.add(itemName);
		}
		return productNameList;
	}
	
	/**
     * This method returns price of all the products in cart
     */
	public List<Double> getProductPriceList() {
		List<Double> productPriceList = new ArrayList<Double>();
		Utility.waitForElementToDisplay(10, cartItemElements);
		List<WebElement> allItems = driver.findElements(cartItemElements);
		for(int i=0; i<allItems.size(); i++) {
			WebElement element = allItems.get(i);
			JSUtils.scrollToElement(element);
			String itemPrice = element.findElement(By.xpath(".//td[@class='product-subtotal']//bdi")).getText();
			itemPrice = itemPrice.replaceAll("[^\\d.]", "");
			Double price = Double.parseDouble(itemPrice);
			productPriceList.add(price);
		}
		return productPriceList;
	}
	
	/**
     * This method returns total price of the products in cart
     */
	public Double getTotalCartPrice() {
		JSUtils.scrollToElement(cartTotalPrice);
		String totalPrice = driver.findElement(cartTotalPrice).getText();
		totalPrice = totalPrice.replaceAll("[^\\d.]", "");
		return Double.parseDouble(totalPrice);
	}
	
	/**
     * This method returns product quantity in cart using its name.
     * 
     * @param productName
     */
	public Integer getProductQuantity(String productName) {
		String xpath = "//label[text()='" + productName + " quantity']/following-sibling::input";
		String quantity = JSUtils.scrollToElement(By.xpath(xpath)).getDomAttribute("value");
		return Integer.parseInt(quantity);
	}
	
	/**
     * This method update the product quantity in cart using its name.
     * 
     * @param productName
     * @param quantity
     */
	public void updateProductQuantity(String productName, String quantity) {
		String xpath = "//label[text()='" + productName + " quantity']/following-sibling::input";
		WebElement element = JSUtils.scrollToElement(By.xpath(xpath));
		element.clear();
		element.sendKeys(quantity);
	}
	
	/**
	 * This method clicks the "Update cart" button.
	 */
	public void updateCart() {
		JSUtils.scrollAndClickElement(updateCartButton);
	}	
	
	/**
	 * This method returns Update cart message.
	 */
	public String getUpdateCartMessage() {
		return JSUtils.scrollAndClickElement(updateCartMessage).getText().trim();
	}
	
	/**
	 * This method clicks the "Proceed to Checkout" button and 
	 * returns the CheckoutPage instance.
	 */
	public CheckoutPage checkoutCart() {
		JSUtils.scrollAndClickElement(checkoutButton);
		return new CheckoutPage();
	}	
}
