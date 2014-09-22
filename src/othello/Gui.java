package othello;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static edu.princeton.cs.introcs.StdDraw.*;
import static othello.State.*;

/** Graphic user interface for Othello. */
public class Gui {	

	//print mini 1 vs alpha 2 
	public static void main(String[] args) {
		
		StringBuilder node1 = new StringBuilder();
		StringBuilder node2 = new StringBuilder();
		StringBuilder winner = new StringBuilder();
		
		//run tourney for Minimax as X and Alpha as O, then Minimax as X and Minimax as O
		for (int depth1 = 1; depth1 <= 3; depth1++) {
			for (int i = 0; i < 2; i++){
				if (i == 0 ) {
					for (int depth2 = 1; depth2 <= 3; depth2++) {
						new Gui().run(new MinimaxPlayer(depth1, 'X'), new AlphaBetaPlayer(depth2, 'O'), node1, node2, winner);
					}
				}
				if (i == 1 ) {
					for (int depth2 = 1; depth2 <= 3; depth2++) {
						new Gui().run(new MinimaxPlayer(depth1, 'X'), new MinimaxPlayer(depth2, 'O'), node1, node2, winner);
					}
				}
			}
			node1.append("\n"); node2.append("\n"); winner.append("\n");
		}
		
		//run tourney for Alpha as X and Minimax as O, then Alpha as O and Alpha as X
		for (int depth1 = 1; depth1 <= 3; depth1++) {
			for (int i = 0; i < 2; i++){
				if (i == 0 ) {
					for (int depth2 = 1; depth2 <= 3; depth2++) {
						new Gui().run(new AlphaBetaPlayer(depth1, 'X'), new MinimaxPlayer(depth2, 'O'), node1, node2, winner);
					}
				}
				if (i == 1 ) {
					for (int depth2 = 1; depth2 <= 3; depth2++) {
						new Gui().run(new AlphaBetaPlayer(depth1, 'X'), new AlphaBetaPlayer(depth2, 'O'), node1, node2, winner);
					}
				}
			}
			node1.append("\n"); node2.append("\n"); winner.append("\n");
		}
		
		//print results
		try(PrintWriter writer = new PrintWriter("Node1.csv")){
			writer.print(node1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try(PrintWriter writer = new PrintWriter("Node2.csv")){
			writer.print(node2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try(PrintWriter writer = new PrintWriter("Winner.csv")){
			writer.print(winner);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/** Plays the game. */
	public String run(Player player1, Player player2, StringBuilder node1, StringBuilder node2, StringBuilder winner) {
//		show(0);
		State board = new State();
		while (!board.gameOver()) {
			int move;
			if (board.getColorToPlay() == 'X') {
//				draw(board, "Black to play. Click here if no legal move.", 0);
				move = player1.move(board);
			} else {
//				draw(board, "White to play. Click here if no legal move.", 0);
				move = player2.move(board);
			}
			if (board.legalMoves().contains(move)) {
				board.play(move);
			} else {
//				draw(board, "Illegal move.", 1000);
			}
		}
		node1.append(player1.getNodeCount() + ", ");
		node2.append(player2.getNodeCount() + ", ");
		String result = "Game over. ";
		if (board.score() > 0) {
			winner.append("true, ");
			result += "Black wins!";
		} else if (board.score() < 0) {
			winner.append("false, ");
			result += "White wins!";
		} else {
			winner.append("tie, ");
			result += "Tie";
		}
		return result;
//		draw(board, result, 0);
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
