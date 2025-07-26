package com.aurionpro.fooddelivery.exception;

public class NegativePriceException extends RuntimeException {
	public NegativePriceException(String message) {
		super(message);
	}
}
