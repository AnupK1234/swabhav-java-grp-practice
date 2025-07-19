package com.practice.model;

public class EmailLogin extends LoginTemplate {

    @Override
    boolean authenticate() {
        System.out.println("Authenticating with Email and Password...");
        return true;
    }
}
