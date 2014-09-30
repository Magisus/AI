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

	/**
	 * constructor for building a node with set wins and playouts.
	 * 
	 * @param wins
	 * @param playouts
	 */
	public Node(double wins, int playouts) {
		children = new TreeMap<>();
		this.wins = wins;
		this.playouts = playouts;
	}

	public double getWins() {
		return wins;
	}

	public int getPlayouts() {
		return playouts;
	}

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
			result += "\t" + move + ": " + String.format("%1.3f", node.getWinRate()) + "\t(" + node.getPlayouts() + " playouts)\n";
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
			result += "\t" + move + ": " + String.format("%1.3f", node.getWinRate()) + "\t(" + node.getPlayouts() + " playouts)\n";
			if (!node.getChildren().isEmpty()) {
				childString += move + ": ";
				result += node.toString(childString);
			}
			
		}
		return result;
	}

	// think this works not sure
	public void recordPlayout(List<Integer> moves, double winScore, char color) {
		TreeMap<Integer, Node> children;
		Node node = this;
		winScore = 1 - winScore;
		node.playouts++;
		
		for (int key : moves) {
			winScore = 1 - winScore; 
			children = node.children;
			if (children.get(key) == null) {
				node.addChild(key);
				node.children.get(key).addWins(winScore);
				return;
			}
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
		} else { // otherwise find the node with highest wins and use that move
			double highWinRate = 0;
			for (Map.Entry<Integer, Node> entry : this.getChildren().entrySet()) {
				if (!legalMoves.contains(entry.getKey())){
					continue;
				}
				double currentWinRate = entry.getValue().getWinRate();
				if (currentWinRate > highWinRate) {
					highWinRate = currentWinRate;
					move = entry.getKey();
				}
			}
		}
//		System.out.println(move);
		return move;
	}

	public double getUcb1TunedValue(Node root) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMoveWithMostWins() {
		Node bestNode = new Node();
		
		// Go through the children and check who has highest wins to find bestNode
		for (Node child : this.getChildren().values()) {
			if (child.getWins() >= bestNode.getWins()) {
				bestNode = child;
			}
		}
		// Next find the map entry associated with our best node
		for (Map.Entry<Integer, Node> entry : this.getChildren().entrySet()) {
			// If we found bestNode, return the corresponding key (the move for that node)
			if (bestNode.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		// If best node was not found, return -2 to signal error occurred 
		//TODO error message
		return -2;
	}
}
