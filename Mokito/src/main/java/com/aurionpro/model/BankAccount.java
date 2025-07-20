package com.aurionpro.model;

public class BankAccount {
	private IBankService bankService;

	public BankAccount(IBankService bankService) {
		this.bankService = bankService;
	}
	
	public double calculateInterest() {
		return (bankService.getBalance()*bankService.getInterestRate())/100;
	}

}
