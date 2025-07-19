package com.practice.model;

public class GoogleLogin extends LoginTemplate {

	@Override
	boolean authenticate() {
		System.out.println("Authenticating with Google...");

		return true;
	}
}
