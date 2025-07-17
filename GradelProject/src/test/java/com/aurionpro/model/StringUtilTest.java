package com.aurionpro.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilTest {
	StringUtil util = new StringUtil();

	@Test
	void testTruncateAInFirst2Places_case1() {
		assertEquals("BCD", util.truncateAInFirst2Places("ABCD"));
	}

	@Test
	void testTruncateAInFirst2Places_case2() {
		assertEquals("BC", util.truncateAInFirst2Places("AABC"));
	}

	@Test
	void testTruncateAInFirst2Places_case3() {
		assertEquals("BC", util.truncateAInFirst2Places("ABC"));
	}

	@Test
	void testTruncateAInFirst2Places_case4() {
		assertEquals("BLODA", util.truncateAInFirst2Places("BLODA"));
	}

	@Test
	void testFirstAndLastTwoCharAreEqual_case1() {
		assertTrue(util.firstAndLastTwoCharAreEqual("ABBBAB"));
	}

	@Test
	void testFirstAndLastTwoCharAreEqual_case2() {
		assertTrue(util.firstAndLastTwoCharAreEqual("ABAB"));
	}

	@Test
	void testFirstAndLastTwoCharAreEqual_case3() {
		assertTrue(util.firstAndLastTwoCharAreEqual("ABCDAB"));
	}

}
