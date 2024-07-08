package org.example.page;

import io.appium.java_client.AppiumBy;
import org.example.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BaseTest {

    // Define locators
    By loginTextBox = AppiumBy.accessibilityId("test-Username");
    By passwordTextBox = AppiumBy.accessibilityId("test-Password");
    By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    By errorMessage = AppiumBy.xpath("//*[contains(@text, 'Username and password do not match any user in this service')]");


    public void validateOnPage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginTextBox));
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordTextBox));
        wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
    }

    public void inputUsername(String username) {
        driver.findElement(loginTextBox).sendKeys(username);
    }

    public void inputPassword(String password) {
        driver.findElement(passwordTextBox).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
