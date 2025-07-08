package com.homestore.ui.pages;

import org.openqa.selenium.By;
import com.homestore.ui.base.BasePage;
import com.homestore.ui.pages.shop.ShopPage;

public class HomePage extends BasePage {

	private By shopButton = By.xpath("//ul[@id='menu-primary-menu']//a[text()='Shop']");

	/**
	 * This method navigates to the shop page by clicking the shop button and
	 * returns a new instance of the ShopPage.
	 */
	public ShopPage goToShop() {
		find(shopButton).click();
		return new ShopPage();
	}
}
