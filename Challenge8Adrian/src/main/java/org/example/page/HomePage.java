package org.example.page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.example.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class HomePage extends BaseTest {

    // Define locators
    By sortMenu = AppiumBy.xpath("//android.widget.ImageView[@bounds='[1265,338][1405,478]']");
    By lowToHighOption = AppiumBy.xpath("//android.widget.TextView[@text='Price (low to high)']");
    By priceElements = AppiumBy.xpath("//android.widget.TextView[@content-desc='test-Price']");
    By cartButton = AppiumBy.accessibilityId("test-Cart");


    By labelProducts = AppiumBy.xpath("//android.widget.TextView[@text='PRODUCTS']");
    By labelSort = AppiumBy.xpath("//android.widget.TextView[@text='Sort items by...']");

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public void validateOnPage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(cartButton));
    }


    public void validateErrorMessage(String message) {
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath(String.format("//*[contains(@text, '%s')]", message))));
    }

    public void sortItemsLowToHigh() {
        // Assertion to check if the user is on the products page
        wait.until(ExpectedConditions.presenceOfElementLocated(labelProducts));

        wait.until(ExpectedConditions.elementToBeClickable(sortMenu)).click();

        // Assertion to check if the user is on the sort menu
        wait.until(ExpectedConditions.presenceOfElementLocated(labelSort));

        wait.until(ExpectedConditions.elementToBeClickable(lowToHighOption)).click();
    }

    public boolean isSortedLowToHigh() {
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
        By addToCartButton = AppiumBy.xpath(String.format("//*[@text='%s']/following-sibling::*[@content-desc='test-ADD TO CART']", itemName));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addButton.click();
    }

    public void goToCart() {
        while (!isElementDisplayed(cartButton)) {
            scrollDown();
        }
        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cartIcon.click();
    }

    private boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void scrollDown() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    public void validateItemInCart(String itemName) {
        By itemLocator = AppiumBy.xpath(String.format("//*[@text='%s']", itemName));
        WebElement itemElement = wait.until(ExpectedConditions.presenceOfElementLocated(itemLocator));
        assert itemElement.isDisplayed() : "Item " + itemName + " is not displayed in the cart.";
    }
}
