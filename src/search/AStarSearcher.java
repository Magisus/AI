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
		frontier.add(node);
		nodeCount++;
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
				return o1.compareTo(o2);
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
			if (!inShallower(node, map) && !cutoff(node)) {
				map.put(node.getState(), node.getDepth());
				for (Node neighbor : node.expand()) {
					addToFrontier(neighbor);
				}
			}
		}
		return null;
	}

}