package othello;

import java.util.*;

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

	// this isnt displaying it correctly but I can't quite figure out Peter's
	// string in the test. not sure how he is building it
	@Override
	public String toString() {
		String result = "<root>\t(" + this.playouts + " playouts)\n";
		for (Map.Entry<Integer, Node> entry : this.getChildren().entrySet()) {
			int move = entry.getKey();
			result += "\t" + move + ": " + entry.getValue().getWinRate() + "\t("
					+ entry.getValue().getPlayouts() + " playouts)\n";
		}
		return result;
	}

	public void recordPlayout(List<Integer> moves, double d, char c) {
		// TODO Auto-generated method stub

	}

	public int playoutMove(State state) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getUcb1TunedValue(Node root) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMoveWithMostWins() {
		// TODO Auto-generated method stub
		return 0;
	}
}
