package othello;

import java.util.*;

/** Node in a Monte Carlo search tree. */
public class Node {
	
	/** Number of wins through this node. */
	private double wins;
	
	/** Number of playouts through this node. */
	private int playOuts;
	
	/** Children of this node. */
	private TreeMap<Integer, Node> children;
	
	public Node() {
		children = new TreeMap<>(); 
		// I think actually we don't want to initialize it like this, not sure. Let's think about it
//		// 1/2 win rate to initialize all nodes
//		wins = 1;
//		playOuts = 2;
	}
	
	/**
	 * constructor for building a node with set wins and playouts. 
	 * @param wins
	 * @param playOuts
	 */
	public Node(double wins, int playOuts) {
		children = new TreeMap<>();
		this.wins = wins;
		this.playOuts = playOuts;
	}

	public double getWins() {
		return wins;
	}

	public int getPlayOuts() {
		return playOuts;
	}
	
	public double getWinRate() {
		return wins/(double)playOuts;
	}
	
	public void addWins(double wins) {
		this.wins += wins;
		this.playOuts++;
	}

	public TreeMap<Integer, Node> getChildren() {
		return children;
	}
	
	public void addChild(int move, Node node) {
		children.put(move, node);
	}
	
	// this isnt displaying it correctly but I can't quite figure out Peter's string in the test. not sure how he is building it
	@Override
	public String toString() {
		String result = "<root>\t(" + this.playOuts + " playouts)\n";
		for (Map.Entry<Integer, Node> entry : this.getChildren().entrySet()) {
			int move = entry.getKey();
			result += "\t" + move + ": " + entry.getValue().getWins() + "\t(" + entry.getValue().getPlayOuts() + " playouts)\n";
		}
//		System.out.println(result);
		return result;
	}

	public void recordPlayout(List<Integer> moves, double d, char c) {
		// TODO Auto-generated method stub
		
	}

	public int playoutMove(State state) {
		// TODO Auto-generated method stub
		return 0;
	}
		
}
