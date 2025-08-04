package com.practice.model;

// A sealed class or interface in Java restricts which other classes or interfaces may extend or implement it.
// This helps control the inheritance hierarchy, enhancing security, maintainability, and predictability of code.
public sealed class Payment permits CreditCard, UPI {
	public void processPayment() {
		System.out.println("Processing generic payment");
	}
}
