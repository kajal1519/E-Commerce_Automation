package com.example.tests;

import com.example.framework.core.BaseTest;
import com.example.framework.pages.CartPage;
import com.example.framework.pages.LoginPage;
import com.example.framework.pages.ProductDetailsPage;
import com.example.framework.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTotalTest extends BaseTest {

    @Test
    public void addTwoItemsAndVerifyTotal() {
    	
    	LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
    	
    	
        ProductsPage productsPage = new ProductsPage(driver);

        // Handle any popups before test
        //productsPage.handleLoginPopupIfPresent();

        // Click first product and add to cart
        productsPage.clickProduct(0);
        ProductDetailsPage product1 = new ProductDetailsPage(driver);
        double price1 = product1.getProductPrice();
        product1.addToCart();

        driver.navigate().back(); // go back to products list

        // Click second product and add to cart
        productsPage.clickProduct(1);
        ProductDetailsPage product2 = new ProductDetailsPage(driver);
        double price2 = product2.getProductPrice();
        product2.addToCart();

        // Go to Cart
        productsPage.goToCart();
        CartPage cartPage = new CartPage(driver);

        double totalFromCart = cartPage.getTotalCartValue(); // Implement method in CartPage to sum all product prices
        double expectedTotal = price1 + price2;

        Assert.assertEquals(totalFromCart, expectedTotal, "Cart total should match sum of product prices.");
    }
}
