package com.aurionpro.test;

import com.aurionpro.model.Context;
import com.aurionpro.model.StateA;
import com.aurionpro.model.StateB;

public class ContextTest {
	public static void main(String[] args) {
		Context context = new Context();

		context.setState(new StateA());
		context.request();

		context.setState(new StateB());
		context.request();

	}
}
