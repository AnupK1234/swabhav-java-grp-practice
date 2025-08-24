package com.aurionpro.service;

import com.aurionpro.Dao.UserDao;
import com.aurionpro.model.User;
import com.bank.misc.PasswordHasher;

public class AuthService {
    private final UserDao userDao;
    private final PasswordHasher hasher;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
        this.hasher = new PasswordHasher();
    }

    public User authenticate(String username, String password) throws Exception {
        User userFromDb = userDao.getUserByUserName(username);

        if (userFromDb == null) {
            return null; // no such user
        }

        String hashedInput = hasher.hashPassword(password);

        if (hashedInput.equals(userFromDb.getPassword())) {
            return userFromDb; // valid user
        }

        return null; // wrong password
    }
}
