package othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MctsPlayer implements Player {

	Node root;
	int playOuts;

	public MctsPlayer(int playOuts) {
		this.playOuts = playOuts;
		root = new Node();
	}

	@Override
	public int move(State state) {

		Node bestNode = new Node();

		for (int i = 0; i < playOuts; i++) {
			State copy = state.copy();
			performPlayout(copy, root);
		}

		// what peter said: TreeMap.getKeys() , gets keys of map in order, use
		// with get children to find moves
		for (Node child : root.getChildren().values()) { // go through children
															// and check their
															// wins for best
															// node
			if (child.getWins() >= bestNode.getWins()) {
				bestNode = child;
			}
		}
		// Next find the map entry associated with our best node
		for (Map.Entry<Integer, Node> entry : root.getChildren().entrySet()) {
			if (bestNode.equals(entry.getValue())) { // if it contains best
														// node, return that
														// entries key (the
														// move)
				return entry.getKey();
			}
		}

		return -1;
	}

	/**
	 * Perform a playout from the root node, descending recursively and updating
	 * the tree on the way back out.
	 */
	double performPlayout(State state, Node node) {
		TreeMap<Integer, Node> children = node.getChildren();
		double winner = 0.5;
		if (children.size() == 0) {
			List<Integer> legalMoves = state.legalMoves();
			State copy = state.copy();
			int move = legalMoves
					.get((int) (Math.random() * legalMoves.size()));
			copy.play(move);
			winner = finishPlayout(copy, new ArrayList<Integer>());
			node.addChild(move);
			node.addWins(winner);
			return winner;
		}
		// Find child with best winrate, or choose a move that hasn't been tried yet and make a node for it
		// Descend tree via that node with a recursive call. Result is winner.
		// Update this node with the result
		node.addWins(winner);
		return winner;
	}

	/** Play random moves beyond the tree till the end of the game. */
	double finishPlayout(State state, List<Integer> moves) {
		State copy = state.copy();
		// Play random moves until the game is over
		while (!copy.gameOver()) {
			List<Integer> legalMoves = copy.legalMoves();
			int randomMove = legalMoves.get((int) (Math.random() * legalMoves
					.size()));
			copy.play(randomMove);
			moves.add(randomMove);
		}
		// Report who won. Once we have the rest of our tree working we can
		// figure out whether or not this win reporting is correct. For now, it
		// passes his test.
		if (copy.score() < 0) {
			// Flip the color to play, because the node we want to update was
			// one turn earlier than the state we passed in
			return State.opposite(state.getColorToPlay()) == 'X' ? 1.0 : 0.0;
		} else if (copy.score() == 0) {
			return 0.5;
		} else {
			return State.opposite(state.getColorToPlay()) == 'X' ? 0.0 : 1.0;
		}
	}

	public void setRoot(Node node) {
		root = node;
	}

	public Node getRoot() {
		return root;
	}

}
