package com.practice.test;

import com.practice.model.Fan;
//In the above example, we create an instance of the Fan class.
//We call the request() method multiple times, which triggers state transitions based on the current state.
//The fan changes its behavior accordingly, and the corresponding messages are printed to the console.

//Clean and structured code, Simplified state transitions, Improved flexibility
public class FanTest {
	public static void main(String[] args) {
		Fan fan = new Fan();
		fan.request(); // Turns on low
		fan.request(); // Turns on medium
		fan.request(); // Turns on high
		fan.request(); // Turns off
	}
}
