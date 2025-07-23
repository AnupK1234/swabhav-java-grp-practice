package com.aurionpro.test;

import com.aurionpro.com.GenericMehtod;

public class Test {

	public static void main(String[] args) {
		GenericMehtod<Integer> a = new GenericMehtod<Integer>();
		a.add(100, 8);
		GenericMehtod<Double> d = new GenericMehtod<Double>();
		d.add(99.99, 0.01);

	}

}
