package com.example.framework.pages;

import com.example.framework.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(css = "button[id^='add-to-cart']")
    private WebElement addToCartButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartCount;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addProductToCart() {
        waitForVisibility(addToCartButton, 10);
        click(addToCartButton);
        waitForText(cartCount, "1", 10);
    }

    public int getCartCount() {
        waitForVisibility(cartCount, 10);
        return Integer.parseInt(cartCount.getText());
    }
}
