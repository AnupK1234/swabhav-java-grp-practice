package com.aurionpro.model;

public class Player {
	private int id;
	private String name;
	private MarkType mark;

	public Player(int id, String name, MarkType mark) {
		this.id = id;
		this.name = name;
		this.mark = mark;
	}

	public String getName() {
		return name;
	}

	public MarkType getMark() {
		return mark;
	}
}
