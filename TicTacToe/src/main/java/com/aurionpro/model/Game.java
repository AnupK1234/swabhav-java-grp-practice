package com.aurionpro.model;

public class Game {
	Player currentPlayer;
	Player[] players;
	Board board;
	ResultAnalyzer analyzer;
	ResultType result = ResultType.PROGRESS;

	public Game(Player[] players, Board board, ResultAnalyzer analyzer) {
		this.players = players;
		this.board = board;
		this.analyzer = analyzer;
		this.currentPlayer = players[0]; // start with player 1
	}

	public void play(int row, int col) throws Exception {
		if (result != ResultType.PROGRESS)
			throw new Exception("Game has already ended.");

		board.setCellMark(row, col, currentPlayer.getMark());
		result = analyzer.analyzeResult();

		if (result == ResultType.PROGRESS)
			switchPlayer();
	}

	private void switchPlayer() {
		currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public ResultType getResult() {
		return result;
	}

}
