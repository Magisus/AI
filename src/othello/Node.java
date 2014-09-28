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
		// 1/2 win rate to intialize all nodes
		wins = 1;
		playOuts = 2;
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
	
	public void addChild(int move) {
		children.put(move, new Node());
	}
	
	public Node getChild(int move){
		return children.get(move);
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
	
	
}
