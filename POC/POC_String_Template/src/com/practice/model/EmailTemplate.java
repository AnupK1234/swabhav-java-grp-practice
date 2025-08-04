package com.practice.model;

public class EmailTemplate {
    public static void main(String[] args) {
        String user = "Vivek";
        String resetLink = "https://example.com/reset-password?token=abc123";

        String email = String.format("""
                Subject: Password Reset Instructions

                Hi %s,

                We received a request to reset your password.
                You can reset it by clicking the link below:

                %s

                If you didn’t request this, please ignore this message.

                Best regards,
                Support Team
                """, user, resetLink);

        System.out.println(email);
    }
}
