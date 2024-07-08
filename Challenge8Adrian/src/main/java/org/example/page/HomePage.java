package org.example.page;

import io.appium.java_client.AppiumBy;
import org.example.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BaseTest {

    public void validateOnPage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("test-Cart")));
    }

    public void swipeToElementWithText(String text) {
        driver.findElement(
                new AppiumBy.ByAndroidUIAutomator(
                        String.format(
                                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"%s\"))",
                                text)));
    }

    public void validateErrorMessage(String message) {
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath(String.format("//*[contains(@text, '%s')]", message))));
    }


}
