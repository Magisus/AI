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
		for (Map.Entry<Integer, Node> entry : root.getChildren().entrySet()) { // next check each entry in map and see if it contains our best node.
			if ( bestNode.equals(entry.getValue()) ) {			// if it contains best node, return that entries key (the move)
				return entry.getKey();
			}
		}
		
		return -1;
	}

	char performPlayout(State state, Node node) {
		TreeMap<Integer, Node> children = node.getChildren();
		if(children.size() == 0){
			List<Integer> legalMoves = state.legalMoves();
			State copy = state.copy();
			int move = legalMoves.get((int)(Math.random() * legalMoves.size()));
			copy.play(move);
			char winner = finishPlayout(copy, new ArrayList<Integer>());
			Node newChild = new Node();
			node.addChild(move, new Node());
			if(winner == state.getColorToPlay()){
				node.addWin();
			} else { //This will add losses for ties
				node.addLoss();
			}
		}
		return 'T';
	}

	char finishPlayout(State state, List<Integer> moves){
		State copy = state.copy();
		while(!copy.gameOver()){
			List<Integer> legalMoves = copy.legalMoves();
			copy.play(moves.get((int)(Math.random() * moves.size())));
		}
		if(copy.score() < 0){
			return 'X';
		} else if(copy.score() == 0){
			return 'T';
		} else {
			return 'O';
		}
	}

	public void setRoot(Node node) {
		root = node;		
	}

	public Node getRoot() {
		return root;
	}

}
