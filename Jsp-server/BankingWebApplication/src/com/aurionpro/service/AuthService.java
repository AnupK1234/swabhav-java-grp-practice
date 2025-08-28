package com.aurionpro.service;

import com.aurionpro.Dao.UserDao;
import com.aurionpro.misc.PasswordHasher;
import com.aurionpro.model.User;

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

        if (hasher.verifyPassword(password, userFromDb.getPassword())) {
            return userFromDb;
        }


        return null; // wrong password
    }
}
