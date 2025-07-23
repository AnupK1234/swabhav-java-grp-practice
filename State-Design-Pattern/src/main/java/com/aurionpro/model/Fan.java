package com.aurionpro.model;

public class Fan {
	private IFanState state;
	
	public Fan() {
		state = new offState(); // starting off state
	}

	public void setState(IFanState state) {
		this.state =  state;
	}

	public void pressButton() {
        state.pressButton(this);
    }
	@Override
	public String toString() {
		return "Fan [state=" + state + "]";
	}
	

}
