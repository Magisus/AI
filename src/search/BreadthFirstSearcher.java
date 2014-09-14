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
		if (!frontier.contains(node)) {
			frontier.add(node);
		}
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
		return map.containsKey(node.getState()) && map.get(node.getState()) < node.getDepth();
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
		nodeCount++;
		while (!frontier.isEmpty()) {
			Node node = removeFromFrontier();
			map.put(node.getState(), node.getDepth());
			if (node.isGoal()) {
				return node.path();
			}
			for (Node neighbor : node.expand()) {
				if (!inShallower(neighbor, map)) {
					addToFrontier(neighbor);
					nodeCount++;
				}
			}
		}
		return null;
	}

}
