package com.aurionpro.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.aurionpro.dao.UserDao;
import com.aurionpro.model.User;

public class AuthService {

	private UserDao userDao;

	public AuthService() {
		this.userDao = new UserDao();
	}

	public String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
			BigInteger number = new BigInteger(1, hash);
			StringBuilder hexString = new StringBuilder(number.toString(16));

			while (hexString.length() < 64) {
				hexString.insert(0, '0');
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 algorithm not found", e);
		}
	}

	public User authenticate(String email, String plainTextPassword) {
		User user = userDao.findUserByEmail(email);

		if (user != null) {
			String hashedPassword = hashPassword(plainTextPassword);
			if (user.getPassword().equals(hashedPassword)) {
				return user;
			}
		}
		return null;
	}
}
