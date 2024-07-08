package org.example.stepdef;


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


}
