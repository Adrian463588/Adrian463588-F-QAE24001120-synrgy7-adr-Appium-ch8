package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {

    protected static AndroidDriver driver;
    protected static WebDriverWait wait;

    public static void setUp() {
        // Set up the driver
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "13.0");
        capabilities.setCapability("deviceName", "192.168.49.101:5555");
        capabilities.setCapability("app", "D:/KULIAH_SEM_8/Binar_QA/Week_8/challenge/[Exercise CH 8] Android.SauceLabs.Mobile.Sample.app.2.7.1 (1).apk");
        capabilities.setCapability("automationName", "UIAutomator2");
        capabilities.setCapability("appPackage", "com.swaglabsmobileapp");
        capabilities.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");

        driver = new AndroidDriver(capabilities);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public static void tearDown() {
        // Tear down the driver
        if (driver != null) {
            driver.quit();
        }
    }

    public static void resetApp() {
        // Reset the app
        driver.terminateApp("com.swaglabsmobileapp");
        driver.activateApp("com.swaglabsmobileapp");
    }
}
