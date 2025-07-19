package com.aurionpro.model;

public abstract class BeverageMaker {
	public void makeBeverage() {
		boilWater();
		addCondiments();
		brew();
		pourInCup();
	}

	void boilWater() {
		System.out.println("Boiling water");
	};

	abstract void addCondiments();

	abstract void brew();

	void pourInCup() {
		System.out.println("Pouring into cup");
	};

}
