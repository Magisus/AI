package othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.princeton.cs.introcs.StdRandom;

// do we need to copy state so much?

public class MctsPlayer implements Player {

	Node root;
	int playouts;
	int playoutsCompleted;

	public MctsPlayer(int playouts) {
		this.playouts = playouts;
		root = new Node();
	}

	@Override
	public int move(State state) {
		for (int i = 0; i < playouts; i++) {
			playout(state);
		}
		int bestMove = root.getMoveWithMostWins(state);
		root = root.getChildren().get(bestMove);
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
		if (root == null) {
			root = new Node();
		}
		descend(copy, root, moves);
		double winScore = 0.5;
		if (!copy.gameOver()) {
			winScore = finishPlayout(copy, moves);
		} else {
			if (copy.score() > 0) winScore = 1;
			if (copy.score() < 0) winScore = 0;
			winScore = 0.5;
		}
		root.recordPlayout(moves, winScore, state.getColorToPlay());
	}

	/** Chooses random moves until the end of the game, then returns the score. */
	public double finishPlayout(State state, List<Integer> moves) { 
		// Play random moves until the game is over
		while (!state.gameOver()) {
			List<Integer> legalMoves = state.legalMoves();
			if (legalMoves.isEmpty()) {
				state.play(State.PASS);
				moves.add(State.PASS);
			}
			int randomMove = legalMoves.get(StdRandom.uniform(legalMoves.size()));
			state.play(randomMove);
			moves.add(randomMove);
		} 
		if (state.score() > 0) return 1;
		if (state.score() < 0) return 0;
		return 0.5;
	}

	/**
	 * Finds the value (0.0-1.0) to be added to the nodes, given the original
	 * color to play.
	 */
	private double findWinCount(double score, char originalColorToPlay) {
		if (originalColorToPlay == 'X' && score < 0) {
			return 1;
		} else if (originalColorToPlay == 'O' && score > 0) {
			return 1;
		} else if (score == 0) {
			return 0.5;
		} else {
			return 0;
		}
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
