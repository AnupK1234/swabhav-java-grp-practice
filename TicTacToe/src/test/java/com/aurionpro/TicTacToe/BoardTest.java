package com.aurionpro.TicTacToe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.aurionpro.exception.CellAlreadyMarkedException;
import com.aurionpro.exception.InvalidCellException;
import com.aurionpro.model.Board;
import com.aurionpro.model.MarkType;

public class BoardTest {

	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board();
	}

	@Test
	void testIsBoardFull_ShouldReturnTrueWhenAllCellsAreFilled()
			throws CellAlreadyMarkedException, InvalidCellException {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board.setCellMark(i, j, MarkType.X);
			}
		}
		assertTrue(board.isBoardFull(), "Board should be full after filling all cells");
	}

	@Test
	void testIsBoardFull_ShouldReturnFalseWhenSomeCellsAreEmpty()
			throws CellAlreadyMarkedException, InvalidCellException {
		board.setCellMark(0, 0, MarkType.X);
		board.setCellMark(1, 1, MarkType.O);
		assertFalse(board.isBoardFull(), "Board should not be full when some cells are unmarked");
	}

	@Test
	void testGetCellMark_ShouldReturnCorrectMark() throws CellAlreadyMarkedException, InvalidCellException {
		board.setCellMark(2, 1, MarkType.O);
		assertEquals(MarkType.O, board.getCellMark(2, 1));
	}

	@Test
	void testSetCellMark_ShouldThrowExceptionForInvalidLocation() {
		assertThrows(InvalidCellException.class, () -> {
			board.setCellMark(3, 0, MarkType.X); // invalid row
		});

		assertThrows(InvalidCellException.class, () -> {
			board.setCellMark(1, -1, MarkType.O); // invalid column
		});
	}

	@Test
	void testSetCellMark_ShouldThrowExceptionWhenCellIsAlreadyMarked()
			throws InvalidCellException, CellAlreadyMarkedException {
		board.setCellMark(1, 1, MarkType.X);
		assertThrows(CellAlreadyMarkedException.class, () -> {
			board.setCellMark(1, 1, MarkType.O); // same cell
		});
	}

	@Test
	void testGetCellMark_ShouldReturnEmptyByDefault() {
		assertEquals(MarkType.EMPTY, board.getCellMark(2, 2));
	}
}
