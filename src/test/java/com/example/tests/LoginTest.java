package com.example.tests;

import com.example.framework.core.BaseTest;
import com.example.framework.pages.LoginPage;
import com.example.framework.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLogin() {
        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        ProductsPage products = new ProductsPage(driver);
        Assert.assertTrue(products.getProductsCount() > 0, "Products list should be displayed");
    }

    @Test
    public void invalidLoginShowsError() {
        LoginPage login = new LoginPage(driver);
        login.enterUsername("invalid_user");
        login.enterPassword("wrong_pass");
        login.clickLogin();
        Assert.assertTrue(login.getErrorMessage().length() > 0, "Error should display for invalid credentials");
    }
}
