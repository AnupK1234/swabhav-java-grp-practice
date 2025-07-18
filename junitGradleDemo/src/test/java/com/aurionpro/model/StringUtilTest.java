package com.aurionpro.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilTest {
	StringUtil obj;

	@Test
	void truncateAInFirst2Place() {
		obj = new StringUtil();
		assertEquals("BCD", obj.truncateAInFirst2Place("ABCD"));
		assertEquals("BC", obj.truncateAInFirst2Place("AABC"));
		assertEquals("BC", obj.truncateAInFirst2Place("ABC"));
		assertEquals("BCDA", obj.truncateAInFirst2Place("BCDA"));
		assertEquals("", obj.truncateAInFirst2Place("AA"));
		assertEquals("", obj.truncateAInFirst2Place("A"));
	}
	
	@Test
	void firstAndLastTwoCharacterAreEqual(){
		obj = new StringUtil();
		assertFalse(obj.firstAndLastTwoCharacterAreEqual("ABBBA"));
		assertTrue(obj.firstAndLastTwoCharacterAreEqual("ABAB"));
		assertTrue(obj.firstAndLastTwoCharacterAreEqual("ABCDAB"));
		assertFalse(obj.firstAndLastTwoCharacterAreEqual("ABA"));
		assertTrue(obj.firstAndLastTwoCharacterAreEqual("AA"));
		assertTrue(obj.firstAndLastTwoCharacterAreEqual("AB"));
		assertFalse(obj.firstAndLastTwoCharacterAreEqual("A"));
		assertFalse(obj.firstAndLastTwoCharacterAreEqual(""));
	}

}
