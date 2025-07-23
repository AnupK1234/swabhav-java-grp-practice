package com.aurionpro.model;

public class StringUtil {

	public String truncateAInFirstTwoPlace(String str) {

		String newString = str;

		if (str.length() == 0) {
			return str;
		}
		if (str.charAt(0) == 'A') {
			newString = newString.substring(1);
		}
		String secondString = newString;
		if (newString.charAt(0) == 'A') {
			secondString = newString.substring(1);
			return secondString;
		}

		return newString;
	}
}
