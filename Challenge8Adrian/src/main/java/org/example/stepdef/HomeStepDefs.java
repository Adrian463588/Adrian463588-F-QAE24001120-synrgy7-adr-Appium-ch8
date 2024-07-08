package org.example.stepdef;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.example.BaseTest;
import org.example.page.HomePage;

public class HomeStepDefs extends BaseTest {

    HomePage homePage = new HomePage();

    @Then("user is on homepage")
    public void userIsOnLoginPage() {
        homePage.validateOnPage();
    }

    @Then("user see error message {string}")
    public void userSeeErrorMessage(String errorMessage) {
        homePage.validateErrorMessage(errorMessage);
    }

    @And("user sorts items from low to high")
    public void userSortsItemsLowToHigh() {
        homePage.sortItemsLowToHigh();
    }

    @Then("items should be sorted from low to high")
    public void itemsShouldBeSortedLowToHigh() {
        assert homePage.isSortedLowToHigh();
    }

    @And("user adds item {string} to the cart")
    public void userAddsItemToCart(String itemName) {
        homePage.addItemToCart(itemName);
    }

    @And("user goes to cart")
    public void userGoesToCart() {
        homePage.goToCart();
    }

    @Then("item {string} should be in the cart")
    public void itemShouldBeInCart(String itemName) {
        homePage.validateItemInCart(itemName);
    }


}
