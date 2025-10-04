package com.example.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop = new Properties();
    static {
        try (InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("config.properties")) {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
