package com.aurionpro.fooddelivery.model;

import struqt.util.UniqueIdGenerator;

public class OrderItem {
	private long id;
	private int quantity;
	private MenuItem item;
	private transient UniqueIdGenerator generator = new UniqueIdGenerator(1L);

	public OrderItem(MenuItem item, int quantity) {
		this.id = generator.next();
		this.item = item;
		this.quantity = quantity;
	}

	public MenuItem getItem() {
		return item;
	}

	public void setItem(MenuItem item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
