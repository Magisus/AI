package othello;

import java.util.List;

public class MinimaxPlayer implements Player {

	private int maxSearchDepth;
	private char color;
	private int nodeCount;

	public MinimaxPlayer(int maxSearchDepth, char color) {
		this.maxSearchDepth = maxSearchDepth;
		this.color = color;
	}

	public int getNodeCount(){
		return nodeCount;
	}
	
	@Override
	public int move(State state) {
		//Return the move with the best score
		int bestMove = -1; 
		int bestScore = color == 'X' ? -101 : 101; //initialize best score as impossibly low/high depending on player for comparison 
		for(int child : state.legalMoves()){
			State copy = state.copy(); 
			copy.play(child);	
			int score = findScore(copy, 1, State.opposite(color));
			if(color == 'X'){
				if(score > bestScore){	//if this play has the best score, store the move for return
					bestScore = score;
					bestMove = child;
				}
			} else {
				if(score < bestScore){  //same for player2
					bestScore = score;
					bestMove = child;
				}
			}
		}
		return bestMove;
	}

	/**
	 * Recursively goes through possible moves and returns the best score possible if taken from the state passed in
	 * @param state starting state of board
	 * @param depth 
	 * @param colorToPlay
	 * @return
	 */
	public int findScore(State state, int depth, char colorToPlay) {
		nodeCount++;
		if(depth == maxSearchDepth){ //once at leaf, return the score of that state
			return state.score();
		}
		
		List<Integer> legalMoves = state.legalMoves();
		int bestScore = colorToPlay == 'X' ? -101 : 101; //initialize best score as impossibly low/high depending on player for comparison 
		for (int currentMove : legalMoves) {
			State copy = state.copy();
			copy.play(currentMove);
			int score = findScore(copy, depth + 1, State.opposite(colorToPlay)); //search down until leaf, then return scores up
			if (colorToPlay == 'X') { 											//once have score, compare for max or min and set to best
				bestScore = Math.max(score, bestScore);
			} else {
				bestScore = Math.min(score, bestScore);
			}
		}
		return bestScore;
	}

	@Override
	public int getMaxSearchDepth() {
		return maxSearchDepth;
	}

}