package com.aurionpro.model;

public class Context {
	private IState state;

	public void setState(IState state) {
		this.state = state;
	}
	
	public void request() {
		if(state!=null) {
			state.handle();
		} else {
			System.out.println("State is not set");
		}
	}

}
