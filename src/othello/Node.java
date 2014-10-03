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
		if (color == 'X'){
			winScore = 1 - winScore;
		}
		Node node = this;
		node.playouts++;

		for (int key : moves) {
			winScore = 1 - winScore;
			TreeMap<Integer, Node> otherChildren = node.children;
			// if the move does not have a child, it is first move of random playout aka new node
			// so add it, give it a score, and finish
			if (otherChildren.get(key) == null) {
				node.addChild(key);
				node.getChild(key).addWins(winScore);
				return;
			}
			// otherwise move to next node down the list and update its score 
			node = otherChildren.get(key);
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
		List<Integer> unplayed = new ArrayList<>();

		if (children.size() == 0) {
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
			for(int key : children.keySet()){
				double currentUCB = children.get(key).getUcb1TunedValue(this);
				if (currentUCB > ucb) {
					ucb = currentUCB;
					move = key;
				}
			}			
		}
		return move;
	}
	
	public double getUcb1TunedValue(Node parent) {
		double r = getWinRate(); 	// the win rate for the move
		int P = parent.playouts; 		// the total number of playouts through the parent
		int p = playouts; 			// the number of playouts through the move
		double value = r
				+ Math.sqrt((Math.log(P) / p)
						* Math.min(0.25, (r - Math.pow(r, 2) + 
								Math.sqrt(2 * (Math.log(P) / p)))));
		return value;
	}

	public int getMoveWithMostWins() {
		int bestMove = State.PASS;
		double mostWins = 0;
		for(int key : children.keySet()){
			Node child = children.get(key);
			if(child.getWins() >= mostWins){
				mostWins = child.getWins();
				bestMove = key;
			}
		}
		System.out.println(toString());
		return bestMove;
	}
}
