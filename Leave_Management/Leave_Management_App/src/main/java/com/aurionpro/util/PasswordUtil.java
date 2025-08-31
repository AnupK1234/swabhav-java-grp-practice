package com.aurionpro.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

public final class PasswordUtil {

    private static final String ALGO = "SHA-256";
    private static final SecureRandom RANDOM = new SecureRandom();

    private PasswordUtil() {}

    // generate random salt of given length (bytes)
    public static byte[] generateSalt(int length) {
        byte[] salt = new byte[length];
        RANDOM.nextBytes(salt);
        return salt;
    }

    // iterative SHA-256: digest = SHA256(digest || password) starting from SHA256(salt || password)
    public static byte[] hashSha256(byte[] salt, String password, int iterations) {
        Objects.requireNonNull(salt);
        Objects.requireNonNull(password);
        if (iterations < 1) throw new IllegalArgumentException("iterations must be >= 1");

        try {
            MessageDigest md = MessageDigest.getInstance(ALGO);
            // initial: sha256(salt || password)
            md.update(salt);
            md.update(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            byte[] digest = md.digest();

            // further iterations: digest = sha256(digest || password)
            for (int i = 1; i < iterations; i++) {
                md.reset();
                md.update(digest);
                md.update(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
                digest = md.digest();
            }
            return digest;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }

    // Create storage string: iterations:saltBase64:hashBase64
    public static String generateStorageString(String password, int saltBytes, int iterations) {
        byte[] salt = generateSalt(saltBytes);
        byte[] hash = hashSha256(salt, password, iterations);
        String saltB64 = Base64.getEncoder().encodeToString(salt);
        String hashB64 = Base64.getEncoder().encodeToString(hash);
        return iterations + ":" + saltB64 + ":" + hashB64;
    }

    // Verify password against stored string
    public static boolean verifyPassword(String password, String storage) {
        if (password == null || storage == null) return false;
        String[] parts = storage.split(":");
        if (parts.length != 3) return false;
        int iterations;
        try {
            iterations = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            return false;
        }
        byte[] salt = Base64.getDecoder().decode(parts[1]);
        byte[] expectedHash = Base64.getDecoder().decode(parts[2]);
        byte[] actualHash = hashSha256(salt, password, iterations);
        return constantTimeEquals(expectedHash, actualHash);
    }

    // constant-time array comparison to avoid timing attacks
    private static boolean constantTimeEquals(byte[] a, byte[] b) {
        if (a == null || b == null) return false;
        if (a.length != b.length) return false;
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result |= a[i] ^ b[i];
        }
        return result == 0;
    }
}

