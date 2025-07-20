package com.aurionpro.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

//Mockito.when(...) -> You're defining what the mock should return
//.thenReturn(value) -> You’re providing the fake/mock value the method will return
class BankAccountTest {

	private BankAccount account;
	private IBankService bankService;

	@BeforeEach
	void init() {
		bankService = Mockito.mock(IBankService.class);
		account = new BankAccount(bankService);
	}

	@AfterEach
	void done() {
		System.out.println("Test finished.");
	}

	@Test
	void testCalculateInterest() {
		Mockito.when(bankService.getBalance()).thenReturn(10000.0);
		Mockito.when(bankService.getInterestRate()).thenReturn(5.0);
		
		assertEquals( 500.0, account.calculateInterest());  

	}

}