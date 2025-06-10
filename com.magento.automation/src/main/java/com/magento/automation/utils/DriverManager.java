// DriverManager.java
package com.magento.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Enables headless mode using new implementation
        options.addArguments("--disable-gpu"); // Disables GPU hardware acceleration
        options.addArguments("--window-size=1920,1080"); // Sets standard resolution
        options.addArguments("--disable-dev-shm-usage"); // Overcomes limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--remote-allow-origins=*"); // Fix for some headless origin policy issues
        driver.set(new ChromeDriver(options));
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
