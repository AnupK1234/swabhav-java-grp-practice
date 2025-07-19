package com.practice.model;

public class LowState implements FanState {
    @Override
    public void handleRequest(Fan fan) {
        System.out.println("Fan turned on medium.");
        fan.setState(new MediumState());
    }
}