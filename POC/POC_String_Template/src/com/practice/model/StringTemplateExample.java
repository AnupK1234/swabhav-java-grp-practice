package com.practice.model;
// %s->	Insert a string
// %d-> Insert a decimal integer
// String Templates allow embedding expressions directly into strings using a STR processor and ${} syntax.


public class StringTemplateExample {
    public static void main(String[] args) {
        String name = "Vivek";
        int age = 22;
        String email = "vivek@example.com";

        String message = String.format("""
                 Hello %s,

                Welcome to our platform! Here are your details:
                - Age  : %d
                - Email: %s

                Thanks for registering! 😊
                """, name, age, email);

        System.out.println(message);
    }
}

