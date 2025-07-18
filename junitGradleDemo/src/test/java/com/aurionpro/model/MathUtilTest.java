package com.aurionpro.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
@TestInstance(Lifecycle.PER_CLASS)

class MathUtilTest {
	
	MathUtil obj;	

	@BeforeAll
	void init() {
		System.out.println("JUnit Testing");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("Test completed..");
		System.out.println("---------------------------------");
	}
	
	@BeforeEach
	void beforeEach() {
		System.out.println("---------------------------------");
		System.out.println("Starting test..");
	}
	
	@AfterAll
	void afterAll() {
		System.out.println("End of JUnit Testing");
	}

	@Test
	void testAdd() {
		obj = new MathUtil();
		assertEquals(4, obj.add(2, 2));
	}
	@Test
	void testSubtract() {
		obj = new MathUtil();
		assertEquals(4, obj.subtract(6, 2));
	}
	@Test
	void testMultiply() {
		obj = new MathUtil();
		assertEquals(4, obj.multiply(2, 2));
	}
	@Test
	void testDivide() {
		obj = new MathUtil();
		assertEquals(4, obj.divide(8, 2));
	}
}
