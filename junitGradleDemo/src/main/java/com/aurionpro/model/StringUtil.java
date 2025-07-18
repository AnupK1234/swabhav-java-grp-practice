package com.aurionpro.model;

public class StringUtil {

    public String truncateAInFirst2Place(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        if (str.length() == 1) {
            return str.charAt(0) == 'A' ? "" : str;
        }

        String firstCharResult = (str.charAt(0) == 'A') ? "" : String.valueOf(str.charAt(0));
        String secondCharResult = (str.charAt(1) == 'A') ? "" : String.valueOf(str.charAt(1));
        String restOfString = str.substring(2);

        return firstCharResult + secondCharResult + restOfString;
    }

    public boolean firstAndLastTwoCharacterAreEqual(String str) {

        if (str == null || str.length() < 2) {
            return false;
        }

        String first2Chars = str.substring(0, 2);

        String last2Chars = str.substring(str.length() - 2);

        return first2Chars.equals(last2Chars);    }
}
