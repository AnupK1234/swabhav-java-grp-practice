package com.aurionpro.Mockito;

public class UserService {
	private UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	public String getUserName(int id) {
		User user = repo.findById(id);
		return user != null ? user.getName() : "Unknown";
	}
}
