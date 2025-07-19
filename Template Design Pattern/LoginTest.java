package com.practice.test;

import com.practice.model.EmailLogin;
import com.practice.model.FacebookLogin;
import com.practice.model.GoogleLogin;
import com.practice.model.LoginTemplate;

//LoginTemplate defines the template method loginProcess()
// Code reuse,Flexibility and extensibility, Consistent algorithm structure

public class LoginTest {
	 public static void main(String[] args) {
	        System.out.println("Google Login:");
	        LoginTemplate google = new GoogleLogin();
	        google.loginProcess();

	        System.out.println("\nFacebook Login:");
	        LoginTemplate facebook = new FacebookLogin();
	        facebook.loginProcess();

	        System.out.println("\nEmail Login:");
	        LoginTemplate email = new EmailLogin();
	        email.loginProcess();
	    }
}
