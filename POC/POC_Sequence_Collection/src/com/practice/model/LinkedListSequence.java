package com.practice.model;

import java.util.*;

public class LinkedListSequence {
	public static void main(String[] args) {
		// Use LinkedList reference to access LinkedList-specific methods
		LinkedList<String> animals = new LinkedList<>();

		// Adding elements in order
		animals.add("Dog");
		animals.add("Cat");
		animals.add("Horse");

		animals.add(1, "Elephant");

		animals.addFirst("Tiger");
		animals.addLast("Zebra");

		System.out.println("Animal at index 2: " + animals.get(2));
		System.out.println("First animal: " + animals.getFirst());
		System.out.println("Last animal: " + animals.getLast());

		System.out.println("All animals:");
		for (String animal : animals) {
			System.out.println(animal);
		}

		animals.remove("Cat");
		animals.removeFirst();
		animals.removeLast();

		System.out.println("After removal: " + animals);
	}
}
