package com.practice.service;

import com.practice.model.Customer;
import com.practice.model.LineItem;
import com.practice.model.Order;

import java.text.SimpleDateFormat;

public class InvoiceGenerator {

    public static String generateInvoice(Customer customer, Order order) {
//    	 StringBuilder sb = new StringBuilder();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        String invoice = "Invoice\n";
        invoice += "--------\n";
        invoice += "Customer Name: " + customer.getName() + "\n";
        invoice += "Order ID: " + order.getId() + "\n";
        invoice += "Date: " + sdf.format(order.getDate()) + "\n\n";
        invoice += "Items:\n";

        for (LineItem item : order.getItems()) {
            invoice += "- " + item.getProduct().getName() +
                       " (Qty: " + item.getQuantity() + ")" +
                       " - ₹" + String.format("%.2f", item.calculateLineItemCost()) + "\n";
        }

        invoice += "\nTotal: ₹" + String.format("%.2f", order.calculateOrderPrice()) + "\n";
        invoice += "Thank you for shopping with us!";

        return invoice;
    }
}
