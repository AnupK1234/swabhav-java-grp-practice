package com.without.record;

public class MainWithoutRecord {
    public static void main(String[] args) {
        Product product = new Product("Laptop", 75000.00);
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product);
    }
}