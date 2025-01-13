package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        try {
            if (driver == null) {
                LoggerUtil.info("Initializing WebDriver");
                ChromeOptions options = new ChromeOptions();
                

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.cookies", 1);
                prefs.put("profile.cookie_controls_mode", 0); 
                prefs.put("profile.default_content_setting_values.notifications", 2); 
                
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--start-maximized");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                
                driver = new ChromeDriver(options);
            }
            return driver;
        } catch (Exception e) {
            LoggerUtil.error("Error initializing WebDriver: " + e.getMessage());
            throw e;
        }
    }

    public static void closeDriver() {
        try {
            if (driver != null) {
                driver.quit();
                driver = null;
                LoggerUtil.info("WebDriver closed");
            }
        } catch (Exception e) {
            LoggerUtil.error("WebDriver run error: " + e.getMessage());
        }
    }
} 