package com.practice.model;

import java.util.ArrayList;
import java.util.List;

// A Sequence Collection in Java is a type of collection that stores elements in a specific order (sequence) and allows:
// Duplicate elements
// Insertion and removal at specific positions
// Access by index
public class ArrayLists {
	 public static void main(String[] args) {
	        // Creating a sequence collection using ArrayList
	        List<String> fruits = new ArrayList<>();

	        // Adding elements in sequence
	        fruits.add("Apple");
	        fruits.add("Banana");
	        fruits.add("Mango");
	        fruits.add("Banana"); // Duplicate allowed

	        // Accessing elements by index
	        System.out.println("First fruit: " + fruits.get(0)); // Apple

	        // Iterating through the sequence
	        System.out.println("All fruits:");
	        for (String fruit : fruits) {
	            System.out.println(fruit);
	        }

	        // Removing by index
	        fruits.remove(2); 
	        
	        System.out.println("After removal:");
	        System.out.println(fruits);
	    }
}
