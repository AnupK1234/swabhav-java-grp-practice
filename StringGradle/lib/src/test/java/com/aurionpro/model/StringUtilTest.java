package com.aurionpro.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilTest {

	@Test
	void Check() {
		String str = "AABB";
		StringUtil s = new StringUtil();
		String expected = "BB";
		assertEquals(expected, s.truncateAInFirstTwoPlace(str));
		System.out.println(str);
	}

}
