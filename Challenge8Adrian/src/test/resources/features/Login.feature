@login @regression
Feature: Login

  @positive-test @smoke
  Scenario: User can login with valid credentials
    Given user is on login page
    And user input username with "standard_user"
    And user input password with "secret_sauce"
    When user click on login button
    Then user is on homepage

  @negative-test
  Scenario: Login with invalid email and password
    Given user is on login page
    And user input username with "invalid_user"
    And user input password with "invalid_password"
    When user click on login button
    Then user see error message "Username and password do not match any user in this service"