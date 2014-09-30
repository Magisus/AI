package othello;

import java.util.*;

import edu.princeton.cs.introcs.StdRandom;

/** Node in a Monte Carlo search tree. */
public class Node {

	/** Number of wins through this node. */
	private double wins;

	/** Number of playouts through this node. */
	private int playouts;

	/** Children of this node. */
	private TreeMap<Integer, Node> children;

	public Node() {
		children = new TreeMap<>();
	}

	public double getWins() {
		return wins;
	}

	public int getPlayouts() {
		return playouts;
	}

	/** Returns ration of wins to playouts. */
	public double getWinRate() {
		return wins / playouts;
	}

	public void addWins(double wins) {
		this.wins += wins;
		this.playouts++;
	}

	public TreeMap<Integer, Node> getChildren() {
		return children;
	}

	public void addChild(int move) {
		children.put(move, new Node());
	}

	public Node getChild(int move) {
		return children.get(move);
	}

	@Override
	public String toString() {
		String result = "<root>\t(" + this.playouts + " playouts)\n";
		String childString;

		for (Map.Entry<Integer, Node> entry : this.getChildren().entrySet()) {
			int move = entry.getKey();
			Node node = entry.getValue();
			result += "\t" + move + ": "
					+ String.format("%1.3f", node.getWinRate()) + "\t("
					+ node.getPlayouts() + " playouts)\n";
			if (!node.getChildren().isEmpty()) {
				childString = "\t" + move + ": ";
				result += node.toString(childString);
			}
		}

		return result;
	}

	public String toString(String childString) {
		String result = childString;
		for (Map.Entry<Integer, Node> entry : this.getChildren().entrySet()) {
			int move = entry.getKey();
			Node node = entry.getValue();
			result += "\t" + move + ": "
					+ String.format("%1.3f", node.getWinRate()) + "\t("
					+ node.getPlayouts() + " playouts)\n";
			if (!node.getChildren().isEmpty()) {
				childString += "\t" + move + ": ";
				result += node.toString(childString);
			}

		}
		return result;
	}

	/**
	 * updates score & playouts of all nodes on path taken during this playout.
	 * also adds new node where we started to randomly finishPlayout
	 * @param moves list of moves taken in this playout
	 * @param winScore
	 * @param color 
	 */
	public void recordPlayout(List<Integer> moves, double winScore, char color) {
		TreeMap<Integer, Node> children;
		Node node = this;
		if (color == 'X') winScore = 1 - winScore;
		node.playouts++;

		for (int key : moves) {
			winScore = 1 - winScore;
			children = node.children;
			// if the move does not have a child, it is first move of random playout aka new node
			// so add it, give it a score, and finish
			if (children.get(key) == null) {
				node.addChild(key);
				node.children.get(key).addWins(winScore);
				return;
			}
			// otherwise move to next node down the list and update its score 
			node = children.get(key);
			node.addWins(winScore);
		}
		return;

	}

	/**
	 * 
	 * @param state
	 * @return move or -2 (leaf)
	 */
	public int playoutMove(State state) {

		int move = -2; // -2 is our fail state
		List<Integer> legalMoves = state.legalMoves();
		List<Integer> unplayed = new ArrayList<Integer>();

		if (this.children.size() == 0) {
			return -2;
		}

		// check for unplayed values
		for (int legMove : legalMoves) {
			if (children.get(legMove) == null) {
				unplayed.add(legMove);
			}
		}
		// if there are unplayed values, choose from them randomly
		if (!unplayed.isEmpty()) {
			move = unplayed.get(StdRandom.uniform(unplayed.size()));
		} else { // otherwise find node with highest Ucb1 value
			double ucb = 0;
			for (Map.Entry<Integer, Node> entry : this.getChildren().entrySet()) {
				if (!legalMoves.contains(entry.getKey())) {
					continue;
				}
				double currentUCB = entry.getValue().getUcb1TunedValue(this);
				if (currentUCB > ucb) {
					ucb = currentUCB;
					move = entry.getKey();
				}
			}			
		}
		return move;
	}
	
	public double getUcb1TunedValue(Node parent) {
		double r = this.getWinRate(); 	// the win rate for the move
		int P = parent.playouts; 		// the total number of playouts through the parent
		int p = this.playouts; 			// the number of playouts through the move
		double value = r
				+ Math.sqrt((Math.log(P) / p)
						* Math.min(0.25, (r - Math.pow(r, 2) + 
								Math.sqrt(2 * (Math.log(P) / Math.PI)))));
		return value;
	}

	public int getMoveWithMostWins(State state) {
		Node bestNode = new Node();
		
		if(children.size() == 0){
			return State.PASS;
		}
		
		// Go through the children and check who has highest wins to find bestNode
		for (int key : this.getChildren().keySet()) {
			if (!state.legalMoves().contains(key)) {
				continue;
			}
			Node child = children.get(key);
			if (child.getWins() >= bestNode.getWins()) {
				bestNode = child;
			}
		}
		// Next find the map entry associated with our best node
		for (Map.Entry<Integer, Node> entry : this.getChildren().entrySet()) {
			// If we found bestNode, return the corresponding key (the move for
			// that node)
			if (bestNode.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		// None of the moves with nodes are legal. Choose a legal move at random.
		return state.legalMoves().get(StdRandom.uniform(state.legalMoves().size()));
	}
}
