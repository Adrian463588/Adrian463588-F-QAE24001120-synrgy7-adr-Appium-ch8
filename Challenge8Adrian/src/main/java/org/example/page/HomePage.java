package org.example.page;

import io.appium.java_client.AppiumBy;
import org.example.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BaseTest {

    By sortMenu = AppiumBy.xpath("//android.widget.ImageView[@bounds='[1265,338][1405,478]']");
    By lowToHighOption = AppiumBy.xpath("//android.widget.TextView[@text='Price (low to high)']");
    By priceElements = AppiumBy.xpath("//android.widget.TextView[@content-desc='test-Price']");
    By cartButton = AppiumBy.accessibilityId("test-Cart");

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

    public void sortItemsLowToHigh() {
        // Click on the sort menu

        wait.until(ExpectedConditions.elementToBeClickable(sortMenu)).click();

        // Select 'Price (low to high)'

        wait.until(ExpectedConditions.elementToBeClickable(lowToHighOption)).click();
    }

    public boolean isSortedLowToHigh() {
        // Wait for the sorted prices to be visible

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(priceElements));

        List<WebElement> prices = driver.findElements(priceElements);
        double previousPrice = 0.0;
        for (WebElement priceElement : prices) {
            String priceText = priceElement.getText().replace("$", "");
            double currentPrice = Double.parseDouble(priceText);
            if (currentPrice < previousPrice) {
                return false;
            }
            previousPrice = currentPrice;
        }
        return true;


    }

    public void addItemToCart(String itemName) {
        By addToCartButton = AppiumBy.xpath(String.format("(//android.view.ViewGroup[@class='android.view.ViewGroup' and @content-desc='test-ADD TO CART'])[1]", itemName));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addButton.click();
    }

    public void goToCart() {

        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cartIcon.click();
    }

    public void validateItemInCart(String itemName) {
        By itemLocator = AppiumBy.xpath(String.format("//*[@text='%s']", itemName));
        WebElement itemElement = wait.until(ExpectedConditions.presenceOfElementLocated(itemLocator));
        assert itemElement.isDisplayed() : "Item " + itemName + " is not displayed in the cart.";
    }



}
