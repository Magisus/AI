package othello;

import java.util.List;

public class AlphaBetaPlayer implements Player {

	private int maxSearchDepth;
	private int nodeCount;

	public AlphaBetaPlayer(int maxSearchDepth) {
		this.maxSearchDepth = maxSearchDepth;
	}
	
	public int getNodeCount(){
		return nodeCount;
	}

	@Override
	public int move(State state) {
		// Return the move with the best score
		int bestMove = -1;
		char color = state.getColorToPlay();
		int bestScore = color == 'X' ? -101 : 101;
		for (int child : state.legalMoves()) {
			State copy = state.copy();
			copy.play(child);
			int score = findScore(copy, -101, 101, 1, State.opposite(color));
			if (color == 'X') {
				if (score > bestScore) { //If this play has the best score, store the move for return
					bestScore = score;
					bestMove = child;
				}
			} else {
				if (score < bestScore) { //Same for player2
					bestScore = score;
					bestMove = child;
				}
			}
		}
		return bestMove;
	}

	private int findScore(State state, int alpha, int beta, int depth, char colorToPlay) {
		nodeCount++;
		if (depth == maxSearchDepth) { //Once at leaf, return the score of that state
			return state.score();
		}

		List<Integer> legalMoves = state.legalMoves();
		if (colorToPlay == 'X') {
			for (int currentMove : legalMoves) {
				State copy = state.copy();
				copy.play(currentMove); 
				alpha = Math.max(alpha,
						findScore(copy, alpha, beta, depth + 1, State.opposite(colorToPlay))); //Find the best score we can from here recursively
				if (beta <= alpha) { // If this move is too good to reach, don't analyze from here. Cut this node off. 
					break;
				}
			}
			return alpha; //Otherwise return this score 
		}
		//Do the same as above from perspective of min player
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
