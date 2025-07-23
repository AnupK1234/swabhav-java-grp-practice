package com.aurionpro.model;

public class MathService {
	private Calculator calculator;

	public MathService(Calculator calculator) {
		this.calculator = calculator;
	}

	public int doComplexMath(int x, int y) {
		return calculator.add(x, y) * calculator.multiply(x, y);
	}
}
