package com.practice.model;

import java.io.*;
import java.util.*;

public class ProductManager {
    private List<Product> products;
    private final String fileName = "products.txt";

    public ProductManager() {
        this.products = loadProducts();
    }

    public void addProduct(Product product) {
        products.add(product);
        saveProducts();
    }

    public boolean removeProduct(int id) {
        boolean removed = products.removeIf(p -> p.getId() == id);
        if (removed) saveProducts();
        return removed;
    }

    public boolean updateProduct(int id, String name, double price, double discountPercent) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setName(name);
                p.setPrice(price);
                p.setDiscountPercent(discountPercent);
                saveProducts();
                return true;
            }
        }
        return false;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private void saveProducts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(products);
        } catch (IOException e) {
            System.out.println("Error saving products: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Product> loadProducts() {
        File file = new File(fileName);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading products: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
