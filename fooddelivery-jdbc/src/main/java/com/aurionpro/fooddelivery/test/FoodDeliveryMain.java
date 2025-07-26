package com.aurionpro.fooddelivery.test;

import java.util.Scanner;

import com.aurionpro.fooddelivery.service.AdminService;
import com.aurionpro.fooddelivery.service.CustomerService;

public class FoodDeliveryMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Sasta Zomato! We don't charge processing fees ;) 😉");

		try {
			while (true) {
				System.out.print("Login as (admin/user/exit): ");
				String role = sc.nextLine().trim();

				if (role == null || role.isEmpty()) {
					System.out.println("Input cannot be empty. Please enter a valid role.");
					continue;
				}

				switch (role.toLowerCase()) {
				case "admin" -> {
					try {
						AdminService admin = new AdminService();
						admin.displayMenu();
					} catch (Exception e) {
						System.out.println("Error while running admin panel: " + e.getMessage());
					}
				}
				case "user" -> {
					try {
						CustomerService customer = new CustomerService();
						customer.displayMenu();
					} catch (Exception e) {
						System.out.println("Error while running customer panel: " + e.getMessage());
					}
				}
				case "exit" -> {
					System.out.println("Thank you for using Sasta Zomato!");
					return;
				}
				default -> {
					System.out.println("Invalid role. Please type 'admin', 'user' or 'exit'.");
				}
				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e.getMessage());
		} finally {
			sc.close();
		}
	}
}
