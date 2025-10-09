package com.example.framework.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static Properties prop;

    // Load configuration from config.properties
    public static void loadConfig() {
        prop = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    // Get the current WebDriver instance
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    // Initialize driver based on browser name in config file
    public static void initDriver() {
        if (prop == null) {
            loadConfig();
        }

        String browser = prop.getProperty("browser", "chrome").toLowerCase();

        try {
            WebDriver driver;

            if (browser.equals("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized", "--incognito");
                driver = new ChromeDriver(options);

            } else if (browser.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                driver = new FirefoxDriver(options);

            } else if (browser.equals("edge")) {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                driver = new EdgeDriver(options);

            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            tlDriver.set(driver);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver for " + browser, e);
        }
    }

    // Quit and remove driver instance
    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();
        }
    }
}
