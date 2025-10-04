package com.example.framework.pages;

import com.example.framework.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    @FindBy(id = "finish")
    private WebElement finishBtn;

    @FindBy(css = ".complete-header")
    private WebElement completeHeader;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterShippingDetails(String fn, String ln, String pcode) {
        type(firstName, fn);
        type(lastName, ln);
        type(postalCode, pcode);
    }

    public void clickContinue() {
        click(continueBtn);
    }

    public void clickFinish() {
        click(finishBtn);
    }

    public String getOrderConfirmationText() {
        waitForVisibility(completeHeader, 10);
        return getText(completeHeader);
    }
}
