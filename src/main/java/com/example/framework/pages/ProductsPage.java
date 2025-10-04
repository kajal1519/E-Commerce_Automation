package com.example.framework.pages;

import com.example.framework.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(css = "div.inventory_item")
    private List<WebElement> products;

    @FindBy(css = "button[id^='add-to-cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int getProductsCount() {
        waitForVisibility(products.get(0), 10); // wait for at least one product
        return products.size();
    }

    public void addFirstProductToCart() {
        waitForVisibility(addToCartButtons.get(0), 10);
        click(addToCartButtons.get(0));
    }

    public void goToCart() {
        click(cartIcon);
    }
}
