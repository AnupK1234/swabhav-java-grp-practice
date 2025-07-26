package com.aurionpro.fooddelivery.service;

import com.aurionpro.fooddelivery.model.Order;
import com.aurionpro.fooddelivery.exception.InvalidUpiIdException;

import java.util.Scanner;

public class PaymentService {

	public void processPayment(Order order) {
		Scanner scanner = new Scanner(System.in);
		String upiId = null;
		boolean valid = false;

		System.out.println("=== Payment for order id: " + order.getId() + " ===");

		while (!valid) {
			try {
				System.out.print("Enter your UPI ID (format: something@something): ");
				upiId = scanner.nextLine();
				validateUpiId(upiId);
				valid = true;
			} catch (InvalidUpiIdException e) {
				System.out.println("Error: " + e.getMessage() + " Please try again.");
			}
		}

		System.out.println("UPI ID validated: " + upiId);

		try {
			animateStatus("Processing Payment");
			animateStatus("Validating Payment");
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Payment interrupted.");
			return;
		}

		System.out.println("\nPayment Success!\n");
	}

	private void validateUpiId(String upiId) throws InvalidUpiIdException {
		if (upiId == null || !upiId.matches("^[^@\\s]+@[^@\\s]+$")) {
			throw new InvalidUpiIdException("Invalid UPI ID format");
		}
	}

	private void animateStatus(String message) throws InterruptedException {
		System.out.print(message);
		for (int i = 0; i < 3; i++) {
			Thread.sleep(700);
			System.out.print(" .");
		}
		System.out.println();
	}
}
