package com.aurionpro.fooddelivery.model;

import com.aurionpro.fooddelivery.enums.UserType;

public class DeliveryPartner extends User {
	private boolean availabilityStatus = true;

	public DeliveryPartner(long id, String name, String email, long phone, UserType userType,
			boolean availabilityStatus) {
		super(id, name, email, phone, UserType.DELIVERYAGENT);
		this.availabilityStatus = availabilityStatus;
	}

	public boolean isAvailable() {
		return availabilityStatus;
	}

	public void setAvailability(boolean availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

}
