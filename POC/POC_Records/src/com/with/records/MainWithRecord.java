package com.with.records;

public class MainWithRecord {
    public static void main(String[] args) {
        ProductRecord product = new ProductRecord("Laptop", 75000.00);
        System.out.println(product.name());
        System.out.println(product.price());
        System.out.println(product);
    }
}
