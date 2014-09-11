package search;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class BreadthFirstSearcher implements Searcher{
	
	private LinkedList<Node> frontier;
	
	int nodeCount;
	
	public BreadthFirstSearcher(){
		frontier = new LinkedList<>();
		nodeCount = 0;
	}

	@Override
	public void addToFrontier(Node node) {
		frontier.add(node);
	}

	@Override
	public boolean cutoff(Node node) {
		if(node.getDepth() > 20){
			return true;
		}
		return false;
	}

	@Override
	public boolean frontierIsEmpty() {
		return frontier.size() == 0;
	}

	@Override
	public int getNodeCount() {
		return nodeCount;
	}

	@Override
	public void initializeFrontier() {
		frontier = new LinkedList<>();
		
	}

	@Override
	public boolean inShallower(Node node, Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node removeFromFrontier() {
		return frontier.removeFirst();
	}

	@Override
	public void resetNodeCount() {
		nodeCount = 0;
		
	}

	@Override
	public LinkedList<Character> search(Node problem) {
		// TODO Auto-generated method stub
		return null;
	}

}
