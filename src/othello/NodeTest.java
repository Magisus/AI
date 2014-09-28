package othello;

import static othello.State.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class NodeTest {

	private Node root;
	
	@Before
	public void setUp() throws Exception {
		root = new Node();
	}

	@Test
	public void testToString() {
		assertEquals("<root>\t(0 playouts)\n", root.toString());
	}

	@Test
	public void testAddChild() {
		root.addChild(15);
		assertEquals("<root>\t(0 playouts)\n\t15: NaN\t(0 playouts)\n", root.toString());		
	}
	
	@Test
	public void testLargerTree() {
		root.addChild(15);
		root.addChild(23);
		root.getChild(15).addChild(7);
		assertEquals("<root>\t(0 playouts)\n\t15: NaN\t(0 playouts)\n\t15: \t7: NaN\t(0 playouts)\n\t23: NaN\t(0 playouts)\n", root.toString());		
	}

	@Test
	public void testRecordPlayout() {
		root.addChild(15);
		root.addChild(23);
		root.getChild(15).addChild(7);
		List<Integer> moves = new ArrayList<Integer>();
		moves.add(15);
		moves.add(7);
		root.recordPlayout(moves, 1.0, 'X');
		assertEquals("<root>\t(1 playouts)\n\t15: 1.000\t(1 playouts)\n\t15: \t7: 0.000\t(1 playouts)\n\t23: NaN\t(0 playouts)\n", root.toString());		
	}

	@Test
	public void testExtendTree() {
		List<Integer> moves = new ArrayList<Integer>();
		moves.add(15);
		moves.add(7);
		root.recordPlayout(moves, 1.0, 'X');
		assertEquals("<root>\t(1 playouts)\n\t15: 1.000\t(1 playouts)\n", root.toString());		
		root.recordPlayout(moves, 0.5, 'X');
		assertEquals("<root>\t(2 playouts)\n\t15: 0.750\t(2 playouts)\n\t15: \t7: 0.500\t(1 playouts)\n", root.toString());		
	}

	@Test
	public void testChooseUntriedMove() {
		State state = new State();
		root.addChild(29);
		root.addChild(43);
		int[] counts = new int[WIDTH * WIDTH];
		for (int i = 0; i < 10000; i++) {
			counts[root.playoutMove(state)]++;
		}
		assertTrue(counts[20] > 4000);
		assertTrue(counts[34] > 4000);
	}

	@Test
	public void testClearlyBestMove() {
		State state = new State();
		for (int i : new int[] {20, 29, 34, 43}) {
			root.addChild(i);
			List<Integer> moves = new ArrayList<Integer>();
			moves.add(i);
			if (i == 34) {
				root.recordPlayout(moves, 1.0, 'X');
			} else {
				root.recordPlayout(moves, 0.0, 'X');				
			}
		}
		assertEquals(34, root.playoutMove(state));
	}
	
	@Test
	public void testUctMove() {
		State state = new State();
		for (int i : new int[] {20, 29, 34, 43}) {
			root.addChild(i);
			List<Integer> moves = new ArrayList<Integer>();
			moves.add(i);
			if (i == 34) {
				root.recordPlayout(moves, 0.0, 'X');
			} else {
				for (int j = 0; j < 1000; j++) {
					root.recordPlayout(moves, 0.5, 'X');
				}
			}
		}
		// Move 34 has a much lower win rate, but it should be chosen because it is so severely undersampled
		assertEquals(0.54, root.getChild(20).getUcb1TunedValue(root), 0.01);
		assertEquals(1.41, root.getChild(34).getUcb1TunedValue(root), 0.01);
		assertEquals(34, root.playoutMove(state));
	}
	
//	@Test
//	publci void testToString() {
//		Node root = new Node();
//		root.addChild(50, new Node());
//		root.addChild(65, new Node());
//		root.addChild(64, new Node());
//		asserEquals("")
//	}
	
}


