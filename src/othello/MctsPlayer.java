package othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
		return root.getMoveWithMostWins();
	}

	/**
	 * Plays out till the end of the game, first by descending the tree then by
	 * playing random moves. Updates the tree after reaching the end of the
	 * game.
	 */
	public void playout(State state) {
		State copy = state.copy();
		copy = descend(copy);
		double winCount = 0.5;
		ArrayList<Integer> moves = new ArrayList<>();
		if (!copy.gameOver()) {
			winCount = findWinCount(finishPlayout(copy, moves), state.getColorToPlay());
		} else {
			winCount = findWinCount(copy.score(), state.getColorToPlay());
		}
		root.recordPlayout(moves, winCount, state.getColorToPlay());
	}

	/** Chooses random moves until the end of the game, then returns the score. */
	public int finishPlayout(State state, List<Integer> moves) {
		return state.score();
	}

	/**
	 * Finds the value (0.0-1.0) to be added to the nodes, given the original
	 * color to play.
	 */
	private double findWinCount(int score, char originalColorToPlay) {
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

	/** Descends through the tree, returning the state of the board at the tree leaf. */
	public State descend(State state) {
		return state;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}
}
