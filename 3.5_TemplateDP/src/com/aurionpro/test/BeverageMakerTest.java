package com.aurionpro.test;

import com.aurionpro.model.BeverageMaker;
import com.aurionpro.model.CoffeeMaker;
import com.aurionpro.model.TeaMaker;

public class BeverageMakerTest {
	public static void main(String[] args) {
		System.out.println("Making a tea: ");
		BeverageMaker teaMaker = new TeaMaker();
		teaMaker.makeBeverage();

		System.out.println();
		
		System.out.println("Making a coffee: ");
		BeverageMaker coffeeMaker = new CoffeeMaker();
		coffeeMaker.makeBeverage();

	}
}
