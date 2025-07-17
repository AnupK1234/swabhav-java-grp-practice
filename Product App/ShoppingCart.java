package com.practice.service;

import com.practice.model.LineItem;
import com.practice.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ShoppingCart {
    private List<LineItem> items = new ArrayList<>();
    private static int idCounter = 0;

    public void addItem(Product product, int quantity) {
        // Check if item already exists in cart, if so, update quantity
        for (LineItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // Otherwise, add new line item
        items.add(new LineItem(++idCounter, quantity, product));
    }
    
    public boolean removeItem(int productId) {
        return items.removeIf(item -> item.getProduct().getId() == productId);
    }

    public List<LineItem> getItems() {
        return items;
    }
    
    public void clearCart() {
        items.clear();
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(LineItem::calculateLineItemCost).sum();
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
            return;
        }
        System.out.println("--- Your Shopping Cart ---");
        items.forEach(item -> System.out.printf("Product: %s, Quantity: %d, Item Total: ₹%.2f%n",
                item.getProduct().getName(), item.getQuantity(), item.calculateLineItemCost()));
        System.out.println("--------------------------");
        System.out.printf("Cart Total: ₹%.2f%n", calculateTotal());
    }
}