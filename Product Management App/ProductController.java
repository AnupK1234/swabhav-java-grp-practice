package com.practice.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.practice.model.Customer;
import com.practice.model.LineItem;
import com.practice.model.Order;
import com.practice.model.Product;
import com.practice.model.ProductManager;

public class ProductController {
	private static Scanner sc = new Scanner(System.in);
	private static ProductManager productManager = new ProductManager();
	private static List<LineItem> cart = new ArrayList<>();

	public static void main(String[] args) {
		while (true) {
			System.out.println("1. Admin");
			System.out.println("2. Customer");
			System.out.println("3. Exit");
			System.out.print("Choose role: ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				adminMenu();
				break;
			case 2:
				customerMenu();
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option.");
				break;
			}

		}
	}

	private static void adminMenu() {
		while (true) {
			System.out.println("\n--- Admin Menu ---");
			System.out.println("1. Add Product");
			System.out.println("2. Remove Product");
			System.out.println("3. Update Product");
			System.out.println("4. Display All Products");
			System.out.println("5. Back to Main Menu");
			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				addProduct();
				break;
			case 2:
				removeProduct();
				break;
			case 3:
				updateProduct();
				break;
			case 4:
				displayProducts();
				break;
			case 5:
				return;
			default:
				System.out.println("Invalid option.");
				break;
			}

		}
	}

	private static void customerMenu() {
		sc.nextLine(); 
		System.out.print("Enter your name: ");
		String name = sc.nextLine();
		Customer customer = new Customer(1, name);
		cart.clear();

		while (true) {
			System.out.println("\n--- Customer Menu ---");
			System.out.println("1. View All Products");
			System.out.println("2. Add Item to Cart");
			System.out.println("3. View Cart");
			System.out.println("4. Remove Item from Cart");
			System.out.println("5. Place Order & Get Bill");
			System.out.println("6. Logout");
			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				displayProducts();
				break;
			case 2:
				addItemToCart();
				break;
			case 3:
				viewCart();
				break;
			case 4:
				removeItemFromCart();
				break;
			case 5:
				placeOrder(customer);
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid option.");
				break;
			}

		}
	}

	private static void addProduct() {
		System.out.print("Enter product ID: ");
		int id = sc.nextInt();
		sc.nextLine(); // consume newline
		System.out.print("Enter product name: ");
		String name = sc.nextLine();
		System.out.print("Enter price: ");
		double price = sc.nextDouble();
		System.out.print("Enter discount percent: ");
		double discount = sc.nextDouble();

		Product p = new Product(id, name, price, discount);
		productManager.addProduct(p);
		System.out.println("Product added successfully.");
	}

	private static void removeProduct() {
		System.out.print("Enter product ID to remove: ");
		int id = sc.nextInt();
		if (productManager.removeProduct(id))
			System.out.println("Product removed.");
		else
			System.out.println("Product not found.");
	}

	private static void updateProduct() {
		System.out.print("Enter product ID to update: ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter new name: ");
		String name = sc.nextLine();
		System.out.print("Enter new price: ");
		double price = sc.nextDouble();
		System.out.print("Enter new discount: ");
		double discount = sc.nextDouble();

		if (productManager.updateProduct(id, name, price, discount))
			System.out.println("Product updated.");
		else
			System.out.println("Product not found.");
	}

	private static void displayProducts() {
		List<Product> products = productManager.getAllProducts();
		if (products.isEmpty()) {
			System.out.println("No products available.");
			return;
		}
		for (Product p : products) {
			System.out.println(p);
		}
	}

	private static void addItemToCart() {
		List<Product> products = productManager.getAllProducts();
		System.out.println("\n--- Available Products ---");
		for (Product p : products) {
			System.out.println(p);
		}
		System.out.print("Enter product ID to add to cart: ");
		int id = sc.nextInt();
		Product product = productManager.getProductById(id);
		if (product == null) {
			System.out.println("Product not found.");
			return;
		}
		System.out.print("Enter quantity: ");
		int qty = sc.nextInt();
		cart.add(new LineItem(cart.size() + 1, qty, product));
		System.out.println("Item added to cart.");
	}

	private static void viewCart() {
		if (cart.isEmpty()) {
			System.out.println("Cart is empty.");
			return;
		}
		for (LineItem item : cart) {
			System.out.println(item);
		}
	}

	private static void removeItemFromCart() {
		System.out.print("Enter LineItem ID to remove: ");
		int id = sc.nextInt();
		boolean removed = cart.removeIf(item -> item.getId() == id);
		if (removed)
			System.out.println("Item removed from cart.");
		else
			System.out.println("Item not found.");
	}

	private static void placeOrder(Customer customer) {
		if (cart.isEmpty()) {
			System.out.println("Cart is empty. Cannot place order.");
			return;
		}

		int orderId = new Random().nextInt(1000);
		Order order = new Order(orderId, new Date());
		for (LineItem item : cart) {
			order.addLineItem(item);
		}
		customer.addOrder(order);

		System.out.println("\n--- Invoice ---");
		System.out.println("Customer Name: " + customer.getName());
		System.out.println("Order ID: " + order.getId());
		System.out.println("Date: " + order.getDate());

		System.out.println("\nItems:");
		for (LineItem item : order.getItems()) {
		    String name = item.getProduct().getName();
		    int qty = item.getQuantity();
		    double price = item.calculateLineItemCost();
		    System.out.println("- " + name + " (Qty: " + qty + ") - ₹" + price);
		}

		System.out.println("\nTotal: ₹" + order.calculateOrderPrice());

		System.out.println("Thank you for shopping with us!");
		cart.clear();
	}

}