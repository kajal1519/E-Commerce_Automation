package com.example.framework.pages;

import com.example.framework.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Product list
    @FindBy(css = "div.inventory_item")
    private List<WebElement> products;

    // Add to Cart buttons
    @FindBy(css = "button[id^='add-to-cart']")
    private List<WebElement> addToCartButtons;

    // Cart icon
    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    // Sort dropdown
    @FindBy(css = ".product_sort_container")
    private WebElement sortDropdown;

    // Menu button for logout
    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    // Logout link
    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    // ---------------- Methods ----------------

    // Waits for at least one product and returns count
    public int getProductsCount() {
        waitForVisibility(products.get(0), 10);
        return products.size();
    }

    // Add first product to cart
    public void addFirstProductToCart() {
        waitForVisibility(addToCartButtons.get(0), 10);
        click(addToCartButtons.get(0));
    }

    // Add product by index to cart
    public void addProductToCart(int index) {
        waitForVisibility(addToCartButtons.get(index), 10);
        click(addToCartButtons.get(index));
    }

    // Go to cart page
    public void goToCart() {
        click(cartIcon);
    }

    // Get product name by index
    public String getProductName(int index) {
        return products.get(index).findElement(By.cssSelector(".inventory_item_name")).getText();
    }

    // Get product price by index
    public double getProductPrice(int index) {
        String priceText = products.get(index)
                .findElement(By.cssSelector(".inventory_item_price"))
                .getText();
        return Double.parseDouble(priceText.replace("$", ""));
    }

    // Click product name to open details
    public void clickProduct(int index) {
        if (products.isEmpty()) {
            throw new RuntimeException("No products available to click!");
        }
        waitForVisibility(products.get(index), 10);
        products.get(index).click();
    }

    // Sort products
    public void sortProductsBy(String criteria) {
        waitForVisibility(sortDropdown, 10);
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(criteria);
    }

    // Logout user
    public void logout() {
        click(menuButton);
        waitForVisibility(logoutLink, 5);
        click(logoutLink);
    }
    
    public void handleLoginPopupIfPresent() {
        try {
            WebElement popupClose = driver.findElement(By.cssSelector(".popup_class")); // update with actual selector
            if(popupClose.isDisplayed()) popupClose.click();
        } catch(Exception e) {
            System.out.println("Pop up not present");
        }
    }
}
