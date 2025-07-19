package com.aurionpro.model;

public class TeaMaker extends BeverageMaker {

	@Override
	void addCondiments() {
		System.out.println("Adding tea powder");

	}

	@Override
	void brew() {
		System.out.println("Brewing the tea");

	}

}
