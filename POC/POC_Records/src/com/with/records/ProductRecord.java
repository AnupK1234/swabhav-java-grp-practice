package com.with.records;

//A record in Java is a special type of class introduced in Java 14 (preview) and officially in Java 16
// designed to store immutable data in a compact and readable way.
// It automatically generates a constructor, getters, equals(), hashCode(), and toString() methods based on its fields.
//  1. Concise Syntax, 2. Immutable by Default, 
public record ProductRecord(String name, double price) {
}
