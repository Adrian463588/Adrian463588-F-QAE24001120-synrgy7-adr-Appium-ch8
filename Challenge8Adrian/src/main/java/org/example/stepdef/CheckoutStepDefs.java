package org.example.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.example.BaseTest;
import org.example.page.CheckoutPage;

public class CheckoutStepDefs extends BaseTest {

   CheckoutPage checkoutPage = new CheckoutPage();

    @And("user proceeds to checkout")
    public void userProceedsToCheckout() {
        checkoutPage.proceedToCheckout();
    }

    @And("user fills checkout info with {string}, {string}, and {string}")
    public void userFillsCheckoutInfo(String firstName, String lastName, String postalCode) {
        checkoutPage.fillCheckoutInfo(firstName, lastName, postalCode);
    }

    @And("user finishes the checkout process")
    public void userFinishesCheckout() {
        checkoutPage.finishCheckout();
    }

    @Then("user should see success message")
    public void userShouldSeeSuccessMessage() {
        checkoutPage.validateSuccessPage();
    }
}