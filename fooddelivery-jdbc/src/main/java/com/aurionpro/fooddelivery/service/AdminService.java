package com.aurionpro.fooddelivery.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.fooddelivery.dao.MenuItemDAO;
import com.aurionpro.fooddelivery.exception.NegativePriceException;
import com.aurionpro.fooddelivery.model.MenuItem;
import struqt.util.UniqueIdGenerator;

public class AdminService {
	private final MenuItemDAO menuItemDAO = new MenuItemDAO();
	private final UniqueIdGenerator generator = new UniqueIdGenerator(1L);

	public void displayMenu() {
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\n--- Admin Menu ---");
			System.out.println("1. Add Menu Item");
			System.out.println("2. Update Menu");
			System.out.println("3. Delete Menu Item");
			System.out.println("4. View menu");
			System.out.println("5. Delivery Partner management");
			System.out.println("0. Exit");
			System.out.print("Enter choice: ");
			choice = sc.nextInt();
			sc.nextLine();

			try {
				switch (choice) {
				case 1 -> addMenuItem(sc);
				case 2 -> updateMenuItem(sc);
				case 3 -> deleteMenuItem(sc);
				case 4 -> viewAllMenuItems();
				case 5 -> DeliveryService.adminMenu(sc);
				case 0 -> System.out.println("Exiting...");
				default -> System.out.println("Invalid choice.");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		} while (choice != 0);
	}

	private void addMenuItem(Scanner sc) throws SQLException {
		long id = generator.next();
		System.out.print("Enter item name: ");
		String name = sc.nextLine();

		System.out.print("Enter item Price: ");
		double price = sc.nextDouble();

		if (price <= 0)
			throw new NegativePriceException("Price can't be negative or zero");

		menuItemDAO.addMenuItem(new MenuItem(id, name, price));
		System.out.println("Item added to menu.");
	}

	private void viewAllMenuItems() throws SQLException {
		List<MenuItem> items = menuItemDAO.getAllItems();
		if (items.isEmpty()) {
			System.out.println("No items found.");
		} else {
			System.out.println("\n-----------------------------------------------------------");
			System.out.printf("| %-20s | %-20s | %-10s |\n", "ID", "Name", "Price");
			System.out.println("-----------------------------------------------------------");
			for (MenuItem item : items) {
				System.out.printf("| %-20d | %-20s | %-10.2f |\n", item.getId(), item.getName(), item.getPrice());
			}
			System.out.println("-----------------------------------------------------------");
		}
	}

	private void updateMenuItem(Scanner sc) throws SQLException {
		System.out.print("Enter Item ID to update: ");
		long id = sc.nextLong();
		sc.nextLine();

		System.out.print("Enter new name: ");
		String name = sc.nextLine();
		System.out.print("Enter new price: ");
		double price = sc.nextDouble();

		MenuItem item = new MenuItem(id, name, price);
		if (menuItemDAO.updateMenuItem(item)) {
			System.out.println("Item updated.");
		} else {
			System.out.println("Item not found.");
		}
	}

	private void deleteMenuItem(Scanner sc) throws SQLException {
		System.out.print("Enter Item ID to delete: ");
		long id = sc.nextLong();
		if (menuItemDAO.deleteMenuItem(id)) {
			System.out.println("Menu Item deleted.");
		} else {
			System.out.println("Item not found.");
		}
	}
}
