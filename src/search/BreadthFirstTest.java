package search;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class BreadthFirstTest {

	private BreadthFirstSearcher searcher;
	
	@Before
	public void setUp(){
		searcher = new BreadthFirstSearcher();
	}

	@Test
	public void testCutOff(){
		FifteenPuzzleNode puzzle = new FifteenPuzzleNode("ABCDEFGHIJ.KMNOL");
	}
	
	@Test
	public void testSearch() {
		FifteenPuzzleNode puzzle = new FifteenPuzzleNode("ABCDEFGHIJ.KMNOL");
		assertEquals(2, searcher.search(puzzle).size());
	}

}
