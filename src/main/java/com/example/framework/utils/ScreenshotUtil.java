package com.example.framework.utils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScreenshotUtil {
public static String takeScreenshot(WebDriver driver, String namePrefix) {
try {
TakesScreenshot ts = (TakesScreenshot) driver;
File src = ts.getScreenshotAs(OutputType.FILE);
String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
String fileName = namePrefix + "_" + timestamp + ".png";
Path destDir = Paths.get(System.getProperty("user.dir"), "screenshots");
if (!Files.exists(destDir)) Files.createDirectories(destDir);
Path dest = destDir.resolve(fileName);
Files.copy(src.toPath(), dest);
return dest.toString();
} catch (IOException e) {
e.printStackTrace();
return null;
}
}
}