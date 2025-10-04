package com.example.framework.utils;


import org.openqa.selenium.WebDriver;


public class AuthGuard {
public static void checkForGoogleInterruption(WebDriver driver, String testNamePrefix) {
try {
String currentUrl = driver.getCurrentUrl().toLowerCase();
String title = "";
try { title = driver.getTitle().toLowerCase(); } catch (Exception ignored) {}


boolean googleUrl = currentUrl.contains("accounts.google.com") || currentUrl.contains("myaccount.google.com");
boolean googleTitle = title.contains("google") && (title.contains("password") || title.contains("change") || title.contains("account"));


boolean pageContainsWarning = false;
try {
String pageSource = driver.getPageSource().toLowerCase();
if (pageSource.contains("google wants to change your password") ||
pageSource.contains("change your password") ||
pageSource.contains("reset your password")) {
pageContainsWarning = true;
}
} catch (Exception ignored) {}


if (googleUrl || googleTitle || pageContainsWarning) {
String path = ScreenshotUtil.takeScreenshot(driver, "google_interruption_" + testNamePrefix);
String msg = "Detected redirection to Google / external auth page. Current URL: " + currentUrl +
". Screenshot: " + (path == null ? "not available" : path);
throw new RuntimeException(msg);
}
} catch (RuntimeException re) {
throw re;
} catch (Exception e) {
System.err.println("AuthGuard check failed: " + e.getMessage());
}
}
}