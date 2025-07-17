package com.practice.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {

    public static final String PRODUCTS_FILE = "products.txt";
    public static final String CUSTOMERS_FILE = "customers.txt";
    public static final String ORDERS_FILE = "orders.txt";

   
    public void saveData(List<?> data, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.err.println("Error saving data to " + filename + ": " + e.getMessage());
            e.printStackTrace();
        }
    }	

   
    public List<?> loadData(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        // Prevent EOFException on empty files
        if (file.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                return (List<?>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from " + filename + ". A new list will be used. Error: " + e.getMessage());
        }
        
        // Return an empty list in case of any errors.
        return new ArrayList<>();
    }
}