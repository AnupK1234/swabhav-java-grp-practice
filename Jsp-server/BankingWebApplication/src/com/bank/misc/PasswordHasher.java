package com.bank.misc;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {

    /**
     * Hashes a password using the SHA-256 algorithm.
     *
     * @param password The plain-text password string to hash.
     * @return The SHA-256 hashed password as a Base64-encoded string.
     */
    public String hashPassword(String password) {
        try {
            // Get an instance of the SHA-256 MessageDigest
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Hash the password string bytes
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Encode the resulting bytes as a Base64 string for easy storage
            return Base64.getEncoder().encodeToString(hashedBytes);

        } catch (NoSuchAlgorithmException e) {
            // This exception should never occur for SHA-256
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Verifies a password by hashing the input and comparing with stored hash.
     *
     * @param inputPassword Plain-text password entered by user
     * @param storedHash    Hash stored in the database
     * @return true if match, false otherwise
     */
    public boolean verifyPassword(String inputPassword, String storedHash) {
        String hashedInput = hashPassword(inputPassword);
        return hashedInput != null && hashedInput.equals(storedHash);
    }
}
