package com.aurionpro.fooddelivery.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private List<MenuItem> items;

	public Menu() {
		this.items = new ArrayList<MenuItem>();
	}

	public void addItem(MenuItem item) {
		this.items.add(item);
	}

	public MenuItem getItemById(int id) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getId() == id)
				return items.get(i);
		}

		return null;
	}

	public List<MenuItem> getItems() {
		return this.items;
	}

}
