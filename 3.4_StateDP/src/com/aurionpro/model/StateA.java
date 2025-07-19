package com.aurionpro.model;

public class StateA implements IState{

	@Override
	public void handle() {
		System.out.println("Request handled by state A");		
	}

}
