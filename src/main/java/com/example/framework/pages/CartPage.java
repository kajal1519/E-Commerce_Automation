package com.example.framework.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.framework.core.BasePage;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = "div.cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(css = ".summary_subtotal_label")
    private WebElement totalPriceLabel;
    
    private By productPrices = By.cssSelector(".inventory_item_price");
    
    
    public int getCartItemsCount() {
        return cartItems.size();
    }

    public double getTotalPrice() {
        String text = totalPriceLabel.getText(); // e.g., "Item total: $39.98"
        String price = text.replace("Item total: $", "");
        return Double.parseDouble(price);
    }
    
    public double getTotalCartValue() {
        double total = 0.0;
        List<WebElement> prices = driver.findElements(productPrices);
        for (WebElement price : prices) {
            String text = price.getText().replace("$", "").trim();
            total += Double.parseDouble(text);
        }
        return total;
    }
    
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isCartNotEmpty() {
        waitForVisibility(cartItems.get(0), 10);
        return cartItems.size() > 0;
    }

    public void clickCheckout() {
        click(checkoutButton);
    }
}
