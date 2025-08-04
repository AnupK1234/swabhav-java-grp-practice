package com.practice.model;

public final class UPI extends Payment {
    @Override
    public void processPayment() {
        System.out.println("Processing payment via UPI 📱");
    }
}
