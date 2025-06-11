// SignUpSteps.java
package com.magento.automation.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.magento.automation.enums.UserData;
import com.magento.automation.pages.HomePage;
import com.magento.automation.pages.SignUpPage;
import com.magento.automation.utils.DriverManager;
import com.magento.automation.utils.ScreenshotUtil;

import io.cucumber.java.en.*;

public class SignUpSteps {
    WebDriver driver = DriverManager.getDriver();
    HomePage homePage = new HomePage(driver);
    SignUpPage signUpPage = new SignUpPage(driver);
    static String registeredEmail;
    static String registeredPassword = UserData.VALID_PASSWORD.getValue();

    @Given("I am on the Magento homepage for signup")
    public void i_am_on_homepage_for_signup() {
        driver.get("https://magento.softwaretestingboard.com/");
        ScreenshotUtil.takeScreenshot(driver, "HomePage");
    }

    @Given("I have created an account with email and password")
    public void i_have_created_account() {
        //driver.get("https://magento.softwaretestingboard.com/");
        homePage.clickCreateAccount();
        ScreenshotUtil.takeScreenshot(driver, "CreateAccountPageFromSignin");

        signUpPage.enterFirstName(UserData.VALID_FIRST_NAME.getValue());
        signUpPage.enterLastName(UserData.VALID_LAST_NAME.getValue());
        registeredEmail = "user" + System.currentTimeMillis() + "@test.com";
        signUpPage.enterEmail(registeredEmail);
        signUpPage.enterPassword(registeredPassword);
        signUpPage.enterConfirmPassword(registeredPassword);
        ScreenshotUtil.takeScreenshot(driver, "FilledValidDetailsForSignin");

        signUpPage.clickCreateAccount();
        ScreenshotUtil.takeScreenshot(driver, "AccountCreatedForSignin");
    }

    @When("I navigate to the Create Account page")
    public void navigate_to_create_account() {
        homePage.clickCreateAccount();
        ScreenshotUtil.takeScreenshot(driver, "CreateAccountPage");
    }

    @When("I fill in the signup form with valid first name, last name, email, password and confirm password")
    public void fill_form_valid_details_signup() {
        signUpPage.enterFirstName(UserData.VALID_FIRST_NAME.getValue());
        signUpPage.enterLastName(UserData.VALID_LAST_NAME.getValue());
        signUpPage.enterEmail("john.doe" + System.currentTimeMillis() + "@test.com");
        signUpPage.enterPassword(UserData.VALID_PASSWORD.getValue());
        signUpPage.enterConfirmPassword(UserData.VALID_PASSWORD.getValue());
        ScreenshotUtil.takeScreenshot(driver, "FilledValidDetails");
    }

    @When("I fill in the signup form with an existing email address and valid details")
    public void fill_form_existing_email_signup() {
        signUpPage.enterFirstName(UserData.EXISTING_FIRST_NAME.getValue());
        signUpPage.enterLastName(UserData.EXISTING_LAST_NAME.getValue());
        signUpPage.enterEmail(UserData.EXISTING_EMAIL.getValue());
        signUpPage.enterPassword(UserData.VALID_PASSWORD.getValue());
        signUpPage.enterConfirmPassword(UserData.VALID_PASSWORD.getValue());
        ScreenshotUtil.takeScreenshot(driver, "FilledExistingEmail");
    }

    @When("I fill in the signup form with valid details but different password and confirm password")
    public void fill_form_mismatched_passwords_signup() {
        signUpPage.enterFirstName(UserData.VALID_FIRST_NAME.getValue());
        signUpPage.enterLastName(UserData.VALID_LAST_NAME.getValue());
        signUpPage.enterEmail("alice" + System.currentTimeMillis() + "@test.com");
        signUpPage.enterPassword(UserData.VALID_PASSWORD.getValue());
        signUpPage.enterConfirmPassword(UserData.MISMATCHED_PASSWORD.getValue());
        ScreenshotUtil.takeScreenshot(driver, "MismatchedPasswords");
    }

    @When("I leave one or more required fields blank in signup")
    public void leave_required_fields_blank_signup() {
        signUpPage.enterFirstName("");
        signUpPage.enterLastName("");
        signUpPage.enterEmail("");
        signUpPage.enterPassword("");
        signUpPage.enterConfirmPassword("");
        ScreenshotUtil.takeScreenshot(driver, "BlankFields");
    }

    @When("I fill in the signup form with a password that does not meet strength criteria")
    public void fill_form_weak_password_signup() {
        signUpPage.enterFirstName("Weak");
        signUpPage.enterLastName("Pass");
        signUpPage.enterEmail("weak" + System.currentTimeMillis() + "@test.com");
        signUpPage.enterPassword(UserData.WEAK_PASSWORD.getValue());
        signUpPage.enterConfirmPassword(UserData.WEAK_PASSWORD.getValue());
        ScreenshotUtil.takeScreenshot(driver, "WeakPassword");
    }

    @When("I submit the signup registration form")
    public void submit_registration_form_signup() {
        signUpPage.clickCreateAccount();
        ScreenshotUtil.takeScreenshot(driver, "SubmitForm");
    }

    @Then("I should navigate to Account information page and should see the success message")
    public void verify_success_message_signup() {
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Thank you for registering with Main Website Store."),
            "Expected success message not found on Account Information page.");
        ScreenshotUtil.takeScreenshot(driver, "AccountInfoPage_SuccessMessage");
    }

    @Then("I should see an error message that email already exists during signup")
    public void verify_existing_email_error_signup() {
        Assert.assertTrue(driver.getPageSource().contains("There is already an account with this email address"));
        ScreenshotUtil.takeScreenshot(driver, "ExistingEmailError");
    }

    @Then("I should see an error message for password mismatch during signup")
    public void verify_password_mismatch_error_signup() {
        Assert.assertTrue(driver.getPageSource().contains("Please enter the same value again."));
        ScreenshotUtil.takeScreenshot(driver, "PasswordMismatchError");
    }

    @Then("I should see validation messages for required fields in signup")
    public void verify_required_field_errors_signup() {
        Assert.assertTrue(driver.getPageSource().contains("This is a required field."));
        ScreenshotUtil.takeScreenshot(driver, "RequiredFieldError");
    }

    @Then("I should see a message indicating the password strength requirement during signup")
    public void verify_weak_password_message_signup() {
        Assert.assertTrue(driver.getPageSource().contains("Minimum length of this field must be equal or greater than 8 symbols"));
        ScreenshotUtil.takeScreenshot(driver, "PasswordStrengthError");
    }
}
// End of SignUpSteps
