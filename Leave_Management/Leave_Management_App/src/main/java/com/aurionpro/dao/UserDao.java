package com.aurionpro.dao;

public class UserDao {
	
	void addUser(User user);
    User getUserById(int id);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);

    User login(String username, String password);

    List<User> getAllManagers();
    List<User> getEmployeesByManager(int managerId);
    List<User> getAllEmployees();

    int getLeaveBalance(int userId);
    void updateLeaveBalance(int userId, int newBalance);
    void resetAnnualLeaveQuota(int userId);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean isManager(int userId);

}
