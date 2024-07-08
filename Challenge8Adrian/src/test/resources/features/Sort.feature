@Sort
Feature: Sort

  @SortLowToHigh
  Scenario: Sorting items from low to high price
    Given user is on login page
    And user input username with "standard_user"
    And user input password with "secret_sauce"
    When user click on login button
    And user sorts items from low to high
    Then items should be sorted from low to high

