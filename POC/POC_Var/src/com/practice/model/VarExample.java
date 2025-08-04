package com.practice.model;

import java.util.List;

// In Java, the var keyword allows you to declare local variables without explicitly specifying their type.
// The compiler infers the type from the right-hand side of the assignment.
// var can only be used for local variables, index variables in loops, and lambda parameters.
public class VarExample {
	public static void main(String[] args) {
        var name = "Vivek";         // Inferred as String
        var age = 22;               // Inferred as int
        var price = 199.99;         // Inferred as double
        var isStudent = true;       // Inferred as boolean
        
        
//         var x; 
//         var y = null; 
        
        
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Price: " + price);
        System.out.println("Student: " + isStudent);

        var list = java.util.List.of("Apple", "Banana", 1);
        for (var fruit : list) {
            System.out.println("Fruit: " + fruit);
        }
        
        var names = List.of("Vivek", "Raj", "Sid");
        // Using var in lambda parameter
        names.forEach((var namees) -> {
            System.out.println("Hello, " + namees);
        });
        
        names.forEach((final var namees) -> {
            System.out.println("Final Name: " + namees);
        });
    }
}
