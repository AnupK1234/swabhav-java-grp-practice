package com.aurionpro;

// here we only give permission to car and bike
sealed interface Vehicle permits Car, Bike {
}

final class Car implements Vehicle {
}

final class Bike implements Vehicle {
}

public class Main {
	public static void main(String[] args) {
		Vehicle v = new Car();
	
		if (v instanceof Car c) {
			System.out.println("Car");
		} else if (v instanceof Bike b) {
			System.out.println("Bike");
		}
	}
}
