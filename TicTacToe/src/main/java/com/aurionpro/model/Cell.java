package com.aurionpro.model;

import com.aurionpro.exception.CellAlreadyMarkedException;

public class Cell {
	MarkType mark = MarkType.EMPTY;

	public boolean isEmpty() {
		if (this.mark == MarkType.EMPTY)
			return true;
		return false;
	}

	public MarkType getMark() {
		return this.mark;
	}

	public void setMark(MarkType mark) throws CellAlreadyMarkedException {
		if (this.mark != MarkType.EMPTY)
			throw new CellAlreadyMarkedException("Cell is already marked");

		this.mark = mark;
	}
}
