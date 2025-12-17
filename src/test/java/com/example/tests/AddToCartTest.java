package com.example.tests;

import com.example.framework.pages.LoginPage;
import com.example.framework.pages.ProductsPage;
import com.example.testNgBaseListener.BaseTest;
import com.example.framework.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test
    public void addProductToCartTest() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
        
        Thread.sleep(3000);
        

        ProductsPage products = new ProductsPage(driver);
        products.addFirstProductToCart();
        products.goToCart();

        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isCartNotEmpty(), "Cart should have at least one product");
    }
}
