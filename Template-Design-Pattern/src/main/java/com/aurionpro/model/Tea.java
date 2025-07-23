package com.aurionpro.model;

public class Tea extends Beverage {

	@Override
	protected void addCondiments() {
		System.out.println("Add lemon and ginger");

	}

	@Override
	protected void brew() {
		System.out.println("Brew Tea leaves in boiling water");

	}

}
