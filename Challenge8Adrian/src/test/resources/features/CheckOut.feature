@Checkout
Feature: Checkout

  @AddToCart @CheckoutProcess @SuccessMessage
  Scenario: Add items to cart and checkout
    Given user is on login page
    And user input username with "standard_user"
    And user input password with "secret_sauce"
    When user click on login button
    And user adds item "Sauce Labs Backpack" to the cart
    And user adds item "Sauce Labs Bike Light" to the cart
    And user goes to cart
    And user proceeds to checkout
    And user fills checkout info with "Disana", "Disini", and "12345"
    And user finishes the checkout process
    Then user should see success message
