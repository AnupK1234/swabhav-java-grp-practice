package com.aurionpro.test;

import java.util.Scanner;

import com.aurionpro.model.*;

public class GameTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Player p1 = new Player(1, "Anup", MarkType.X);
		Player p2 = new Player(2, "Deep", MarkType.O);

		Player[] players = { p1, p2 };
		Board board = new Board();
		ResultAnalyzer analyzer = new ResultAnalyzer(board);
		Game game = new Game(players, board, analyzer);

		while (game.getResult() == ResultType.PROGRESS) {
			System.out
					.println(game.getCurrentPlayer().getName() + "'s turn [" + game.getCurrentPlayer().getMark() + "]");
			System.out.print("Enter row (0-2): ");
			int row = sc.nextInt();
			System.out.print("Enter column (0-2): ");
			int col = sc.nextInt();

			try {
				game.play(row, col);
				board.displayBoard();
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
				continue;
			}
		}

		System.out.println("Game Over!");
		if (game.getResult() == ResultType.WIN) {
			System.out.println("Winner is: " + game.getCurrentPlayer().getName());
		} else if (game.getResult() == ResultType.DRAW) {
			System.out.println("It's a Draw!");
		}

		sc.close();
	}
}
