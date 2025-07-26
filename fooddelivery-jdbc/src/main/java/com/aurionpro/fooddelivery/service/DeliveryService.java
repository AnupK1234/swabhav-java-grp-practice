package com.aurionpro.fooddelivery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.aurionpro.fooddelivery.model.DeliveryPartner;
import struqt.util.UniqueIdGenerator;

public class DeliveryService {
	private static final List<DeliveryPartner> partners = new ArrayList<>();
	private transient static final UniqueIdGenerator generator = new UniqueIdGenerator(100);

	public static void addPartner(String name, String email, long phone) {
		DeliveryPartner partner = new DeliveryPartner(generator.next(), name, email, phone, null, true);
		partners.add(partner);
		System.out.println("Delivery partner added.");
	}

	public static void viewPartners() {
		if (partners.isEmpty()) {
			System.out.println("No delivery partners available.");
			return;
		}
		System.out.println("\n--- Registered Delivery Partners ---");
		for (DeliveryPartner p : partners) {
			System.out.println("ID: " + p.getId() + ", Name: " + p.getName() + ", Available: " + p.isAvailable());
		}
	}

	public static DeliveryPartner assignPartner() {
		Optional<DeliveryPartner> availablePartner = partners.stream().filter(p -> p.isAvailable()).findAny();
		if (availablePartner.isPresent()) {
			DeliveryPartner assigned = availablePartner.get();
			assigned.setAvailability(false); // mark as occupied
			System.out.println("Delivery Partner Assigned: " + assigned.getName());
			return assigned;
		} else {
			System.out.println("No available delivery partner at the moment. Order will be queued.");
			return null;
		}
	}

	public static void releasePartner(DeliveryPartner partner) {
		partner.setAvailability(false);
	}

	public static void adminMenu(Scanner sc) {
		int choice;
		do {
			System.out.println("\n--- Delivery Partner Management ---");
			System.out.println("1. Add Delivery Partner");
			System.out.println("2. View All Partners");
			System.out.println("0. Back to Admin Menu");
			System.out.print("Enter choice: ");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1 -> {
				System.out.print("Enter name: ");
				String name = sc.nextLine();
				System.out.print("Enter email: ");
				String email = sc.nextLine();
				System.out.print("Enter phone: ");
				long phone = sc.nextLong();
				addPartner(name, email, phone);
			}
			case 2 -> viewPartners();
			case 0 -> System.out.println("Returning to Admin Menu...");
			default -> System.out.println("Invalid choice");
			}
		} while (choice != 0);
	}
}
