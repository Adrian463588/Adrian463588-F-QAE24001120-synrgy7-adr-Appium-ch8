package org.example.page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.example.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class CheckoutPage extends BaseTest {

    // Define locators
    By checkoutButton = AppiumBy.xpath("//android.view.ViewGroup[@class='android.view.ViewGroup' and @content-desc='test-CHECKOUT']");
    By firstNameField = AppiumBy.accessibilityId("test-First Name");
    By lastNameField = AppiumBy.accessibilityId("test-Last Name");
    By postalCodeField = AppiumBy.accessibilityId("test-Zip/Postal Code");
    By continueButton = AppiumBy.accessibilityId("test-CONTINUE");
    By finishButton = AppiumBy.accessibilityId("test-FINISH");
    By successMessage = AppiumBy.xpath("//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']");

    By labelYourCart = AppiumBy.xpath("//android.widget.TextView[@text='YOUR CART']");
    By labelCheckoutInfo = AppiumBy.xpath("//android.widget.TextView[@text='CHECKOUT: INFORMATION']");
    By labelCheckoutOverview = AppiumBy.xpath("//android.widget.TextView[@text='CHECKOUT: OVERVIEW']");
    By labelFinish = AppiumBy.xpath("//android.widget.TextView[@text='CHECKOUT: COMPLETE!']");

    public void proceedToCheckout() {
        // Assertion to check if the user is on the cart page
        wait.until(ExpectedConditions.presenceOfElementLocated(labelYourCart));

        scrollToElement(checkoutButton);
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutBtn.click();
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        // Assertion to check if the user is on the checkout info page
        wait.until(ExpectedConditions.presenceOfElementLocated(labelCheckoutInfo));

        WebElement firstNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        WebElement lastNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        WebElement postalCodeElem = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField));
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));

        firstNameElem.sendKeys(firstName);
        lastNameElem.sendKeys(lastName);
        postalCodeElem.sendKeys(postalCode);
        continueBtn.click();
    }

    public void finishCheckout() {
        // Assertion to check if the user is on the checkout overview page
        wait.until(ExpectedConditions.presenceOfElementLocated(labelCheckoutOverview));

        scrollToElement(finishButton);
        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finishBtn.click();
    }

    public void validateSuccessPage() {
        // Assertion to check if the user is on the success page
        wait.until(ExpectedConditions.presenceOfElementLocated(labelFinish));

        WebElement successElem = wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
        assert successElem.isDisplayed();
    }

    private void scrollToElement(By element) {
        boolean isFound = false;
        while (!isFound) {
            try {
                driver.findElement(element);
                isFound = true;
            } catch (Exception e) {
                // Scroll down
                (driver).findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
            }
        }
    }
}
