package com.example.tests;

import com.example.framework.core.BaseTest;
import com.example.framework.pages.LoginPage;
import com.example.framework.pages.ProductsPage;
import com.example.framework.pages.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductDetailsTest extends BaseTest {

    @Test
    public void validateProductDetails() {
        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
/*
        ProductsPage products = new ProductsPage(driver);

        // Click first product
        String productName = products.getProductName(0);
        products.clickProduct(0);
*/
        ProductsPage products = new ProductsPage(driver);
        products.clickProduct(0); // Open first product

        ProductDetailsPage details = new ProductDetailsPage(driver);
        String title = details.getProductTitle();
        String description = details.getProductDescription();
        double price = details.getProductPrice();

        //ProductDetailsPage details = new ProductDetailsPage(driver);
        Assert.assertEquals(details.getProductTitle(), title, "Product title mismatch");
        Assert.assertTrue(details.getProductDescription().length() > 0, "Product description is missing");
        Assert.assertTrue(details.getProductPrice() > 0, "Product price is invalid");
    }
}
