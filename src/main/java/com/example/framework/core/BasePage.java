package com.example.framework.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) { 
        this.driver = driver; 
    }

    protected void waitForVisibility(WebElement element, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForText(WebElement element, String text, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    protected void type(WebElement element, String text) {
        waitForVisibility(element, 10);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(WebElement element) {
        waitForVisibility(element, 30);
        element.click();
    }

    protected String getText(WebElement element) {
        waitForVisibility(element, 10);
        return element.getText();
    }
    
    
}
