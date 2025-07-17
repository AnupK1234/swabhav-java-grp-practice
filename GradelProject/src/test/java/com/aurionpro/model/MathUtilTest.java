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
	class MathUtilTest {

	    MathUtil obj;

	    @BeforeAll
	    void beforeAll() {
	        System.out.println("Before All");
	        obj = new MathUtil(); 
	    }

	    @BeforeEach
	    void init() {
	        System.out.println("Before Each");
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
	        assertTrue(obj.add(10, 20) == 30);
	    }

	    @Test
	    void testMul() {
	        assertTrue(obj.mul(10, 20) == 200);
	    }

	    @Test
	    void testDiv() {
	        assertEquals(2, obj.div(10, 5));
	    }

	    @Test
	    void testDiv_ArithmeticException() {
	        assertThrows(ArithmeticException.class, () -> obj.div(5, 0));
	    }

	    @Test
	    void testArray() {
	        int[] arr = { 10, 40, 30, 2, 1 };
	        int[] expected = { 1, 2, 10, 30, 40 };
	        assertArrayEquals(expected, obj.sortArray(arr));
	    }

	    @Test
	    void testSub() {
	        int expect = 5;
	        assertEquals(expect, obj.sub(15, 10));
	    }
	}
