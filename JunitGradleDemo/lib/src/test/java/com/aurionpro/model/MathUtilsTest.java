package com.aurionpro.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class MathUtilsTest {

	@BeforeEach
	void beforeEach() {
		System.out.println("Before Each");
	}

	@BeforeAll
	void beforeAll() {
		System.out.println("Before All");
	}

	@AfterEach
	void afterEach() {
		System.out.println("After Each");
	}

	@AfterAll
	void afterAll() {
		System.out.println("After All");
	}

	@Test
	void testAdd() {
		MathUtils mu = new MathUtils();
		int expected = 4;
		assertEquals(expected, mu.add(2, 2));
	}

	@Test
	void testMultiply() {
		MathUtils mu = new MathUtils();
		int expected = 30;
		assertEquals(expected, mu.multiply(5, 6));
	}

	@Test
	void testDivide() {
		MathUtils mu = new MathUtils();
		int expected = 30;
		assertEquals(expected, mu.divide(90, 3));
	}

}
