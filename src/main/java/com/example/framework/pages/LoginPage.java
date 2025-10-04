package com.example.framework.pages;

import com.example.framework.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String user) { type(username, user); }
    public void enterPassword(String pass) { type(password, pass); }
    public void clickLogin() { click(loginBtn); }

    public String getErrorMessage() {
        try { return getText(errorMessage); } 
        catch (Exception e) { return ""; }
    }
}
