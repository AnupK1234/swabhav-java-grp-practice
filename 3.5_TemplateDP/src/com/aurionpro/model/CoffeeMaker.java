package com.aurionpro.model;

public class CoffeeMaker extends BeverageMaker{

	@Override
	void addCondiments() {
		System.out.println("Adding coffee powder");
		
	}

	@Override
	void brew() {
		System.out.println("Brewing a coffee");
		
	}

}
