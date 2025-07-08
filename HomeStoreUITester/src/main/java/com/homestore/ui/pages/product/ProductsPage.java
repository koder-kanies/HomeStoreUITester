package com.homestore.ui.pages.product;

import org.openqa.selenium.By;

import com.homestore.ui.pages.shop.ShopPage;
import com.homestore.ui.utils.JSUtils;

public class ProductsPage extends ShopPage {
	
	private By addToCartButton = By.xpath("//button[@name='add-to-cart']");
	private By itemAddedMessage = By.xpath("//div[@class='woocommerce-message']");

	/**
	 * This method clicks the "Add to Cart" button for the currently selected product.
	 */
	public void addToCart() {
		JSUtils.scrollToElement(addToCartButton).click();
	}

	/**
	 * This method verifies if the confirmation message indicating 
	 * the item was added to the cart is displayed.
	 *
	 * @param productName
	 */
	public boolean isItemAddedMessageDisplayed(String productName) {
		String displayMessage = "\"" + productName + "\" has been added to your cart.";
		String message = JSUtils.scrollToElement(itemAddedMessage).getText();
		message = message.replace("“", "\"").replace("”", "\"");
		return message.contains(displayMessage);
	}
}
