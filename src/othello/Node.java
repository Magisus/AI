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
		children = new HashMap<Integer, Node>(); 
	}

	public int getWins() {
		return wins;
	}

	public int getPlayOuts() {
		return playOuts;
	}

	public Map<Integer, Node> getChildren() {
		return children;
	}
	
	public void addChild(int move, Node node) {
		children.put(move, node);
	}
	
	
	
}
