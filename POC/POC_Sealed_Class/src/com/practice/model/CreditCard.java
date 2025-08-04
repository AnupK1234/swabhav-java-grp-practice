package com.practice.model;

public final class CreditCard extends Payment {
    @Override
    public void processPayment() {
        System.out.println("Processing payment via Credit Card 💳");
    }
}
