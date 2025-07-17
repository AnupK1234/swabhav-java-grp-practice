package com.aurionpro.model;

public class StringUtil {
	String truncateAInFirst2Place(String str) {

		String first2Chars = str.length() > 2 ? str.substring(0, 2) : str;
		String rest = str.length() > 2 ? str.substring(2) : "";

		first2Chars = first2Chars.replace("A", "");

		return first2Chars + rest;
	}

	boolean firstAndLastTwoCharAreEqual(String str) {
		if (str.length() < 2)
			return false;

		if (str.charAt(0) == str.charAt(str.length() - 2) && str.charAt(1) == str.charAt(str.length() - 1))
			return true;

		return false;
	}
}
