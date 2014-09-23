package othello;

import java.util.List;

public class AlphaBetaPlayer implements Player {

	private char color;
	private int maxSearchDepth;
	private int nodeCount;

	public AlphaBetaPlayer(int maxSearchDepth, char color) {
		this.maxSearchDepth = maxSearchDepth;
		this.color = color;
	}
	
	public int getNodeCount(){
		return nodeCount;
	}

	@Override
	public int move(State state) {
		// Return the move with the best score
		int bestMove = -1;
		int bestScore = color == 'X' ? -101 : 101; //initialize best score as impossibly low/high depending on player for comparison 
		for (int child : state.legalMoves()) {
			State copy = state.copy();
			copy.play(child);
			int score = findScore(copy, -101, 101, 1, State.opposite(color));
			if (color == 'X') {
				if (score > bestScore) { //if this play has the best score, store the move for return
					bestScore = score;
					bestMove = child;
				}
			} else {
				if (score < bestScore) { //same for player2
					bestScore = score;
					bestMove = child;
				}
			}
		}
		return bestMove;
	}

	private int findScore(State state, int alpha, int beta, int depth, char colorToPlay) {
		nodeCount++;
		if (depth == maxSearchDepth) { //once at leaf, return the score of that state
			return state.score();
		}

		List<Integer> legalMoves = state.legalMoves();
		if (colorToPlay == 'X') {
			for (int currentMove : legalMoves) {
				State copy = state.copy();
				copy.play(currentMove); 
				alpha = Math.max(alpha,
						findScore(copy, alpha, beta, depth + 1, State.opposite(colorToPlay))); //find the best score we can from here recursively
				if (beta <= alpha) { // if this move is too good to reach, dont analyze from here. cut this node off. 
					break;
				}
			}
			return alpha; //otherwise return this score 
		}
		//do the same as above from perspective of min player
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

	@Override
	public int getMaxSearchDepth() {
		return maxSearchDepth;
	}

}