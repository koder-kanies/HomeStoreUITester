package com.homestore.ui.pages.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import com.homestore.ui.pages.HomePage;
import com.homestore.ui.pages.cart.CartPage;
import com.homestore.ui.pages.product.ProductsPage;
import com.homestore.ui.utils.JSUtils;
import com.homestore.ui.utils.Utility;

public class ShopPage extends HomePage {
	
	private By shopSearchBox = By.xpath("//form[@class='search-form']//input[@class='search-field']");
	private By cartMenu = By.xpath("//a[@class='cart-contents']");
	private By viewCart = By.xpath("//a[@title='View cart']");
	
	/**
	 * This method searches for a product on searchBox using the provided product name.
	 *
	 * @param productName
	 */
	public void searchProductItem(String productName) {
		Utility.waitForElementToDisplay(10, shopSearchBox);
		sendKeys(shopSearchBox, productName + Keys.ENTER);
	}
	
	/**
	 * This method opens the product details page of a searched item based on its name.
	 *
	 * @param productName
	 */
	public ProductsPage openSearchedProductItem(String productName) {
		String xpath = "//a[text()='" + productName + "']";
		JSUtils.scrollToElement(By.xpath(xpath)).click();
		return new ProductsPage();
	}
	
	/**
	 * This method navigates to a specific product category on the shop page.
	 *
	 * @param categoryName
	 */
	public void goToCategory(String categoryName) {
		String xpath = "//span[contains(text(), 'Product Categories')]/following::a[contains(text(), '" + categoryName + "')]";
		JSUtils.scrollAndClickElement(By.xpath(xpath));
	}
	
	/**
	 * This method adds a product to the cart directly from a category page using its name.
	 *
	 * @param productName
	 */
	public void addItemOnCategoryPage(String productName) {
		String xpath = "//h2[contains(text(),'" + productName + "')]/ancestor::li//a[text()='Add to cart']";
		JSUtils.scrollAndClickElement(By.xpath(xpath));
	}
	
	/**
	 * This method opens the shopping cart page from the main menu.
	 */
	public CartPage goToCart() {
		JSUtils.scrollAndClickElement(cartMenu);
		return new CartPage();
	}
	
	/**
	 * This method adds a product to the cart directly from a home page using its name.
	 *
	 * @param productName
	 */
	public void addItemOnHomePage(String productName) {
		String xpath = "//h2[text()='" + productName + "']/ancestor::li//a[text()='Add to cart']";
		JSUtils.scrollAndClickElement(By.xpath(xpath));
	}
	
	/**
	 * This method returns the product price in home page using its name.
	 *
	 * @param productName
	 */
	public Double getProductPrice(String productName) {
		String xpath = "//h2[text()='" + productName + "']/following-sibling::span[@class='price']/descendant::ins//bdi";
		String itemPrice = JSUtils.scrollToElement(By.xpath(xpath)).getText();
		itemPrice = itemPrice.replaceAll("[^\\d.]", "");
		return Double.parseDouble(itemPrice);
	}
	
	/**
	 * This method opens the shopping cart page from the home page.
	 */
	public CartPage clickViewCart() {
		JSUtils.clickElement(viewCart);
		return new CartPage();
	}
}
