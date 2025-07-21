package com.aurionpro.TicTacToe;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.aurionpro.exception.CellAlreadyMarkedException;
import com.aurionpro.exception.InvalidCellException;
import com.aurionpro.model.Board;
import com.aurionpro.model.MarkType;
import com.aurionpro.model.ResultAnalyzer;

public class ResultAnalyzeTest {

	private Board board;
	private ResultAnalyzer resultAnalyze;

	@BeforeEach
	void setUp() {
		board = new Board();
		resultAnalyze = new ResultAnalyzer(board);
	}

	@Test
	void testHorizontalWinCheck_firstRowWin() throws CellAlreadyMarkedException, InvalidCellException {
		board.setCellMark(0, 0, MarkType.X);
		board.setCellMark(0, 1, MarkType.X);
		board.setCellMark(0, 2, MarkType.X);

		assertTrue(resultAnalyze.checkHorizontal());

	}

	@Test
	void testVerticalWinCheck_firstColWin() throws CellAlreadyMarkedException, InvalidCellException {
		board.setCellMark(0, 0, MarkType.X);
		board.setCellMark(1, 0, MarkType.X);
		board.setCellMark(2, 0, MarkType.X);

		assertTrue(resultAnalyze.checkVertical());

	}

	@Test
	void testDiagonalWinCheck_firstDiagonalWin() throws CellAlreadyMarkedException, InvalidCellException {
		board.setCellMark(0, 0, MarkType.X);
		board.setCellMark(1, 1, MarkType.X);
		board.setCellMark(2, 2, MarkType.X);

		assertTrue(resultAnalyze.checkDiagonal());

	}
}
