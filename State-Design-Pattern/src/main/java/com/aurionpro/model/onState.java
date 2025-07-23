package com.aurionpro.model;

public class onState implements IFanState{

	@Override
	public void pressButton(Fan fan) {
		System.out.println("Fan is turned high state");
		fan.setState(new highState());		
	}

	

}
