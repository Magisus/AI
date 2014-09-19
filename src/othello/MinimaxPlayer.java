package othello;

import java.util.List;

public class MinimaxPlayer implements Player {

	private int maxSearchDepth;
	private char color;

	public MinimaxPlayer(int maxSearchDepth, char color) {
		this.maxSearchDepth = maxSearchDepth;
		this.color = color;
	}

	@Override
	public int move(State state) {
		return move(state, 0, color);
	}

	public int move(State state, int depth, char colorToPlay) {
		int bestMove = -1;
		List<Integer> legalMoves = state.legalMoves();
		for (int currentMove : legalMoves) {
			State copy = state.copy();
			copy.play(currentMove);
			if (colorToPlay == 'X') {
				int bestScore = -101;
				//TODO We need to find the score and recur, somewhere
				if (bestScore < score) {
					bestScore = score;
					bestMove = currentMove;
				}
			} else {
				int bestScore = 101;
				int score = depth + 1 == maxSearchDepth ? copy.score() : move(copy, depth
						+ 1, State.opposite(colorToPlay));
				if (bestScore > score) {
					bestScore = score;
					bestMove = currentMove;
				}
			}
		}
		System.out.println(bestMove);
		return bestMove;
	}

}
