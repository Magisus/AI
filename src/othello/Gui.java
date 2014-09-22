package othello;

import java.awt.Color;
import static edu.princeton.cs.introcs.StdDraw.*;
import static othello.State.*;

/** Graphic user interface for Othello. */
public class Gui {

	public static void main(String[] args) {
		for(int depth = 1; depth <=7; depth++){
			for(int i = 0; i < 2; i++){				
			}
		}
		new Gui().run(new AlphaBetaPlayer(2, 'X'), new MinimaxPlayer(4, 'O'));
	}
	
	/** Plays the game. */
	public void run(Player player1, Player player2) {
		show(0);
		System.out.println("Player 1: " + player1.getClass().getName() + " at depth " + player1.getMaxSearchDepth());
		System.out.println("Player 2: " + player2.getClass().getName() + " at depth " + player2.getMaxSearchDepth());
		State board = new State();
		while (!board.gameOver()) {
			int move;
			if (board.getColorToPlay() == 'X') {
				draw(board, "Black to play. Click here if no legal move.", 0);
				move = player1.move(board);
			} else {
				draw(board, "White to play. Click here if no legal move.", 0);
				move = player2.move(board);				
			}
			if (board.legalMoves().contains(move)) {
				board.play(move);
			} else {
				draw(board, "Illegal move.", 1000);
			}
		}
		System.out.println("Player 1 node count: " + player1.getNodeCount() + "  Player 2 node count: " + player2.getNodeCount());
		String result = "Game over. ";
		if (board.score() > 0) {
			result += "Black wins!";
		} else if (board.score() < 0) {
			result += "White wins!";
		} else {
			result += "Tie";
		}
		draw(board, result, 0);
	}
	
	/**
	 * Draws the board and displays message, then pauses for pause milliseconds.
	 */
	public void draw(State board, String message, int pause) {
		clear(BLACK);
		for (int r = 0; r < WIDTH; r++) {
			for (int c = 0; c < WIDTH; c++) {
				double x = 0.15 + (0.1 * c);
				double y = 0.95 - (0.1 * r);
				setPenColor(new Color(0, 127, 0));
				filledSquare(x, y, 0.045);
				char color = board.get(r, c);
				if (color == 'X') {
					setPenColor(BLACK);
					filledCircle(x, y, 0.04);
				} else if (color == 'O') {
					setPenColor(WHITE);
					filledCircle(x, y, 0.04);
				}
			}
		}
		setPenColor(WHITE);
		text(0.5, 0.1, message);
		show(pause);
	}

}
