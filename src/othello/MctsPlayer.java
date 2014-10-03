package othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.princeton.cs.introcs.StdRandom;


public class MctsPlayer implements Player {

	Node root;
	int playouts;

	public MctsPlayer(int playouts) {
		this.playouts = playouts;
		root = new Node();
	}

	@Override
	public int move(State state) {
		root = new Node();
		for (int i = 0; i < playouts; i++) {
			playout(state);
		}
		int bestMove = root.getMoveWithMostWins();
		return bestMove;
		
	}

	/**
	 * Plays out till the end of the game, first by descending the tree then by
	 * playing random moves. Updates the tree after reaching the end of the
	 * game.
	 */
	public void playout(State state) {
		State copy = state.copy();
		ArrayList<Integer> moves = new ArrayList<>();
		descend(copy, root, moves);
		double winScore = finishPlayout(copy, moves);
		root.recordPlayout(moves, winScore, state.getColorToPlay());
	}

	/** Descends through the tree. */
	public void descend(State state, Node node, List<Integer> moves) {
		Node nodeToPlay = null;
		int move;
		
		move = node.playoutMove(state);
		while (move != -2) { //leaf 
			moves.add(move);
			state.play(move);
			nodeToPlay = node.getChildren().get(move);
			if (nodeToPlay == null) { //leaf
				return;
			}
			node = nodeToPlay;
			move = node.playoutMove(state);
		}
		return;
	}
	
	/** Chooses random moves until the end of the game, then returns the score. */
	public double finishPlayout(State state, List<Integer> moves) { 
		while (!state.gameOver()) {
			List<Integer> legalMoves = state.legalMoves();
			int randomMove = legalMoves.get(StdRandom.uniform(legalMoves.size()));
			state.play(randomMove);
			moves.add(randomMove);
		} 
		if (state.score() > 0) return 1;
		if (state.score() < 0) return 0;
		return 0.5;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}
	
	@Override
	public String toString() {
		return "Monte Carlo " + this.playouts;
	}
}
