package com.magento.automation.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.magento.automation.enums.UserData;
import com.magento.automation.pages.HomePage;
import com.magento.automation.pages.SignInPage;
import com.magento.automation.utils.DriverManager;
import com.magento.automation.utils.ScreenshotUtil;

import io.cucumber.java.en.*;

public class SignInSteps {

    WebDriver driver = DriverManager.getDriver();
    HomePage homePage = new HomePage(driver);
    SignInPage signInPage = new SignInPage(driver);

    @When("I navigate to the Sign In page")
    public void navigate_to_signin_page() {
        driver.get("https://magento.softwaretestingboard.com/");
        //ScreenshotUtil.takeScreenshot(driver, "HomePageBeforeSignin");
        homePage.clickSignIn();
        ScreenshotUtil.takeScreenshot(driver, "SignInPageLoaded");
    }

    @When("I enter the registered email and password")
    public void enter_registered_credentials() {
        String email = UserData.SIGNIN_VALID_EMAIL.getValue();
        String password = UserData.SIGNIN_VALID_PASSWORD.getValue();
        signInPage.enterEmailAndPassword(email, password);
    }

    @When("I enter a valid email with an incorrect password")
    public void enter_invalid_password() {
        String validEmail = UserData.SIGNIN_VALID_EMAIL.getValue();
        String invalidPassword = UserData.SIGNIN_INVALID_PASSWORD.getValue(); 
        signInPage.enterEmail(validEmail);
        signInPage.enterPassword(invalidPassword); 
        ScreenshotUtil.takeScreenshot(driver, "InvalidPasswordEntered");
    }

    @When("I enter a non-registered email with any password")
    public void enter_non_registered_email() {
        signInPage.enterEmail("nonexisting" + System.currentTimeMillis() + "@test.com");
        signInPage.enterPassword("SomePassword123!");
        ScreenshotUtil.takeScreenshot(driver, "NonExistingEmailEntered");
    }

    @When("I leave email and password fields blank")
    public void leave_email_and_password_blank() {
        signInPage.enterEmail("");
        signInPage.enterPassword("");
        ScreenshotUtil.takeScreenshot(driver, "BlankFieldsEntered");
    }

    @When("I click on the Sign In button")
    public void click_signin_button() {
        signInPage.clickSignIn();
        ScreenshotUtil.takeScreenshot(driver, "ClickedSignIn");
    }

    @Then("I should be signed in and see a welcome message")
    public void verifySuccessfulSignIn() {
        Assert.assertTrue(signInPage.isHomePageDispalyed(), "Home page not displayed after sign in");
        ScreenshotUtil.takeScreenshot(driver, "WelcomeMessage");
        }

    

    @Then("I should see an error message for incorrect credentials")
    public void verify_error_invalid_credentials() {
        Assert.assertTrue(driver.getPageSource().contains("The account sign-in was incorrect") ||
                          driver.getPageSource().contains("No account found"), 
                          "Error message not found for invalid credentials!");
        ScreenshotUtil.takeScreenshot(driver, "ErrorInvalidCredentials");
    }

    @Then("I should see validation messages for required fields")
    public void verify_blank_field_validation() {
        Assert.assertTrue(driver.getPageSource().contains("This is a required field."),
                          "Validation message not found for blank fields!");
        ScreenshotUtil.takeScreenshot(driver, "BlankFieldValidation");
    }
}
