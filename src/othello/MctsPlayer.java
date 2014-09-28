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
		
		// Go through the children and check who has highest wins to find bestNode
		for (Node child : root.getChildren().values()) { 
			if (child.getWins() >= bestNode.getWins()) {
				bestNode = child;
			}
		}
		// Next find the map entry associated with our best node
		for (Map.Entry<Integer, Node> entry : root.getChildren().entrySet()) {
			// If we found bestNode, return the corresponding key (the move for that node)
			if (bestNode.equals(entry.getValue())) { 
				return entry.getKey();
			}
		}
		// If best node was not found, return -1 to signal error occurred 
		//TODO error message
		return -1;
	}

	/**
	 * Perform a playout from the root node, descending recursively and updating
	 * the tree on the way back out.
	 */
	double performPlayout(State state, Node node) {
		
		TreeMap<Integer, Node> children = node.getChildren();
		double winScore = 0.5;
		int move = -1;
		State copy = state.copy();
		
		// if a leaf, go through and play out randomly, then add new node for that result
		if (children.size() == 0) {
			List<Integer> legalMoves = state.legalMoves();
			move = legalMoves
					.get((int) (Math.random() * legalMoves.size()));
			copy.play(move);
			winScore = finishPlayout(copy, new ArrayList<Integer>()); //TODO verify passing new array list as arg
			node.addChild(move, new Node(winScore, 1)); //add new child with winScore and 1 playout.
			// In my implementation, I think we will need to change the way Maggie has winScore reported in finishPlayout
			return winScore;
		}
		
		// otherwise recurse on best node (random choice until all played once, then highest win rate (atm.) should use UCB1-TUNED formula at some point)

		// check for unplayed values
		Node nodeToPlay = null;
		List<Integer> unplayed = null;
		for (Map.Entry<Integer, Node> entry : node.getChildren().entrySet()) {
			if (entry.getValue().getPlayOuts() == 0) { unplayed.add(entry.getKey()); }
		}
		// if there are unplayed values, choose from them randomly
		if (!unplayed.isEmpty()) {
			move = unplayed.get((int) (Math.random() * unplayed.size() - 1));
			nodeToPlay = node.getChildren().get(move);
		} else { //otherwise find the node with highest win rate and use that move
			double highWin = 0;
			for (Map.Entry<Integer, Node> entry : node.getChildren().entrySet()) {
				double currentWin = entry.getValue().getWins();
				if (currentWin >= highWin) { 
					highWin = currentWin; 
					move = entry.getKey(); 
					nodeToPlay = entry.getValue();
					}
			}
			
		}
		
		copy.play(move);
		winScore = performPlayout(copy, nodeToPlay);
		node.addWins(winScore);
		
		//need to alternate score each return back.
		return winScore;
		
//		if (children.size() == 0) {
//			List<Integer> legalMoves = state.legalMoves();
//			State copy = state.copy();
//			int move = legalMoves
//					.get((int) (Math.random() * legalMoves.size()));
//			copy.play(move);
//			winScore = finishPlayout(copy, new ArrayList<Integer>());
//			Node newChild = new Node();
//			node.addChild(move, newChild);
//			node.addWins(winScore);
//			return winScore;
//		}
//		/* Find child with best winrate, or choose a move that hasn't been tried yet and make a node for it
//		 * Descend tree via that node with a recursive call. Result is winScore.
//		 * Update this node with the result */
//		
//		node.addWins(winScore);
//		return winScore;
	}

	/** 
	 * Play random moves beyond the tree till the end of the game.
	 * @return 1.0 for win, 0.5 for tie, 0 for loss 
	 */
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
		/* Report who won. Once we have the rest of our tree working we can
		 * figure out whether or not this win reporting is correct. For now, it
		 * passes his test. */
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
	
	@Override
	public String toString() {
		return "Monte Carlo " + this.playOuts;
	}

}
