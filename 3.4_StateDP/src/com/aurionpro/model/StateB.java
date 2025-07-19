package com.aurionpro.model;

public class StateB implements IState {

	@Override
	public void handle() {
		System.out.println("Request handle by state B");
	}

}
