package com.aurionpro.model;

public class StringUtil {
	public String truncateAInFirst2Places(String str) {
        if (str.length() <= 2) {
            return str.replaceAll("A", "");
        }

        String first2 = str.substring(0, 2).replaceAll("A", "");
        String remaining = str.substring(2);

        return first2 + remaining;
    }
	 public boolean firstAndLastTwoCharAreEqual(String str) {
	        if (str.length() < 2) {
	            return false;
	        }

	        String f2 = str.substring(0, 2);
	        String l2 = str.substring(str.length() - 2);

	        return f2.equals(l2);
	    }
}
