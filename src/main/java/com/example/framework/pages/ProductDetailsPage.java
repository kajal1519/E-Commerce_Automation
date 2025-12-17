package com.example.framework.pages;

import com.example.framework.core.BasePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage extends BasePage {

    // WebElements
    @FindBy(css = ".inventory_details_name")
    private WebElement productTitle;

    @FindBy(css = ".inventory_details_desc")
    private WebElement productDescription;

    @FindBy(css = ".inventory_details_price")
    private WebElement productPrice;

    @FindBy(css = "button.btn_primary.btn_inventory") // Add to cart button
    private WebElement addToCartButton;

    @FindBy(className = "shopping_cart_link") // Cart icon to navigate
    private WebElement cartIcon;
    
    @FindBy(css =".inventory_details_desc")
    private WebElement descriptionLocator;
    
    private By nameLocator = By.cssSelector(".inventory_details_name");
    

    // Constructor
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Methods to interact with product details
    public String getProductTitle() {
        WebElement nameElement = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(nameLocator));
        return nameElement.getText();
    }

    public String getProductDescription() {
        WebElement descElement = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(descriptionLocator));
        return descElement.getText();
    }

    public double getProductPrice() {
        try {
            WebElement priceElement = new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOf(productPrice));
            String priceText = priceElement.getText();
            return Double.parseDouble(priceText.replace("$", ""));
        } catch (Exception e) {
            throw new RuntimeException("Product price not found!", e);
        }
    }

    public void addToCart() {
        WebElement addBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn_primary.btn_inventory")));
        addBtn.click();
    }

    public void goToCart() {
        click(cartIcon);
    }
}
