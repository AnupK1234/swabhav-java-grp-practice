package com.aurionpro.model;

public class offState implements IFanState{

	@Override
	public void pressButton(Fan fan) {

		System.out.println("Fan is turned on state");
		fan.setState(new onState());		
	}

	

}
