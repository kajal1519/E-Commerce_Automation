package com.example.framework.pages;

import com.example.framework.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = "div.cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

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
