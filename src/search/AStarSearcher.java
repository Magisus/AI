package search;

import java.util.LinkedList;
import java.util.Map;

public class AStarSearcher implements Searcher {

	@Override
	public void addToFrontier(Node node) {
		int x =5;
		// TODO Auto-generated method stub

	}

	@Override
	public boolean cutoff(Node node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean frontierIsEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getNodeCount() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetNodeCount() {
		// TODO Auto-generated method stub

	}

	@Override
	public LinkedList<Character> search(Node problem) {
		// TODO Auto-generated method stub
		return null;
	}

}
