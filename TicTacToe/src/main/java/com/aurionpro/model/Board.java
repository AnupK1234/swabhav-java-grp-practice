package com.aurionpro.model;

import com.aurionpro.exception.CellAlreadyMarkedException;
import com.aurionpro.exception.InvalidCellException;

public class Board {
	Cell[][] cell = new Cell[3][3];

	public Board() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cell[i][j] = new Cell();
			}
		}
	}

	public boolean isBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (cell[i][j].getMark() == MarkType.EMPTY)
					return false;
			}
		}
		return true;
	}

	public void setCellMark(int locX, int locY, MarkType mark) throws InvalidCellException, CellAlreadyMarkedException {
		if (locX > 2 || locX < 0 || locY > 2 || locY < 0)
			throw new InvalidCellException("Invalid Cell Exception");

		this.cell[locX][locY].setMark(mark);
	}

	public MarkType getCellMark(int locX, int locY) {
		return this.cell[locX][locY].getMark();
	}

	public void displayBoard() {
		System.out.println("\nCurrent Board:");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				MarkType mark = cell[i][j].getMark();
				switch (mark) {
				case X:
					System.out.print(" X ");
					break;
				case O:
					System.out.print(" O ");
					break;
				default:
					System.out.print(" - ");
				}
				if (j < 2)
					System.out.print("|");
			}
			System.out.println();
			if (i < 2)
				System.out.println("-----------");
		}
		System.out.println();
	}

}
