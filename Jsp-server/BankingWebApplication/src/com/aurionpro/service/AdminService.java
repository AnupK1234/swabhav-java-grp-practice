package com.aurionpro.service;

import java.sql.SQLException;
import java.util.List;

import com.aurionpro.Dao.UserDao;
import com.aurionpro.model.Role;
import com.aurionpro.model.User;
import com.bank.misc.PasswordHasher;

public class AdminService {

    private UserDao userDao;

    public AdminService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllCustomer() throws SQLException {
        return userDao.getCustomer(Role.CUSTOMER);
    }

    public boolean createUser(String username, String plainPassword, String firstName, 
                              String lastName, int accountNumber, double balance) throws SQLException {
        // 1. Hash the password before saving
        PasswordHasher hasher = new PasswordHasher();
        String hashedPassword = hasher.hashPassword(plainPassword);

        // 2. Create a new User object
        User newUser = new User(firstName, username, lastName, balance, accountNumber, hashedPassword, Role.CUSTOMER);

        // 3. Call the DAO to save the user
        return userDao.addUser(newUser);
    }
}
