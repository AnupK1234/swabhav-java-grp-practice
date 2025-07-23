package com.aurionpro.model;

public class highState implements IFanState {

	@Override
	public void pressButton(Fan fan) {

		System.out.println("Fan is turned off");
		fan.setState(new offState());
	}



}
