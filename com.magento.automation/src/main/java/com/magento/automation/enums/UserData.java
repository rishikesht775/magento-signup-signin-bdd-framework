package com.magento.automation.enums;

public enum UserData {

    // Sign Up Data
    VALID_FIRST_NAME("John"),
    VALID_LAST_NAME("Doe"),
    VALID_PASSWORD("Test@1234"),
    MISMATCHED_PASSWORD("Mismatch@123"),
    WEAK_PASSWORD("123"),
    EXISTING_FIRST_NAME("Jane"),
    EXISTING_LAST_NAME("Smith"),
    EXISTING_EMAIL("jane.smith@test.com"),

    // Sign In Data
    SIGNIN_VALID_EMAIL("jane.smith@test.com"),
    SIGNIN_VALID_PASSWORD("Test@1234"),
    SIGNIN_INVALID_PASSWORD("WrongPass123"),
    SIGNIN_NON_EXISTING_EMAIL("nonuser" + System.currentTimeMillis() + "@mailinator.com");

    private final String value;

    UserData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
