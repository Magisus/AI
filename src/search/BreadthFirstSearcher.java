package search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class BreadthFirstSearcher implements Searcher {

	private LinkedList<Node> frontier;
	int nodeCount;

	public BreadthFirstSearcher() {
		frontier = new LinkedList<>();
		nodeCount = 0;
	}

	@Override
	public void addToFrontier(Node node) {
			frontier.add(node);
			nodeCount++;
	}

	@Override
	public boolean cutoff(Node node) {
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

	@SuppressWarnings("boxing")
	@Override
	public boolean inShallower(Node node, Map<String, Integer> map) {
		return map.get(node.getState()) != null && map.get(node.getState()) < node.getDepth();
	}

	@Override
	public Node removeFromFrontier() {
		return frontier.removeFirst();
	}

	@Override
	public void resetNodeCount() {
		nodeCount = 0;

	}

	@SuppressWarnings("boxing")
	@Override
	public LinkedList<Character> search(Node problem) {
		frontier.clear();
		Map<String, Integer> map = new HashMap<>();
		addToFrontier(problem);
		while (!frontier.isEmpty()) {
			Node node = removeFromFrontier();
			if (node.isGoal()) {
				return node.path();
			}
			if (!inShallower(node, map)) {
				map.put(node.getState(), node.getDepth());
				for (Node neighbor : node.expand()) {
					addToFrontier(neighbor);
				}
			}
		}
		return null;
	}

}
