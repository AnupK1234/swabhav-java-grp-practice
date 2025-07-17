package com.aurionpro.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StringUtilTest {

	@Test
	void testCharacterEqual() {
		StringUtil obj = new StringUtil();

		boolean expected = false;
		assertEquals(expected, obj.firstAndLastTwoCharAreEqual("ABBBA"));
	}

	@Test
	void testTruncate() {
		StringUtil obj = new StringUtil();

		String expected = "ikb";
		assertEquals(expected, obj.truncateAInFirst2Place("AA"));
	}

}
