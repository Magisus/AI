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
		//Return the move with the best score
		int bestMove = -1;
		int bestScore = color == 'X' ? -101 : 101;
		for(int child : state.legalMoves()){
			State copy = state.copy();
			copy.play(child);
			int score = findScore(copy, 1, color);
			if(color == 'X'){
				if(score > bestScore){
					bestScore = score;
					bestMove = child;
				}
			} else {
				if(score < bestScore){
					bestScore = score;
					bestMove = child;
				}
			}
		}
		return bestMove;
	}

	public int findScore(State state, int depth, char colorToPlay) {
		if(depth == maxSearchDepth){
			return state.score();
		}
		
		List<Integer> legalMoves = state.legalMoves();
		int bestScore = colorToPlay == 'X' ? -101 : 101;
		for (int currentMove : legalMoves) {
			State copy = state.copy();
			copy.play(currentMove);
			int score = findScore(copy, depth + 1, State.opposite(colorToPlay));
			if (colorToPlay == 'X') {
				bestScore = Math.max(score, bestScore);
			} else {
				bestScore = Math.min(score, bestScore);
			}
		}
		return bestScore;
	}

}
