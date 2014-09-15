package search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AStarTest {

	private AStarSearcher searcher;
	
	@Before
	public void setUp() throws Exception {
		searcher = new AStarSearcher();
	}

	@Test
	public void testSearch() {
		FifteenPuzzleNode puzzle = new FifteenPuzzleNode("ABCDEFGHIJ.KMNOL");
		assertEquals(2, searcher.search(puzzle).size());
	}

}
