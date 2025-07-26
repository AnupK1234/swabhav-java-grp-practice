package com.aurionpro.fooddelivery.service;

import com.aurionpro.fooddelivery.model.Order;
import com.aurionpro.fooddelivery.model.OrderItem;
import com.aurionpro.fooddelivery.model.MenuItem;

import java.time.format.DateTimeFormatter;

public class InvoicePrinter {

	public static void printInvoice(Order order) {
		System.out.println("========== INVOICE ==========");
		System.out.println("Order ID: " + order.getId());
		String formattedDate = order.getTimestamp().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		System.out.println("Date: " + formattedDate);
		System.out.println("-----------------------------");
		System.out.printf("%-20s %5s %10s %12s\n", "Item", "Qty", "Price", "Subtotal");
		System.out.println("---------------------------------------------");

		for (OrderItem orderItem : order.getItems()) {
			MenuItem item = orderItem.getItem();
			int qty = orderItem.getQuantity();
			double price = item.getPrice();
			double subtotal = price * qty;

			System.out.printf("%-20s %5d %10.2f %12.2f\n", item.getName(), qty, price, subtotal);
		}

		System.out.println("---------------------------------------------");
		System.out.printf("%-30s : %10.2f\n", "Total Amount", order.getTotalAmount());
		System.out.printf("%-30s : %10.2f\n", "Discount", order.getDiscout());
		System.out.printf("%-30s : %10.2f\n", "Final Amount", order.getFinalAmt());
		System.out.println("Payment Mode: " + order.getPaymentMode());
		System.out.println("========== THANK YOU ==========");
	}
}
