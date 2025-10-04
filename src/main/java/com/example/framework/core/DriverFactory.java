
package com.example.framework.core;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory 
{ 
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

public static WebDriver getDriver() 

{ 
	return tlDriver.get();
	
}

public static void initDriver() 

{ 
	try
	
	{ 
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions(); 
		

		options.addArguments("--start-maximized");
		options.addArguments("--incognito"); 
		tlDriver.set(new ChromeDriver(options));
		
		
	} catch (Exception e) 
	
	{ 
		throw new RuntimeException("Failed to initialize ChromeDriver", e);
		
	
	} 
	
} 

public static void quitDriver()
{ 
	if (getDriver() != null) { getDriver().quit(); tlDriver.remove();
	
	} 
	
}

}