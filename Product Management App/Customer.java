package com.practice.model;

import java.util.*;

public class Customer {
	private int id;
	private String name;
	private List<Order> orders;

	public Customer() {
		this.orders = new ArrayList<>();
	}

	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
		this.orders = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void addOrder(Order order) {
		this.orders.add(order);
	}
}