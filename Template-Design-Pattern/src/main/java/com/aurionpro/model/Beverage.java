package com.aurionpro.model;

public abstract class Beverage {

	public final void prepareBeverage() {
		
		boilWater();
		brew();
		pourInCup();
		addCondiments();

	}
	
	private void boilWater() {
		System.out.println("Boil Water");
		
	}
	protected abstract void brew();
	
	private void pourInCup() {
		System.out.println("Pouring into cup");
	}
	protected abstract void addCondiments();
	

}
