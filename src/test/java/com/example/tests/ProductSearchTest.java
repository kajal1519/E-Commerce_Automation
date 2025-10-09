package com.example.tests;

import com.example.framework.core.BaseTest;
import com.example.framework.pages.LoginPage;
import com.example.framework.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductSearchTest extends BaseTest {

    @Test
    public void filterProductsByPriceLowToHigh() {
        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        ProductsPage products = new ProductsPage(driver);
        products.sortProductsBy("Price (low to high)");

        // Verify that products are sorted by price ascending
        double firstPrice = products.getProductPrice(0);
        double secondPrice = products.getProductPrice(1);
        Assert.assertTrue(firstPrice <= secondPrice, "Products are not sorted correctly by price.");
    }
}
