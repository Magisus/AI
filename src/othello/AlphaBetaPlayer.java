package othello;

import java.util.List;

public class AlphaBetaPlayer implements Player {

	private char color;
	private int maxSearchDepth;

	public AlphaBetaPlayer(int maxSearchDepth, char color) {
		this.maxSearchDepth = maxSearchDepth;
		this.color = color;
	}

	@Override
	public int move(State state) {
		// Return the move with the best score
		int bestMove = -1;
		int bestScore = color == 'X' ? -101 : 101;
		for (int child : state.legalMoves()) {
			State copy = state.copy();
			copy.play(child);
			int score = findScore(copy, -101, 101, 1, color);
			if (color == 'X') {
				if (score > bestScore) {
					bestScore = score;
					bestMove = child;
				}
			} else {
				if (score < bestScore) {
					bestScore = score;
					bestMove = child;
				}
			}
		}
		return bestMove;
	}

	private int findScore(State state, int alpha, int beta, int depth, char colorToPlay) {
		if (depth == maxSearchDepth) {
			return state.score();
		}

		List<Integer> legalMoves = state.legalMoves();
		if (colorToPlay == 'X') {
			for (int currentMove : legalMoves) {
				State copy = state.copy();
				copy.play(currentMove);
				alpha = Math.max(alpha,
						findScore(copy, alpha, beta, depth + 1, State.opposite(colorToPlay)));
				if (beta <= alpha) {
					break;
				}
			}
			return alpha;
		}
		for (int currentMove : legalMoves) {
			State copy = state.copy();
			copy.play(currentMove);
			beta = Math.min(beta,
					findScore(copy, alpha, beta, depth + 1, State.opposite(colorToPlay)));
			if (beta <= alpha) {
				break;
			}
		}
		return beta;
	}

}
