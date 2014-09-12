package search;

import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class AStarSearcher implements Searcher {
	
	private PriorityQueue<Node> frontier;
	private int nodeCount;

	@Override
	public void addToFrontier(Node node) {
		frontier.add(node);
	}

	@Override
	public boolean cutoff(Node node) {
		return node.getDepth() > 20;
	}

	@Override
	public boolean frontierIsEmpty() {
		return frontier.isEmpty();
	}

	@Override
	public int getNodeCount() {
		return nodeCount;
	}

	@Override
	public void initializeFrontier() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean inShallower(Node node, Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node removeFromFrontier() {
		return frontier.poll();
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
