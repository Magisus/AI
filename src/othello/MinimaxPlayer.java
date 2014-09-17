package othello;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MinimaxPlayer implements Player {

	int maxSearchDepth;
	
	
	@Override
	public int move(State state) {
		return 0;
	}
	
	public int move(State state, int depth){
		List<Integer> legMoves = state.legalMoves();
		if (legMoves.get(0) == State.PASS){
			return State.PASS;
		}
		if (depth == maxSearchDepth ){
			Collections.sort(legMoves);
			return legMoves.get(legMoves.size() - 1);
		}
		return 0;
	}
	
	public MinimaxPlayer(int maxSearchDepth) {
		this.maxSearchDepth = maxSearchDepth;
	}

}
