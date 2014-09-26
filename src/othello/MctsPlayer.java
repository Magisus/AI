package othello;

import java.util.Map;

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
		
		for(int i = 0; i < playOuts; i++) {
			State copy = state.copy();
			performPlayout(copy, root);
		}
		
		// what peter said: TreeMap.getKeys() , gets keys of map in order, use with get children to find moves		
		
		for (Node child : root.getChildren().values()) { 	// go through children and check their wins for best node
			if (child.getWins() >= bestNode.getWins()) { 
				bestNode = child; 
				}
		}
		for (Map.Entry entry : root.getChildren().entrySet()) { // next check each entry in map and see if it contains our best node.
			if ( bestNode.equals(entry.getValue()) ) {			// if it contains best node, return that entries key (the move)
				return (int)entry.getKey();
			}
		}
		
		return -1;
	}

	private void performPlayout(State state, Node node) {
		
		
		
	}


}
