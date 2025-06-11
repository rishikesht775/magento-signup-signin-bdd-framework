Feature: Account Signup and Signin Functionality on Magento Site

  Background:
    Given I am on the Magento homepage for signup

  @signup_valid
  Scenario: Successful account creation with valid details
    When I navigate to the Create Account page
    And I fill in the signup form with valid first name, last name, email, password and confirm password
    And I submit the signup registration form
   Then I should navigate to Account information page and should see the success message

  @signup_existing_email
  Scenario: Attempt to create account with an existing email
    When I navigate to the Create Account page
    And I fill in the signup form with an existing email address and valid details
    And I submit the signup registration form
    Then I should see an error message that email already exists during signup

  @signup_mismatch_password
  Scenario: Attempt to create account with mismatched passwords
    When I navigate to the Create Account page
    And I fill in the signup form with valid details but different password and confirm password
    And I submit the signup registration form
    Then I should see an error message for password mismatch during signup

  @signup_missing_fields
  Scenario: Attempt to create account with missing required fields
    When I navigate to the Create Account page
    And I leave one or more required fields blank in signup
    And I submit the signup registration form
    Then I should see validation messages for required fields in signup

  @signup_weak_password
  Scenario: Attempt to create account with weak password
    When I navigate to the Create Account page
    And I fill in the signup form with a password that does not meet strength criteria
    And I submit the signup registration form
    Then I should see a message indicating the password strength requirement during signup

  @signin_valid
  Scenario: Sign in with newly created valid credentials
    #Given I have created an account with email and password
    When I navigate to the Sign In page
    When I enter the registered email and password
    And I click on the Sign In button
    Then I should be signed in and see a welcome message

  @signin_invalid_password
  Scenario: Attempt to sign in with incorrect password
    When I navigate to the Sign In page
    And I enter a valid email with an incorrect password
    And I click on the Sign In button
    Then I should see an error message for incorrect credentials

  @signin_nonexisting_email
  Scenario: Attempt to sign in with non-existing email
    When I navigate to the Sign In page
    And I enter a non-registered email with any password
    And I click on the Sign In button
    Then I should see an error message for incorrect credentials

  @signin_blank_fields
  Scenario: Attempt to sign in with empty email and password fields
    When I navigate to the Sign In page
    And I leave email and password fields blank
    And I click on the Sign In button
    Then I should see validation messages for required fields
