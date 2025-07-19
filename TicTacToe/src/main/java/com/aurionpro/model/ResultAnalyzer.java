package com.aurionpro.model;

public class ResultAnalyzer {
	Board board;
	ResultType result = ResultType.PROGRESS;

	public ResultAnalyzer(Board board) {
		this.board = board;
	}

	private boolean checkHorizontal() {
		for (int row = 0; row < 3; row++) {
			MarkType m1 = board.getCellMark(row, 0);
			MarkType m2 = board.getCellMark(row, 1);
			MarkType m3 = board.getCellMark(row, 2);

			if (m1 != MarkType.EMPTY && m1 == m2 && m2 == m3)
				return true;
		}
		return false;
	}

	private boolean checkVertical() {
		for (int col = 0; col < 3; col++) {
			MarkType m1 = board.getCellMark(0, col);
			MarkType m2 = board.getCellMark(1, col);
			MarkType m3 = board.getCellMark(2, col);

			if (m1 != MarkType.EMPTY && m1 == m2 && m2 == m3)
				return true;
		}
		return false;
	}

	private boolean checkDiagonal() {
		MarkType c00 = board.getCellMark(0, 0);
		MarkType c11 = board.getCellMark(1, 1);
		MarkType c22 = board.getCellMark(2, 2);
		MarkType c02 = board.getCellMark(0, 2);
		MarkType c20 = board.getCellMark(2, 0);

		if (c00 != MarkType.EMPTY && c00 == c11 && c11 == c22)
			return true;
		if (c02 != MarkType.EMPTY && c02 == c11 && c11 == c20)
			return true;

		return false;
	}

	public ResultType analyzeResult() {
		if (checkHorizontal() || checkVertical() || checkDiagonal()) {
			return ResultType.WIN;
		}

		if (board.isBoardFull()) {
			return ResultType.DRAW;
		}

		return ResultType.PROGRESS;
	}
}
