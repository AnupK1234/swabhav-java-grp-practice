package com.aurionpro.service;

public class EmailService {
	public static void sendOtp(String to, String otp) {
		try {
			// Use Resend API (or JavaMail SMTP)
			System.out.println("Sending OTP " + otp + " to " + to);
			// call API here
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
