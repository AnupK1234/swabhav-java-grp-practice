package com.practice.model;

public class FacebookLogin extends LoginTemplate {

    @Override
    boolean authenticate() {
        System.out.println("Authenticating with Facebook...");
        return false;
    }
}
