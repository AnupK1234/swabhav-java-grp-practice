package com.aurionpro.model;

import java.util.StringTokenizer;

public class Tokenizer {
	public static void main(String[] args) {
		String sentence = "HEllo frnds!! cha pi loo!!";
		StringTokenizer st = new StringTokenizer(sentence);
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		System.out.println(st.hasMoreTokens());
		
	}
}
