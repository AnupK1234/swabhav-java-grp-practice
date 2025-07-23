package com.aurionpro.model;

public class Coffee extends Beverage{

	@Override
	protected void brew() {
		System.out.println("Brew grinded coffee bean in boiling water");
		
	}

	@Override
	protected void addCondiments() {
		System.out.println("Add Sugar and Milk");
		System.out.println();
	}

}
