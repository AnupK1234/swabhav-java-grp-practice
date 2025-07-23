package com.aurionpro.model;

import java.util.Optional;

public class Array {
//	String name = null;
//	if (name != null) {
//	    System.out.println(name.length());
//	}
	public void optionalmethod() {
		Optional<String> opt = Optional.of("Hello");
		if (opt.isPresent()) {
			System.out.println(opt.get());
		}
	}
	public static Optional<Double> add(Integer a, Integer b) {
		if (a == null || b == null)
			return Optional.empty(); // no result if any value missing

		return Optional.of((double) (a + b));
	}

	public static void main(String[] args) {
		add(10, 20).ifPresent(System.out::println); // 30.0
		System.out.println(add(null, 5).orElse(0.0)); // 0.0

	}

}
