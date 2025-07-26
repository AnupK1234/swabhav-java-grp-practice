package com.aurionpro.fooddelivery.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.fooddelivery.dao.MenuItemDAO;
import com.aurionpro.fooddelivery.dao.OrderDAO;
import com.aurionpro.fooddelivery.enums.PaymentMode;
import com.aurionpro.fooddelivery.model.MenuItem;
import com.aurionpro.fooddelivery.model.Order;
import com.aurionpro.fooddelivery.model.OrderItem;

public class CustomerService {
	private Scanner sc = new Scanner(System.in);
	private List<OrderItem> cart = new ArrayList<>();
	private MenuItemDAO menuDao = new MenuItemDAO();
	private OrderDAO orderDao = new OrderDAO();
	private List<MenuItem> items;
	PaymentService pay;

	public void displayMenu() {
		try {
			items = menuDao.getAllItems();
			int choice;
			do {
				System.out.println("\n--- Customer Menu ---");
				System.out.println("1. View Menu");
				System.out.println("2. Add to Cart");
				System.out.println("3. View Cart");
				System.out.println("4. Place Order");
				System.out.println("0. Exit");
				System.out.print("Enter choice: ");
				choice = Integer.parseInt(sc.nextLine());

				switch (choice) {
				case 1 -> showMenu();
				case 2 -> addItemToCart();
				case 3 -> viewCart();
				case 4 -> placeOrder();
				case 0 -> System.out.println("Goodbye!");
				default -> System.out.println("Invalid choice");
				}
			} while (choice != 0);

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void showMenu() {
		System.out.printf("%-10s %-25s %-10s\n", "ID", "Name", "Price");
		items.forEach(
				item -> System.out.printf("%-10d %-25s %-10.2f\n", item.getId(), item.getName(), item.getPrice()));
	}

	private void addItemToCart() {
		System.out.print("Enter item ID or keyword: ");
		String input = sc.nextLine().toLowerCase();
		MenuItem matchedItem = null;

		if (input.matches("\\d+")) {
			long id = Long.parseLong(input);
			matchedItem = items.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
		} else {
			List<MenuItem> matched = items.stream().filter(i -> i.getName().toLowerCase().contains(input)).toList();
			if (matched.size() == 1)
				matchedItem = matched.get(0);
			else if (matched.size() > 1) {
				System.out.println("Multiple items matched:");
				for (int i = 0; i < matched.size(); i++) {
					System.out.printf("%d. %s - %.2f\n", i + 1, matched.get(i).getName(), matched.get(i).getPrice());
				}
				System.out.print("Choose item: ");
				int idx = Integer.parseInt(sc.nextLine()) - 1;
				matchedItem = matched.get(idx);
			}
		}

		if (matchedItem != null) {
			System.out.print("Enter quantity: ");
			int qty = Integer.parseInt(sc.nextLine());
			cart.add(new OrderItem(matchedItem, qty));
			System.out.println("Added to cart.");
		} else
			System.out.println("Item not found.");
	}

	private void viewCart() {
		if (cart.isEmpty())
			System.out.println("Cart is empty.");
		else {
			System.out.println("\n--- Your Cart ---");
			for (OrderItem item : cart) {
				System.out.printf("%s x%d = %.2f\n", item.getItem().getName(), item.getQuantity(),
						item.getItem().getPrice() * item.getQuantity());
			}
		}
	}

	private void placeOrder() {
		if (cart.isEmpty()) {
			System.out.println("Cart is empty.");
			return;
		}

		System.out.print("Enter delivery address: ");
		String deliveryAdd = sc.nextLine();
		if (deliveryAdd.isEmpty()) {
			System.out.println("Delivery address cannot be empty.");
			return;
		}

		System.out.println("Choose Payment Mode: 1. UPI  2. Cash");
		int paymentChoice;
		try {
			paymentChoice = sc.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input for payment mode.");
			sc.nextLine();
			return;
		}

		Order order;
		if (paymentChoice == 1) {
			order = new Order(cart, PaymentMode.UPI, deliveryAdd);
			pay = new PaymentService();
			pay.processPayment(order);
		} else if (paymentChoice == 2) {
			order = new Order(cart, PaymentMode.CASH, deliveryAdd);
			System.out.println("Payment mode selected: CASH. Please pay at your door step.");
		} else {
			System.out.println("Invalid payment choice.");
			return;
		}

		try {
			long orderId = orderDao.insertOrder(order);
			orderDao.insertOrderItems(orderId, cart);
			System.out.println("Order placed successfully! Assigned to: ");
			cart.clear();
		} catch (SQLException e) {
			System.out.println("Failed to place order: " + e.getMessage());
		}
	}
}
