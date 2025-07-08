package com.homestore.ui.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.homestore.ui.base.BaseTest;
import com.homestore.ui.pages.cart.CartPage;
import com.homestore.ui.pages.checkout.CheckoutPage;
import com.homestore.ui.pages.product.ProductsPage;
import com.homestore.ui.pages.shop.ShopPage;
import com.homestore.ui.testdata.UserTestData;
import com.homestore.ui.utils.Utility;

public class UITests extends BaseTest {

	/**
	 * User Flow: Adding Items to Cart and Completing Checkout
	 * 1. User lands on the e-commerce website’s home page.
	 * 2. User browses through various product categories and selects an item to purchase.
	 * 3. User clicks on the selected item to view more details.
	 * 4. User decides to add the item to the cart and clicks on the “Add to Cart” button.
	 * 5. The item is successfully added to the cart, and the user is shown a confirmation message.
	 * 6. The user decides to continue shopping and adds more items to the cart.
	 * 7. The user reviews the cart to ensure all desired items are added.
	 * 8. User clicks on the “Proceed to Checkout” button to start the checkout process.
	 * 9. The user create a new account .
	 * 10. The user provides shipping information, such as name, address, and contact details.
	 * 11. The user clicks on the “Place Order” button to confirm the purchase.
	 * 12. The system processes the order and simulate placing order with default payment method, expecting failure.
	 */
	@Test(description = "Validates the complete e-commerce flow of adding items to the cart, proceeding to checkout, creating a new account"
			+ " with shipping details, and attempting to place an order—expecting failure due to a missing or unsupported payment method.")
	public void testAddItemsAndCheckoutPaymentError() {

		// Go to e-commerce home page.
		ShopPage shopPage = homePage.goToShop();
		
		// User search for a product
		List<String> itemList = new ArrayList<String>();
		String searchText = "pillow";
		shopPage.searchProductItem(searchText);
		String productName = "Multi-colored pillows";
		ProductsPage productsPage = shopPage.openSearchedProductItem(productName);
		
		// Add product to cart and validate message
		productsPage.addToCart();
		Boolean isMessageDisplayed = productsPage.isItemAddedMessageDisplayed(productName);
		Utility.validateResponse("Verify item is added to the cart", isMessageDisplayed, true);
		itemList.add(productName);
		
		// User navigate to product from category page and add to cart
		String categoryName = "Porch";
		shopPage.goToCategory(categoryName);
		productName = "Wooden rocking chair";
		shopPage.addItemOnCategoryPage(productName);
		itemList.add(productName);
		
		// Go to cart and review the cart items
		CartPage cartPage = shopPage.goToCart();
		List<String> itemInCart = cartPage.getProductNameList();
		List<Double> priceInCart = cartPage.getProductPriceList();
		Double sum = priceInCart.stream().mapToDouble(Double::doubleValue).sum();
		Double totalCartPrice = cartPage.getTotalCartPrice();
		
		Utility.validateResponse("Verify items in cart", itemInCart, itemList);
		Utility.validateResponse("Verify total cart value", totalCartPrice, sum);
		
		// Go to checkout page and create an account
		CheckoutPage checkoutPage = cartPage.checkoutCart();
		checkoutPage.createNewAccount(UserTestData.FIRST_NAME, UserTestData.LAST_NAME, UserTestData.COUNTRY, 
				UserTestData.ADDRESS, UserTestData.CITY, UserTestData.STATE, UserTestData.PINCODE, 
				UserTestData.PHONE, UserTestData.EMAIL, UserTestData.PASSWORD);
		String inputPassword = checkoutPage.returnInputPassword();
		Utility.validateResponse("Validate returned password", inputPassword, UserTestData.PASSWORD);
		
		// Place the order and verify the error
		checkoutPage.placeOrder();
		String errorMessage = checkoutPage.getErrorMessage();
		String expectedMessage = "Invalid payment method.";
		Utility.validateResponse("Verify error message", errorMessage, expectedMessage);
	}
	
	/**
	 * User Flow: Selecting Items from Home Page and Updating Cart Quantities
	 * 1. User lands on the e-commerce website’s home page.
	 * 2. User scrolls to a featured item displayed on the home page.
	 * 3. User clicks the "Add to Cart" button twice to add 2 units of the first item.
	 * 4. User scrolls to another featured item and clicks the "Add to Cart" button once.
	 * 5. User clicks the “View Cart” button and navigates to the cart page.
	 * 6. Cart page displays both selected items with their respective quantities.
	 * 7. User updates the quantity of the first item to 1 and the second item to 2.
	 * 8. User clicks “Update Cart” to apply quantity changes.
	 * 9. Cart reflects updated quantities and the total price is recalculated accordingly.
	 */
	@Test
	(description = "Validates that user can add items from home page, update cart quantities, and verify recalculated totals.")
	public void testAddItemsFromHomeAndUpdateCart() {
		
		// Go to e-commerce home page.
		ShopPage shopPage = homePage.goToShop();
		
		// User scrolls to first item and add 2 units
		String firstProductName = "Comfortable gray bed";
		shopPage.addItemOnHomePage(firstProductName);
		shopPage.addItemOnHomePage(firstProductName);
		Double firstItemPrice = shopPage.getProductPrice(firstProductName);
		
		// User scrolls to second item and add 1 unit
		Utility.wait_inSeconds(10);
		String secondProductName = "Medium white couch";
		shopPage.addItemOnHomePage(secondProductName);
		Double secondItemPrice = shopPage.getProductPrice(secondProductName);
		
		// Click view cart and review the cart items
		CartPage cartPage = shopPage.clickViewCart();
		Integer firstProductCount = cartPage.getProductQuantity(firstProductName);
		Utility.validateResponse("Verify quantity of " + firstProductName, firstProductCount, 2);
		Integer secondProductCount = cartPage.getProductQuantity(secondProductName);
		Utility.validateResponse("Verify quantity of " + secondProductName, secondProductCount, 1);
		
		// User updates the quantity of the first item to 1 and the second item to 2
		cartPage.updateProductQuantity(firstProductName, "1");
		cartPage.updateProductQuantity(secondProductName, "2");
		
		// Update the cart and verify the message
		cartPage.updateCart();
		String actualMessage = cartPage.getUpdateCartMessage();
		String expectedMessage = "Cart updated.";
		Utility.validateResponse("Verify update cart message", actualMessage, expectedMessage);
		
		// Verify cart reflects updated quantities
		firstProductCount = cartPage.getProductQuantity(firstProductName);
		Utility.validateResponse("Verify quantity of " + firstProductName, firstProductCount, 1);
		secondProductCount = cartPage.getProductQuantity(secondProductName);
		Utility.validateResponse("Verify quantity of " + secondProductName, secondProductCount, 2);
		
		// Verify total price is recalculated
		Double actualTotalPrice = cartPage.getTotalCartPrice();
		Double expectedTotalPrice = firstItemPrice + (2 * secondItemPrice);	
		Utility.validateResponse("Verify total cart price is updated. First item: " + firstItemPrice + ", Second item: "
				+ secondItemPrice + " x 2", actualTotalPrice, expectedTotalPrice);
	}
}
