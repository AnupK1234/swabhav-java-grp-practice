package com.practice.model;

public class Fan {
	private FanState currentState;
	public Fan() {
		this.currentState=new OffState();
	}
	public void setState(FanState state) {
		this.currentState=state;
	}
	public void request() {
		currentState.handleRequest(this);
	}
}
