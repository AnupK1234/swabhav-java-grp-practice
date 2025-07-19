package com.aurionpro.TicTacToe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.aurionpro.exception.CellAlreadyMarkedException;
import com.aurionpro.model.Cell;
import com.aurionpro.model.MarkType;

public class CellTest {

	private Cell cell;

	@BeforeEach
	void setUp() {
		cell = new Cell();
	}

	@Test
	void testIsEmptyInitially() {
		assertTrue(cell.isEmpty(), "Cell should be empty initially");
		assertEquals(MarkType.EMPTY, cell.getMark(), "Initial mark should be EMPTY");
	}

	@Test
	void testSetMarkSuccess() throws CellAlreadyMarkedException {
		cell.setMark(MarkType.X);
		assertEquals(MarkType.X, cell.getMark());
		assertFalse(cell.isEmpty(), "Cell should not be empty after marking");
	}

	@Test
	void testSetMarkThrowsExceptionIfAlreadyMarked() throws CellAlreadyMarkedException {

		cell.setMark(MarkType.O);
		Exception exception = assertThrows(CellAlreadyMarkedException.class, () -> {
			cell.setMark(MarkType.X); // Should throw
		});
		assertEquals("Cell is already marked", exception.getMessage());
	}
}
