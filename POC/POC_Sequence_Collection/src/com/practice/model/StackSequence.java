package com.practice.model;

import java.util.*;

public class StackSequence {
    public static void main(String[] args) {
        Stack<String> books = new Stack<>();

        // Pushing elements onto the stack
        books.push("Java");
        books.push("Python");
        books.push("C++");

        // Peeking the top element
        System.out.println("Top book: " + books.peek()); // C++

        // Popping from stack (removes top)
        String removedBook = books.pop();
        System.out.println("Removed book: " + removedBook); // C++

        // Checking the current stack
        System.out.println("Books in stack: " + books);
    }
}
