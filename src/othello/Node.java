package othello;

import java.util.*;

/** Node in a Monte Carlo search tree. */
public class Node {
	
	/** Number of wins through this node. */
	private int wins;
	
	/** Number of playouts through this node. */
	private int playOuts;
	
	/** Children of this node. */
	private Map<Integer, Node> children;
	
	public Node() {
		children = new TreeMap<Integer, Node>(); 
		// 1/2 win rate to intialize all nodes
		wins = 1;
		playOuts = 2;
	}

	public int getWins() {
		return wins;
	}

	public int getPlayOuts() {
		return playOuts;
	}
	
	public double getWinRate() {
		return (double)wins/(double)playOuts;
	}
	
	public void addWin() {
		this.wins++;
		this.playOuts++;
	}
	
	public void addLoss() {
		this.playOuts++;
	}

	public Map<Integer, Node> getChildren() {
		return children;
	}
	
	public void addChild(int move, Node node) {
		children.put(move, node);
	}
	
	
	
}
