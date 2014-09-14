package search;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class AStarSearcher implements Searcher {

	private PriorityQueue<Node> frontier;
	private int nodeCount;

	public AStarSearcher() {
		nodeCount = 0;
		initializeFrontier();
	}

	@Override
	public void addToFrontier(Node node) {
		if (!frontier.contains(node)) {
			frontier.add(node);
		}
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
		frontier = new PriorityQueue<Node>(1, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if(o1.evaluate() > o2.evaluate()){
					return 1;
				} else if(o1.evaluate() < o2.evaluate()){
					return -1;
				} else {
					return 0;
				}
			}
		});

	}

	@Override
	public boolean inShallower(Node node, Map<String, Integer> map) {
		return map.containsKey(node.getState())
				&& map.get(node.getState()) < node.getDepth();
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
		frontier.clear();
		Map<String, Integer> map = new HashMap<String, Integer>();
		addToFrontier(problem);
		nodeCount++;
		while (!frontier.isEmpty()) {
			Node node = removeFromFrontier();
			map.put(node.getState(), node.getDepth());
			if (node.isGoal()) {
				return node.path();
			}
			for (Node neighbor : node.expand()) {
				if (!inShallower(neighbor, map) && !cutoff(neighbor)) {
					addToFrontier(neighbor);
					nodeCount++;
				}
			}
		}
		return null;
	}

}