package com.practice.model;

public abstract class LoginTemplate {
    
    // Template method
    public final void loginProcess() {
        initialize();
        if (authenticate()) {
            showSuccessMessage();
        } else {
            showFailureMessage();
        }
    }

    void initialize() {
        System.out.println("Initializing login...");
    }

    abstract boolean authenticate();

    void showSuccessMessage() {
        System.out.println("Login Successful!");
    }

    void showFailureMessage() {
        System.out.println("Login Failed!");
    }
}
