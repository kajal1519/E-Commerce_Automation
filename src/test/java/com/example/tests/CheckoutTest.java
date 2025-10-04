package com.example.tests;

import com.example.framework.core.BaseTest;
import com.example.framework.pages.LoginPage;
import com.example.framework.pages.ProductsPage;
import com.example.framework.pages.CartPage;
import com.example.framework.pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void checkoutFlowTest() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
       

        ProductsPage products = new ProductsPage(driver);
        products.addFirstProductToCart();
        products.goToCart();
     

        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isCartNotEmpty(), "Cart should have at least one product");
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.enterShippingDetails("Kajal", "Sharma", "560001");
        checkout.clickContinue();
        checkout.clickFinish();

        Assert.assertEquals(checkout.getOrderConfirmationText(), "Thank you for your order!",
                "Order confirmation message should match");
    }
}


