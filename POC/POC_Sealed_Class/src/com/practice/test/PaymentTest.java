package com.practice.test;

import com.practice.model.CreditCard;
import com.practice.model.Payment;
import com.practice.model.UPI;

public class PaymentTest {
	 public static void main(String[] args) {
	        Payment payment1 = new CreditCard();
	        payment1.processPayment(); 

	        Payment payment2 = new UPI();
	        payment2.processPayment(); 
	    }
}
