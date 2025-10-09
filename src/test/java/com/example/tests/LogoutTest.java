package com.example.tests;

import com.example.framework.core.BaseTest;
import com.example.framework.pages.LoginPage;
import com.example.framework.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @Test
    public void userCanLogoutSuccessfully() {
        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        ProductsPage products = new ProductsPage(driver);
        products.logout();

        // Attempt to access inventory page
        driver.get("https://www.saucedemo.com/inventory.html");

        // Verify that user is redirected to login page
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "User is not redirected to login page after logout");
    }
}
